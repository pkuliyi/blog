package com.liyi.blog.service;

import com.liyi.blog.pojo.SysUser;
import com.liyi.blog.vo.Result;
import com.liyi.blog.vo.params.LoginParam;

public interface LoginService {
    /**
     * 登录
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);

    SysUser checkToken(String token);

    Result logout(String token);

    Result register(LoginParam loginParam);
}
