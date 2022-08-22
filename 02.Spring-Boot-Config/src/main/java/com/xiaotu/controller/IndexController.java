package com.xiaotu.controller;

import com.xiaotu.bean.BlogProperties;
import com.xiaotu.bean.ConfigBean;
import com.xiaotu.bean.TestConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private BlogProperties blogProperties;
    @Autowired
    private ConfigBean configBean;
    @Autowired
    private TestConfigBean testConfigBean;

    @RequestMapping("/")
    String index() {
        return testConfigBean.getName()+"，"+testConfigBean.getAge();
        //        return configBean.getName()+"，"+configBean.getAge();
    }

}
