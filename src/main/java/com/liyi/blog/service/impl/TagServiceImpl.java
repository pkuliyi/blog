package com.liyi.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyi.blog.mapper.ArticleTagMapper;
import com.liyi.blog.pojo.ArticleTag;
import com.liyi.blog.pojo.Tag;
import com.liyi.blog.service.TagService;
import com.liyi.blog.mapper.TagMapper;
import com.liyi.blog.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author liyi
* @description 针对表【tag】的数据库操作Service实现
* @createDate 2022-03-24 15:45:53
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService{

    @Resource
    TagMapper tagMapper;

    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        List<Tag> tagsByArticleId = tagMapper.findTagsByArticleId(articleId);
        return copyList(tagsByArticleId);
    }

    @Override
    public List<TagVo> hot(int limit) {
        //标签拥有的文章数量最多是最热标签
        List<Long> hotTagIds = tagMapper.findHotTagIds(limit);
        List<TagVo> tagVoList = new ArrayList<>();
        for (Long hotTagId : hotTagIds) {
            Tag tag = tagMapper.selectById(hotTagId);
            TagVo tagVo = new TagVo();
            BeanUtils.copyProperties(tag,tagVo);
            tagVoList.add(tagVo);
        }
        return tagVoList;
    }

    private List<TagVo> copyList(List<Tag> tagsByArticleId) {
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagsByArticleId) {
            tagVoList.add(copy(tag));
        }
        return  tagVoList;
    }

    private TagVo copy(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        return tagVo;
    }

}




