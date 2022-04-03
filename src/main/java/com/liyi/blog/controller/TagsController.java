package com.liyi.blog.controller;

import com.liyi.blog.service.TagService;
import com.liyi.blog.vo.Result;
import com.liyi.blog.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController {

    @Resource
    private TagService tagService;

    //最热标签
    @GetMapping("/hot")
    public Result hot(){
        int limit = 6;
        List<TagVo> tagVoList = tagService.hot(limit);
        return Result.success(tagVoList);
    }

}
