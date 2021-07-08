package com.chen.mybatisPlugin.config;

import com.chen.mybatisPlugin.interceptor.MyInterceptor;
import com.chen.mybatisPlugin.task.SendTask;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈俊宏
 */
@Configuration
public class InterceptorConfig {
    @Value("${sendTask.tryTimes}")
    private Integer tryTimes;
    @Value("${sendTask.targetUrl}")
    private String targetUrl;
    @Bean
    public CloseableHttpClient createClient(@Autowired HttpRequestRetryHandler retryHandler, @Autowired PoolingHttpClientConnectionManager connectionManager) {
        return HttpClients.custom().setConnectionManager(connectionManager).setRetryHandler(retryHandler).build();
    }

    @Bean
    public PoolingHttpClientConnectionManager getConnectionManager() {//1.1
        return new PoolingHttpClientConnectionManager(60000, TimeUnit.MILLISECONDS);
    }

    @Bean
    public HttpRequestRetryHandler retryHandler() {
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                // 如果已经重试了n次，就放弃
                if (executionCount >= tryTimes) {
                    return false;
                }
                // 如果服务器丢掉了连接，那么就重试
                if (exception instanceof NoHttpResponseException) {
                    return true;
                }
                // 不要重试SSL握手异常
                if (exception instanceof SSLHandshakeException) {
                    return false;
                }
                // 超时
                if (exception instanceof InterruptedIOException) {
                    return false;
                }
                // 目标服务器不可达
                if (exception instanceof UnknownHostException) {
                    return true;
                }
                // 连接被拒绝
                if (exception instanceof ConnectTimeoutException) {
                    return false;
                }
                // SSL握手异常
                if (exception instanceof SSLException) {
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };
        return httpRequestRetryHandler;
    }

    @Bean
    public MyInterceptor myInterceptor(@Autowired SendTask sendTask) {
        return new MyInterceptor(sendTask);
    }

    @Bean
    public SendTask getSendTask(@Autowired CloseableHttpClient client){
        return new SendTask(client,targetUrl);
    }
}
