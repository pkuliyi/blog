package com.liyi.blog.service;

import com.liyi.blog.pojo.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liyi.blog.vo.TagVo;

import java.util.List;

/**
* @author liyi
* @description 针对表【tag】的数据库操作Service
* @createDate 2022-03-24 15:45:53
*/
public interface TagService extends IService<Tag> {

    List<TagVo> findTagsByArticleId(Long articleId);

    List<TagVo> hot(int limit);
}
