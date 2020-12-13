package com.powernode.springbootdemo.controller;

import com.powernode.springbootdemo.commonutils.JwtUtils;
import com.powernode.springbootdemo.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @PACKAGE_NAME: com.powernode.springbootdemo.controller
 * @NAME: LoginController
 * @AUTHOR: 如意郎君
 * @DATE: 2020/12/6
 * @TIME: 11:21
 * @DAY_NAME_SHORT: 星期日
 * @VERSION: 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("api/vue-shop/")
public class LoginController {


    @RequestMapping("/login")
    public R login(@RequestParam(value = "username") String username,
                   @RequestParam(value = "password") String password){

        System.out.println("username===>" + username);
        if (username.equals("admin") && password.equals("123456")) {

            return R.ok().data("成功","success").data("token", JwtUtils.getJwtToken("1",username));
        }


        return R.error();
    }
}
