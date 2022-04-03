package com.liyi.blog.mapper;

import com.liyi.blog.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyi.blog.vo.Archives;

import java.util.List;

/**
* @author liyi
* @description 针对表【article】的数据库操作Mapper
* @createDate 2022-03-23 21:47:32
* @Entity com.liyi.blog.pojo.Article
*/
public interface ArticleMapper extends BaseMapper<Article> {
    List<Archives> listArchives();
}




