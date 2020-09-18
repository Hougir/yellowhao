package com.powernode.springbootdemo.service;

import com.powernode.springbootdemo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yellowhao
 * @since 2020-09-16
 */
public interface UserService extends IService<User> {
    //登录的方法
    String login(User user);



    //根据openid判断
    User getOpenIdMember(String openid);
}
