package com.liyi.blog.mapper;

import com.liyi.blog.pojo.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liyi
* @description 针对表【tag】的数据库操作Mapper
* @createDate 2022-03-24 15:45:53
* @Entity com.liyi.blog.pojo.Tag
*/
public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> findTagsByArticleId(Long articleId);

    List<Long> findHotTagIds(int limit);
}




