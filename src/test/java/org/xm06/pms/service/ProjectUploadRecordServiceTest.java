package org.xm06.pms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProjectUploadRecordServiceTest {
    @Autowired
    ProjectUploadRecordService projectUploadRecordService;
    @Test
    public void queryProjectSubmit14day() {
        projectUploadRecordService.queryProjectSubmit14day(8);
    }
}