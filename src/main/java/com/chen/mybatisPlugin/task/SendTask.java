package com.chen.mybatisPlugin.task;

import com.alibaba.fastjson.JSON;
import com.chen.mybatisPlugin.model.SqlRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 陈俊宏
 */
@Component
public class SendTask {
    final CloseableHttpClient httpClient;

    @Autowired
    public SendTask(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void doPost(SqlRequest sqlRequest) throws IOException {
        HttpPost httpPost = new HttpPost("http://localhost:8080/test/sql");
        String jsonString = JSON.toJSONString(sqlRequest);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        CloseableHttpResponse result = httpClient.execute(httpPost);
        handlerResponse(result);
    }

    private void handlerResponse(CloseableHttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        System.out.println("响应状态为:" + response.getStatusLine());
        if (entity != null) {
            System.out.println("响应内容长度为:" + entity.getContentLength());
            System.out.println("响应内容为:" + EntityUtils.toString(entity));
        }
    }
}
