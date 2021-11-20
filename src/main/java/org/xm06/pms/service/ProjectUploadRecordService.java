package org.xm06.pms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.xm06.pms.dao.ProjectMapper;
import org.xm06.pms.dao.ProjectUpdateRecordMapper;
import org.xm06.pms.dao.UserMapper;
import org.xm06.pms.query.ProjectRecordQuery;
import org.xm06.pms.utils.AssertUtil;
import org.xm06.pms.utils.FileUtil;
import org.xm06.pms.vo.Project;
import org.xm06.pms.vo.ProjectUpdateRecord;
import org.xm06.pms.vo.User;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@Service
public class ProjectUploadRecordService {

    @Resource
    ProjectUpdateRecordMapper projectUpdateRecordMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    ProjectMapper projectMapper;

    /**
     * 添加项目提交记录
     * @param record
     * @param file
     * @throws IOException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addProjectUpdateRecord(ProjectUpdateRecord record, MultipartFile file) throws IOException {
        AssertUtil.isTrue(record.getSubmitDegree() == null
                            || record.getSubmitDegree() <= 0
                ,"提交进度非法" );

        User user = userMapper.selectByPrimaryKey(record.getUserId());
        AssertUtil.isTrue(user == null, "提交用户不存在");

        Project project = projectMapper.selectByPrimaryKey(record.getProjectId());
        AssertUtil.isTrue(project == null, "该项目不存在");

        //更新项目的进度
        Double degree = project.getCompletionDegree();
        Project updateProject = new Project();
        updateProject.setId(project.getId());
        updateProject.setCompletionDegree(degree + record.getSubmitDegree());

        AssertUtil.isTrue(projectMapper.updateByPrimaryKeySelective(updateProject)<1,
                "保存进度到项目失败");


        if(file != null) {
            String folder = FileUtil.getUserSubmitProjectRecordPath(user.getUserName(), project.getId());
            String src = FileUtil.saveFile(file, folder);
            record.setUploadFileSrc(src);
        }
        record.setCreateDate(new Date());
        record.setUpdateDate(new Date());
        record.setIsValid(true);
        AssertUtil.isTrue(projectUpdateRecordMapper.insertSelective(record)<1, "保存项目进度失败");
    }


    /**
     * 分页查询项目提交记录列表
     * @param recordQuery 需含有projectId
     * @return
     */
    public PageInfo<ProjectUpdateRecord> pageQueryProjectUpdateRecord(ProjectRecordQuery recordQuery){
        PageHelper.startPage(recordQuery.getPage(), recordQuery.getLimit());

        List<ProjectUpdateRecord> all =
                projectUpdateRecordMapper.queryUpdateRecord(recordQuery.getProjectId());


        //装入user，自己装，节约数据库开销
        HashMap<Integer, List<ProjectUpdateRecord>> map = new HashMap<>();
        for (ProjectUpdateRecord record : all) {
            List<ProjectUpdateRecord> list = map.getOrDefault(record.getUserId(), new ArrayList<>());
            list.add(record);
            map.put(record.getUserId(), list);
        }

        Set<Integer> keySet = map.keySet();
        for (Integer userId : keySet) {
            User user = userMapper.selectByPrimaryKey(userId);
            List<ProjectUpdateRecord> list = map.get(userId);
            for (ProjectUpdateRecord record : list) {
                record.setUser(user);
            }
        }

        return new PageInfo<>(all);
    }
}
