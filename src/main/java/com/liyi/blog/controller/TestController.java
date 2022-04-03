package com.liyi.blog.controller;

import com.liyi.blog.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping
    public Result test(){
        return Result.success(null);
    }

}
