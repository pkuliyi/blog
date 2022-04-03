package com.liyi.blog.service;

import com.liyi.blog.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liyi.blog.vo.Result;

/**
* @author liyi
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2022-03-23 21:48:13
*/
public interface SysUserService extends IService<SysUser> {

    SysUser findUserById(Long id);

    SysUser findUser(String account, String password);

    Result getUserInfoByToken(String token);

    SysUser findUserByAccount(String account);
}
