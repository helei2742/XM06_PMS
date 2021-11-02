package org.xm06.pms.service;

import org.springframework.stereotype.Service;
import org.xm06.pms.dao.InformMapper;
import org.xm06.pms.vo.Inform;

import javax.annotation.Resource;

@Service
public class InformService {

    @Resource
    InformMapper informMapper;

    /**
     * 保存消息
     * @param inform
     */
    public Integer saveInform(Inform inform) {
        return informMapper.insertHasKey(inform);
    }

}
