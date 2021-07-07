package com.chen.mybatisPlugin;

import com.chen.mybatisPlugin.model.Blog;
import com.chen.mybatisPlugin.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MybatisPluginApplicationTests {
    @Autowired
    BlogService blogService;

    @Test
    void contextLoads() {
        Blog blog = blogService.selectByPrimaryKey(1420226);
        System.out.println(blog);
    }

    @Test
    void test2(){
        Blog blog = new Blog();
        blog.setTitle("测试?使用");
        blog.setBlogername("测试使用");
        blog.setPublish(new Date());
        blog.setViews(10);
        blog.setUrl("www.baidu.com");
        blog.setIspick(0);
        int insert = blogService.insert(blog);
        System.out.println(insert);
    }

    @Test
    void test3(){
        Blog blog = new Blog();
        blog.setTitle("测试使用");
        blog.setId(1420226);
        blogService.updateByPrimaryKeySelective(blog);
    }

}
