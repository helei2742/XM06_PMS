package org.xm06.pms.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.xm06.pms.model.InformModel;
import org.xm06.pms.model.UserModel;
import org.xm06.pms.service.GroupService;
import org.xm06.pms.service.InformService;
import org.xm06.pms.service.UserService;
import org.xm06.pms.utils.RandomUtil;
import org.xm06.pms.utils.UserIDBase64;
import org.xm06.pms.vo.User;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
@Service
@ServerEndpoint("/api/websocket/{uid}/{groupId}")
public class WebSocketServer {

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static final AtomicInteger onlineCount = new AtomicInteger(0);

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
    private final static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    //小组id，和小组的通知消息的map
    public static final Hashtable<Integer, Vector<InformModel>> groupMessage = new Hashtable<>();

    //存储用户id，小组id，对应的未看过的消息id  <用户id, <小组id, 未看消息id[]> >
    public static final Hashtable<Integer,Hashtable<Integer, Vector<Long>>> notReadMessage = new Hashtable<>();


    /**
     * 清除notReadMessage,和groupMessage的内容，主要在定时保存这些数据后调用
     */
    public static void clearStaticMsg() {
//        System.out.println(groupMessage);
//        System.out.println(notReadMessage);
        Set<Integer> keySet = groupMessage.keySet();
        for (Integer integer : keySet) {
            groupMessage.get(integer).clear();
        }

        Set<Integer> keySet1 = notReadMessage.keySet();
        for (Integer integer : keySet1) {
            Hashtable<Integer, Vector<Long>> hashtable = notReadMessage.get(integer);
            Set<Integer> keySet2 = hashtable.keySet();
            for (Integer integer1 : keySet2) {
                hashtable.get(integer1).clear();
            }
        }
//        System.out.println("清除后");
//        System.out.println(groupMessage);
//        System.out.println(notReadMessage);
    }

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //接收sid
    private Integer uid = null;
    //接受小组id
    private Integer groupId = null;




    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session
            , @PathParam("uid") Integer uid
            , @PathParam("groupId") Integer groupId) {

        this.session = session;
        webSocketSet.add(this);     // 加入set中

        this.uid = uid;
        this.groupId = groupId;

        //一、链接时初始化对应id 、groupId 的未读消息容器
        //1、用户未看的消息    <小组id, 未看消息[]>
        Hashtable<Integer, Vector<Long>> hashtable =
                WebSocketServer.notReadMessage.getOrDefault(uid, new Hashtable<>());

        //2 初始化用户的  <小组id, 未看消息[]>
        hashtable.putIfAbsent(groupId, new Vector<>());

        //3 初始化静态存储的 <用户id, <小组id, 未看消息[]> >
        WebSocketServer.notReadMessage.put(uid, hashtable);

        addOnlineCount();           // 在线数加1

        try {
            System.out.println("有新客户端开始监听,uid=" + this.uid + "groupId="+groupId+",当前在线人数为:" + getOnlineCount());

            //发送给用户未读消息
            //1、数据库中未读的消息
            Vector<InformModel> dbNotRead = queryNotReadInDataBase(this.uid, this.groupId);

            //2、内存中未读消息id
            Vector<Long> notReadIds = WebSocketServer.notReadMessage.get(uid).get(groupId);
            Vector<InformModel> notRead = new Vector<>();
            //3、内存中有未读记录页添加到notRead中
            if(WebSocketServer.groupMessage.get(groupId) != null) {
                for (InformModel informModel : WebSocketServer.groupMessage.get(groupId)) {
                    for (Long notReadId : notReadIds) {
                        if (informModel.getId().equals(notReadId))
                            notRead.add(informModel);
                    }
                }
            }
            //4、合并两部分未读消息
            notRead.addAll(dbNotRead);

            for (InformModel informModel : notRead) {
                //发送给本socket的客户端
                sendMessage(informModel);
            }

        } catch (IOException e) {
            System.out.println("websocket IO Exception");
        }
    }


    private static InformService informService;
    @Autowired
    public void setInformService(InformService informService) {
        WebSocketServer.informService = informService;
    }

    private Vector<InformModel> queryNotReadInDataBase(Integer uid, Integer groupId){
        return informService.queryNotReadInform(uid, groupId);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  // 从set中删除
        subOnlineCount();              // 在线数减1
        // 断开连接情况下，更新主板占用情况为释放
        System.out.println("释放的sid=" + uid + "groupId="+groupId+"的客户端");
        releaseResource();
    }

    private void releaseResource() {
        // 这里写释放资源和要处理的业务
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }


    /**
     * 收到客户端消息后调用的方法
     * @Param jsonObj 客户端发送过来的消息,为一个json对象，包含该消息包含的组id groupId 和 信息message
     *
     */
    @OnMessage
    public void onMessage(String jsonObj, Session session) throws IOException {
        System.out.println("收到来自客户端 uid=" + uid +"groupId="+groupId+ " 的信息:" + jsonObj);
        InformModel informModel = JSONObject.parseObject(jsonObj, InformModel.class);

        //设置对应属性
        //随机消息id
        informModel.setId(RandomUtil.getRandomId(this.uid));
        informModel.setSendDate(new Date().getTime());
        informModel.setSendUser(getUserModel(this.uid));

        Integer groupId = informModel.getGroupId();

        //获取需群发的小组成员id
        List<Integer> idList = getGroupMemberIdList(groupId);

        //将消息保存到内存（不需存到数据库，设置定时存储到数据库）
        Vector<InformModel> informModels = WebSocketServer.groupMessage.getOrDefault(groupId, new Vector<>());
        informModels.add(informModel);
        WebSocketServer.groupMessage.put(groupId, informModels);

        //添加该消息未读到对应的 uid，groupId中
        for (Integer toId : idList) {
            this.addNotReadId(informModel.getId(),toId,groupId);
        }

        try {
            //群发通知
            sendMessage(informModel, idList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将未读消息id添加到 WebSocketServer.notReadMessage 中
     * @param id
     */
    private void addNotReadId(Long id, Integer uid, Integer groupId){
        System.out.println("添加未读"+uid+"="+groupId+"="+id);
        Hashtable<Integer, Vector<Long>> hashtable =
                WebSocketServer.notReadMessage.getOrDefault(uid, new Hashtable<>());
        Vector<Long> vector = hashtable.getOrDefault(groupId, new Vector<>());
        //排除发送者自己
        if(!uid.equals(this.uid)){
            vector.add(id);
        }
        hashtable.put(groupId, vector);
        WebSocketServer.notReadMessage.put(uid, hashtable);
    }

    /**
     * 发生错误回调
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println(session.getBasicRemote() + "客户端发生错误");
        error.printStackTrace();
    }

    /**
     * 群发自定义消息
     */
    public static void sendMessage(InformModel informModel, List<Integer> toIds) throws IOException {
        System.out.println("推送消息到客户端 uid:" + toIds + "groupId:"+informModel.getGroupId()+
                "，推送内容:" + informModel);

        if(toIds== null || toIds.size() <= 0) {
            return;
        }
        //向相应的socket发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                //给小组的成员发送消息
                if(toIds.contains(item.uid) && item.groupId.equals(informModel.getGroupId())){
                    item.sendMessage(informModel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 实现服务器主动推送消息到 指定客户端
     */
    public void sendMessage(InformModel informModel) throws IOException {
        Boolean saveF = informModel.getNotRead();

        //看该用户在该组的消息是否读过
        // 数据库中获取的未读消息在获取的稍后就标记notRead为true了
        if(informModel.getNotRead() == null) {
            boolean notRead =
                    WebSocketServer.notReadMessage.get(uid).get(groupId).contains(informModel.getId());
            informModel.setNotRead(notRead);
        }
        this.session.getBasicRemote().sendText(JSON.toJSONString(informModel));
        //会更改此对象的notRead值，发送完成后重置
        informModel.setNotRead(saveF);
    }



    private static GroupService groupService;
    private static UserService userService;
    @Autowired
    public void setGroupService(GroupService groupService) {
        WebSocketServer.groupService = groupService;
    }
    @Autowired
    public void setUserService(UserService userService){
        WebSocketServer.userService = userService;
    }

    /**
     * 获取小组的成员id列表
     * @param groupId
     * @return
     */
    private List<Integer> getGroupMemberIdList(Integer groupId){
        return groupService.queryGroupMemberIdList(groupId);
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    private UserModel getUserModel(Integer userId){
        User user = userService.selectByPrimaryKey(userId);
        UserModel userModel = new UserModel();
        userModel.setUsername(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        userModel.setUserIdStr(UserIDBase64.encoderUserID(userId));
        return userModel;
    }

    /**
     * 获取当前在线人数
     *
     * @return
     */
    public static int getOnlineCount() {
        return onlineCount.get();
    }

    /**
     * 当前在线人数 +1
     *
     * @return
     */
    public static void addOnlineCount() {
        onlineCount.getAndIncrement();
    }

    /**
     * 当前在线人数 -1
     *
     * @return
     */
    public static void subOnlineCount() {
        onlineCount.getAndDecrement();
    }

    /**
     * 获取当前在线客户端对应的WebSocket对象
     *
     * @return
     */
    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }
}