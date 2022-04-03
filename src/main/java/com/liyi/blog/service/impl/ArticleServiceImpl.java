package com.liyi.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyi.blog.pojo.Article;
import com.liyi.blog.pojo.SysUser;
import com.liyi.blog.service.ArticleService;
import com.liyi.blog.mapper.ArticleMapper;
import com.liyi.blog.service.SysUserService;
import com.liyi.blog.service.TagService;
import com.liyi.blog.vo.Archives;
import com.liyi.blog.vo.ArticleVo;
import com.liyi.blog.vo.Result;
import com.liyi.blog.vo.TagVo;
import com.liyi.blog.vo.params.PageParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author liyi
* @description 针对表【article】的数据库操作Service实现
* @createDate 2022-03-23 21:47:32
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{

    @Resource
    ArticleMapper articleMapper;

    @Resource
    TagService tagService;

    @Resource
    SysUserService sysUserService;

    @Override
    public Result listArticles(PageParams pageParams) {
        Page<Article> page = new Page<Article>(pageParams.getPage(),pageParams.getPageSize());
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //置顶排序设置
        lambdaQueryWrapper.orderByDesc(Article::getWeight);
        //创建日期倒序排序
        lambdaQueryWrapper.orderByDesc(Article::getCreateDate);
        Page<Article> articlePage = articleMapper.selectPage(page,lambdaQueryWrapper);
        List<Article> records = articlePage.getRecords();
        List<ArticleVo> articleVoList = copylist(records,true,true);
        return Result.success(articleVoList);
    }

    @Override
    public Result hotArticles(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getViewCounts);
        queryWrapper.select(Article::getId,Article::getTitle);
        //无视优化规则直接拼接到 sql 的最后
        queryWrapper.last("limit "+limit);
        List<Article> articleList = articleMapper.selectList(queryWrapper);
        return Result.success(articleList);
    }

    @Override
    public Result newArticle(int limit) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(Article::getId,Article::getTitle);
        lambdaQueryWrapper.orderByDesc(Article::getCreateDate);
        lambdaQueryWrapper.last("limit "+limit);
        List<Article> articleList = articleMapper.selectList(lambdaQueryWrapper);
        return Result.success(articleList);
    }

    @Override
    public Result listArchives() {
        List<Archives> archives = articleMapper.listArchives();
        return Result.success(archives);
    }

    private List<ArticleVo> copylist(List<Article> records,boolean needTag,boolean needAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,needTag,needAuthor));
        }
        return articleVoList;
    }

    private ArticleVo copy (Article article,boolean needTag,boolean needAuthor){
        ArticleVo articleVo = new ArticleVo();
        //copy两个类中的相同属性
        BeanUtils.copyProperties(article,articleVo);
        //设置时间
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd"));

        if(needTag){
            //设置标签信息
            List<TagVo> tagsByArticleId = tagService.findTagsByArticleId(article.getId());
            articleVo.setTags(tagsByArticleId);
        }

        if(needAuthor) {
            //设置用户名称
            SysUser userById = sysUserService.findUserById(article.getAuthorId());
            articleVo.setAuthor(userById.getNickname());
        }

        return articleVo;
    }

}




