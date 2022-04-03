package com.liyi.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyi.blog.pojo.SysUser;
import com.liyi.blog.service.LoginService;
import com.liyi.blog.service.SysUserService;
import com.liyi.blog.mapper.SysUserMapper;
import com.liyi.blog.vo.ErrorCode;
import com.liyi.blog.vo.LoginUserVo;
import com.liyi.blog.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author liyi
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2022-03-23 21:48:13
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    LoginService loginService;

    @Override
    public SysUser findUserById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if(sysUser==null){
            sysUser = new SysUser();
            sysUser.setNickname("未知");
        }
        return sysUser;
    }

    @Override
    public SysUser findUser(String account, String password) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getAccount,account);
        lambdaQueryWrapper.eq(SysUser::getPassword,password);
        lambdaQueryWrapper.select(SysUser::getId,SysUser::getAccount,SysUser::getAvatar,SysUser::getNickname);
        SysUser sysUser = sysUserMapper.selectOne(lambdaQueryWrapper);
        return sysUser;
    }

    @Override
    public Result getUserInfoByToken(String token) {

        SysUser sysUser = loginService.checkToken(token);

        if(sysUser==null){
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(),ErrorCode.TOKEN_ERROR.getMsg());
        }

        LoginUserVo loginUserVo = new LoginUserVo();

        BeanUtils.copyProperties(sysUser,loginUserVo);

        return Result.success(loginUserVo);

    }

    @Override
    public SysUser findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getAccount,account);
        SysUser sysUser = sysUserMapper.selectOne(lambdaQueryWrapper);
        return sysUser;
    }

}




