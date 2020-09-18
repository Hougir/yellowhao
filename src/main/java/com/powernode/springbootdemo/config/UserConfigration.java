package com.powernode.springbootdemo.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @PACKAGE_NAME: com.powernode.springbootdemo.config
 * @NAME: UserConfigration
 * @AUTHOR: 如意郎君
 * @DATE: 2020/9/16
 * @TIME: 14:43
 * @DAY_NAME_SHORT: 星期三
 * @VERSION: 1.0
 *
 *
 *配置插件
 *
 */

@Configuration
@EnableTransactionManagement
@MapperScan("com.powernode.springbootdemo.mapper")
public class UserConfigration {
    /**
     * 逻辑删除插件
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }




}
