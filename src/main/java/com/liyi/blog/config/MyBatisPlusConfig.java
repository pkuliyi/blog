package com.liyi.blog.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//扫描mapper文件夹
@MapperScan("com.liyi.blog.mapper")//交给mybatis做的，可以让这个配置类做扫描
@EnableTransactionManagement//自动管理事务
@Configuration//配置类
public class MyBatisPlusConfig {

    //注册乐观锁插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();

        //注册乐观锁插件
        //mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        //分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }

    //逻辑删除组件
    //高版本不用配置插件了，上述java代码无需编写。
    //    @Bean
    //    public ISqlInjector sqlInjector(){
    //        return new LogicSqlInjector();
    //    }

}
