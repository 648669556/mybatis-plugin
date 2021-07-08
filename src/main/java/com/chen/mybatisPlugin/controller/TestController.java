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

    @PostMapping(value = "/test/sql", produces = {"application/json;charset=utf-8"})
    public String testSql(@RequestBody SqlRequest sqlRequest) {
        String jsonString = JSON.toJSONString(sqlRequest);
        System.out.println(jsonString);
        return jsonString;
    }
}
