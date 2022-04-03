package com.liyi.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyi.blog.mapper.ArticleMapper;
import com.liyi.blog.mapper.TagMapper;
import com.liyi.blog.pojo.Article;
import com.liyi.blog.pojo.Tag;
import com.liyi.blog.service.ArticleService;
import com.liyi.blog.vo.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    ArticleService articleService;

    @Resource
    ArticleMapper articleMapper;

    @Resource
    TagMapper tagMapper;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    void contextLoads() {
        String salt = "mszlu!@#";
        String s = DigestUtils.md5Hex(123 + salt);
        System.out.println(s);
    }

}
