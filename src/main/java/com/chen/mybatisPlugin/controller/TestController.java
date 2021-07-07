package com.chen.mybatisPlugin.controller;

import com.alibaba.fastjson.JSON;
import com.chen.mybatisPlugin.model.SqlRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈俊宏
 */
@RestController
public class TestController {

    @PostMapping(value = "/test/sql",produces = {"applictaion/json;charset=utf-8"})
    public String testSql(@RequestBody SqlRequest sqlRequest) {
        return JSON.toJSONString(sqlRequest);
    }
}
