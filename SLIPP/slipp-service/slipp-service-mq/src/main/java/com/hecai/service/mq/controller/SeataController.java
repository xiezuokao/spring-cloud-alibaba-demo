package com.hecai.service.mq.controller;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.hecai.api.mq.bean.Test2;
import com.hecai.service.mq.dao.Test2Dao;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/seata")
public class SeataController {
    @Autowired
    Test2Dao test2Dao;

    @GlobalTransactional
    @GetMapping("/test")
    public Object test(){
        int insert = test2Dao.insert(new Test2() {{
            setUuid(UuidUtils.generateUuid());
        }});
        return insert;
    }
}
