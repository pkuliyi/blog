package com.liyi.blog.hander;

import com.alibaba.fastjson.JSON;
import com.liyi.blog.pojo.SysUser;
import com.liyi.blog.service.LoginService;
import com.liyi.blog.vo.ErrorCode;
import com.liyi.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//登陆拦截器未登录就拦截
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    LoginService loginService;

    //在执行controller方法(Handler）之前进行执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //在执行controller 方法(Handler）之前进行执行
        /**
        *1。 需要断 请求的接口路经 是否为 HandlerMethod (controller 方法）
        *2。 判街 token是否为空，如果为空 未登录
        *3。 如果token 不为空，登录验证 loginService checkToken
        *4。 如果认证成功 放行即可*/

//        Handler ： 绑定了注解@RequestMapping和@Controller的类
//        HandlerMethod：就是Handler下某个绑定@RequestMapping注解的方法（GetMapping、PostMapping...等都绑定的有注解@RequestMapping，spring mvc在做注解解析处理生成代理对象等的时候，会做值的合并等处理，所以最终都是用RequestMapping的注解来计算，所以@Controller和@RestController的处理等同）
//handler 可能是 RequestResourceHandler springboot 程序 坊问静态资源 默认去classpath下的static目录去查询

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String token = request.getHeader("Authorization");


        //日志
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");


        if(StringUtils.isBlank(token)){
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null){
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(),ErrorCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        return true;
    }
}
