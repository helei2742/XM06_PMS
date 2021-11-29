package org.xm06.pms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xm06.pms.Starter;
import org.xm06.pms.dao.SystemRecordMapper;
import org.xm06.pms.utils.DateUtil;
import org.xm06.pms.vo.SystemRecord;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SystemRecordService {

    /**
     * 记录登录网站的idset
     */
    private static final CopyOnWriteArraySet<Integer> userIdStrSet = new CopyOnWriteArraySet<>();
    public void addLoginUserId(Integer i) {
        userIdStrSet.add(i);
        System.out.println(userIdStrSet);
    }

    /**
     * 记录注册用户的数量
     */
    private static final AtomicInteger registerCount = new AtomicInteger(0);
    public void addRegisterCount() {
        registerCount.getAndIncrement();
        System.out.println(registerCount+"注册数量");
    }

    /**
     * 记录提交任务的数量
     */
    private static final AtomicInteger taskSubmitCount = new AtomicInteger(0);
    public void addTaskSubmitCount() {
        taskSubmitCount.getAndIncrement();
        System.out.println(taskSubmitCount+"提交数量");
    }

    /**
     * 记录创建了多少任务
     */
    private static final AtomicInteger taskCreateCount = new AtomicInteger(0);
    public void addTaskCreateCount() {
        taskCreateCount.getAndIncrement();
    }

    /**
     * 记录创建了多少小组
     */
    private static final AtomicInteger groupCreateCount = new AtomicInteger(0);
    public void addGroupCreateCount() {
        groupCreateCount.getAndIncrement();
    }

    /**
     * 记录创建了多少项目
     */
    private static final AtomicInteger projectCreateCount = new AtomicInteger(0);
    public void addProjectCreateCount() {
        projectCreateCount.getAndIncrement();
    }

    /**
     * 记录更新进度数量
     */
    private static final AtomicInteger projectUpdateCount = new AtomicInteger(0);
    public void addProjectUpdateCount() {
        projectUpdateCount.getAndIncrement();
    }

    private void reset(){
        userIdStrSet.clear();
        registerCount.set(0);
        taskSubmitCount.set(0);
        taskCreateCount.set(0);
        groupCreateCount.set(0);
        projectCreateCount.set(0);
        projectUpdateCount.set(0);
    }

    @Resource
    SystemRecordMapper systemRecordMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void restoreSystemRecord() {

        SystemRecord systemRecord = new SystemRecord();

        systemRecord.setRecordDate(DateUtil.getToday());
        systemRecord.setLoginCount(userIdStrSet.size());
        systemRecord.setRegisterCount(registerCount.get());
        systemRecord.setTaskSubmitCount(taskSubmitCount.get());
        systemRecord.setTaskCreateCount(taskCreateCount.get());
        systemRecord.setGroupCreateCount(groupCreateCount.get());
        systemRecord.setProjectCreateCount(projectCreateCount.get());
        systemRecord.setProjectUpdateCount(projectUpdateCount.get());
        reset();
        systemRecordMapper.insertSelective(systemRecord);

    }

    /**
     * 获取7天之内mei一天的数据，
     * {"登录人数", "注册人数", "提交任务数量", "创建任务数量", "创建小组数量",
     *             "创建项目数量", "提交项目进度数量"}
     * @return
     */
    public Map<String, Object> last7DaySystemRecord(){
        List<SystemRecord> query8Day = systemRecordMapper.query8Day();
        Map<String, List<Integer>> titleMapData = new HashMap<>();
        String[] titles = SystemRecord.titles;
        String[] columns = null;
//        Integer maxY = -1;
//        if(query8Day.size()>1){
//             columns = new String[query8Day.size()-1];
//            for (int i = 1; i < query8Day.size(); i++) {
//                SystemRecord last = query8Day.get(i-1);
//                SystemRecord systemRecord = query8Day.get(i);
//                columns[i-1] = DateUtil.formatSystemRecordDate(systemRecord.getRecordDate());
//
//                for (String title : titles) {
//                    List<Integer> dataList = titleMapData.getOrDefault(title, new ArrayList<>());
//                    Integer today = systemRecord.get(title) - last.get(title);
//                    maxY = Math.max(maxY, today);
//                    dataList.add(today);
//                    titleMapData.put(title, dataList);
//                }
//            }
//        }
        if(query8Day.size()>1){
            columns = new String[query8Day.size()-1];
            for (int i = 1; i < query8Day.size(); i++) {
                SystemRecord systemRecord = query8Day.get(i);
                columns[i-1] = DateUtil.formatSystemRecordDate(systemRecord.getRecordDate());
                for (String title : titles) {
                    List<Integer> dataList = titleMapData.getOrDefault(title, new ArrayList<>());
                    dataList.add(systemRecord.get(title));
                    titleMapData.put(title, dataList);
                }

            }
        }
        Map<String, Object> res = new HashMap<>();
        res.put("legend", titles);
        res.put("series", titleMapData);
        res.put("xAxis", columns);
        res.put("code", 200);
        return res;
    }

    /**
     * 获取系统总记录情况，也就是最后一条xm06_t_system_record 表中的记录
     *
     * @return
     */
    public Map<String, Object> systemTotalRecord() {
//        SystemRecord totalRecord = systemRecordMapper.queryLastRecord();
//        Integer integer = systemRecordMapper.queryTotalLogin();
//        totalRecord.setLoginCount(integer);
        SystemRecord totalRecord = systemRecordMapper.queryTotalRecord();

        String[] titles = SystemRecord.titles;
        List<Integer> dataList = new ArrayList<>();
        for (String title : titles) {
            dataList.add(totalRecord.get(title));
        }

        Map<String, Object> res = new HashMap<>();
        res.put("xAxis", titles);
        res.put("series", dataList);
        return res;
    }
}
