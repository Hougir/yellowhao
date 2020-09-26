package com.powernode.springbootdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.powernode.springbootdemo.commonutils.JwtUtils;
import com.powernode.springbootdemo.commonutils.ResultEnum;
import com.powernode.springbootdemo.commonutils.YellowHaoException;
import com.powernode.springbootdemo.entity.User;
import com.powernode.springbootdemo.mapper.UserMapper;
import com.powernode.springbootdemo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yellowhao
 * @since 2020-09-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    //登录的方法
    @Override
    public String login(User member) {
        //获取登录手机号和密码
        String name = member.getName();
        String password = member.getPassword();

        //手机号和密码非空判断
        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            throw new YellowHaoException(ResultEnum.FAILED);
        }

        //判断手机号是否正确
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        User mobileMember = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        if(mobileMember == null) {//没有这个账号
            throw new YellowHaoException(ResultEnum.NOT_FOUND_USER);
        }

        //判断密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式 MD5
        /*if(!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new YellowHaoException(ResultEnum.FAILED);
        }*/

        //判断用户是否禁用
        /*if(mobileMember.getIsDisabled()) {
            throw new YellowHaoException(ResultEnum.USER_LOCK);
        }*/

        //登录成功
        //生成token字符串，使用jwt工具类
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;
    }

    //根据openid判断
    @Override
    @Transactional
    public User getOpenIdMember(String openid) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        User member = baseMapper.selectOne(wrapper);
        return member;
    }
}
