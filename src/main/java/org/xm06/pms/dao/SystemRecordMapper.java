package org.xm06.pms.dao;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.xm06.pms.base.BaseMapper;
import org.xm06.pms.vo.SystemRecord;

import java.util.List;

public interface SystemRecordMapper extends BaseMapper<SystemRecord,Integer> {

    public List<SystemRecord> query8Day();

    SystemRecord queryLastRecord();
    Integer queryTotalLogin();
    SystemRecord queryTotalRecord();
}