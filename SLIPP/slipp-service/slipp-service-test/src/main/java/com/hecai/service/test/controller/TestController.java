package com.hecai.service.test.controller;

//import com.hecai.api.mq.feign.SeataFeign;
import com.hecai.api.mq.feign.SeataFeign;
import com.hecai.common.config.restdata.Code;
import com.hecai.common.config.restdata.RestData;
import com.hecai.service.test.dao.InfoFile;
import com.hecai.service.test.dao.InfoFileDao;
import com.hecai.service.test.dao.Test1;
import com.hecai.service.test.dao.Test1Dao;
import io.seata.spring.annotation.GlobalTransactional;
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

    @Autowired
    SeataFeign seataFeign;
    @Autowired
    Test1Dao test1Dao;

    @GlobalTransactional
    @GetMapping("/test4")
    public Object test4(){
        RestData test = seataFeign.test();
        int i=2/0;
        int count = test1Dao.insert(new Test1() {{
            setName("xiaoli");
            setAge(18);
        }});
        return "1111";
    }
}
