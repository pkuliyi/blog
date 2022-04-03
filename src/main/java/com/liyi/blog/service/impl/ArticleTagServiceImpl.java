package com.liyi.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyi.blog.mapper.TagMapper;
import com.liyi.blog.pojo.ArticleTag;
import com.liyi.blog.pojo.Tag;
import com.liyi.blog.service.ArticleTagService;
import com.liyi.blog.mapper.ArticleTagMapper;
import com.liyi.blog.vo.TagVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author liyi
* @description 针对表【article_tag】的数据库操作Service实现
* @createDate 2022-03-24 15:41:37
*/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService{

}




