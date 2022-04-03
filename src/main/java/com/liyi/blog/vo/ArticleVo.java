package com.liyi.blog.vo;

import lombok.Data;

import java.util.List;


/**
 * @author liyi
 */

@Data
public class ArticleVo {

    private Long id;

    private String title;

    private String summary;

    private int commentCounts;

    private int viewCounts;

    private int weight;

    /**
     * 创建时间
     */

    private String createDate;

    private String author;

    private List<TagVo> tags;

    //private ArticleBodyVo body;

    //private List<CategoryVo> categorys;

}
