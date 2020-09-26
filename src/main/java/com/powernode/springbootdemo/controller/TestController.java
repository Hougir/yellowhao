package com.powernode.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
