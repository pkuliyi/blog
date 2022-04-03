package com.liyi.blog.controller;

import com.liyi.blog.pojo.Article;
import com.liyi.blog.service.ArticleService;
import com.liyi.blog.vo.Result;
import com.liyi.blog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("articles")
public class ArticleController {


    @Resource
    private ArticleService articleService;

    @PostMapping
    public Result listArticle(@RequestBody PageParams pageParams){
        return articleService.listArticles(pageParams);
    }

    @RequestMapping("/hot")
    public Result hotArticles(){
        int limit =5;
        return articleService.hotArticles(limit);
    }

    @PostMapping("/new")
    public Result newArticles(){
        int limit=5;
        return articleService.newArticle(limit);
    }

    /**
     * 首页 文章归档
     * @return
     */
    @PostMapping("/listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }


}
