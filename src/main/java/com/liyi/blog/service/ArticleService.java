package com.liyi.blog.service;

import com.liyi.blog.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liyi.blog.vo.Result;
import com.liyi.blog.vo.params.PageParams;

/**
* @author liyi
* @description 针对表【article】的数据库操作Service
* @createDate 2022-03-23 21:47:32
*/

public interface ArticleService extends IService<Article> {
    /**
     * 分页查询文章
     * @param pageParams
     * @return
     */
    Result listArticles(PageParams pageParams);

    Result hotArticles(int limit);

    Result newArticle(int limit);

    Result listArchives();
}
