package com.hecai.service.test.controller;

import com.hecai.common.config.restdata.Code;
import com.hecai.common.config.restdata.RestData;
import com.hecai.service.test.dao.InfoFile;
import com.hecai.service.test.dao.InfoFileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RefreshScope
public class TestController {
    @Value("${server.port}")
    String port;
    @Value("${config.appKey}")
    private String appKey;
    @Autowired
    InfoFileDao infoFileDao;

    @GetMapping("/test1")
    public Object test1(){
        return port+"服务实例:"+this.appKey;
    }

    @GetMapping("/test2")
    public RestData test2(){
        return new RestData(Code.INFO_SUCCEED);
    }

    @GetMapping("/test3")
    public List<InfoFile> test3(){
        return infoFileDao.selectList(null);
    }
}
