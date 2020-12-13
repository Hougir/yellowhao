package com.powernode.springbootdemo.controller;

import com.powernode.springbootdemo.commonutils.ResultEnum;
import com.powernode.springbootdemo.commonutils.YellowHaoException;
import com.powernode.springbootdemo.commonutils.wx.ConstantPropertiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @PACKAGE_NAME: com.powernode.springbootdemo.controller
 * @NAME: TestController
 * @AUTHOR: 如意郎君
 * @DATE: 2020/9/26
 * @TIME: 19:28
 * @DAY_NAME_SHORT: 星期六
 * @VERSION: 1.0
 */

@Controller
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "success";
    }

    @GetMapping("/login")
    public String login(){
        return "wx_login";
    }

    public static void main(String[] args) {
        String redirectUrl = "http://www.guli.shop:80/api/ucenter/wx/callback"; //获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            throw new YellowHaoException(ResultEnum.FAILED);
        }

        System.out.println(redirectUrl);
    }
}
