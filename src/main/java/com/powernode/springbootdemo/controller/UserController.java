package com.powernode.springbootdemo.controller;


import com.powernode.springbootdemo.commonutils.R;
import com.powernode.springbootdemo.commonutils.ResultEnum;
import com.powernode.springbootdemo.commonutils.YellowHaoException;
import com.powernode.springbootdemo.entity.User;
import com.powernode.springbootdemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yellowhao
 * @since 2020-09-16
 */
@Api(description = "用户管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;



    @ApiOperation(value = "查询所有用户")
    @GetMapping("/list")
    public R list(){
        List<User> list = userService.list(null);
        Map map = new HashMap();
        map.put("user",list);
        return R.ok().data(map);
    }


    @ApiOperation(value = "根据id删除用户")
    @Transactional
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable("id") Integer id){
        try {
            /*int i = 10 / 0;*/
            userService.removeById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new YellowHaoException(ResultEnum.FAILED);
        }
        return R.ok().message(ResultEnum.SUCCESS);
    }

    @ApiOperation(value = "添加用户")
    @Transactional
    @PostMapping("/insert")
    public R insert(@RequestBody User user){
        try {
            userService.save(user);
        } catch (Exception e) {
            throw new YellowHaoException(ResultEnum.ADD_FATLED);
        }
        return R.ok().message(ResultEnum.SUCCESS);
    }

    @ApiOperation(value = "修改用户")
    @Transactional
    @PostMapping("/updata")
    public R updata(@RequestBody User user){

        System.out.println(user);
        try {
            userService.updateById(user);
        } catch (Exception e) {
            throw new YellowHaoException(ResultEnum.ADD_FATLED);
        }
        return R.ok().message(ResultEnum.SUCCESS);
    }


    @ApiOperation(value = "获取登陆者信息")
    @GetMapping("/getUser")
    public R getUser(HttpSession session){
        String nickname = (String)session.getAttribute("nickname");
        String headimgurl = (String)session.getAttribute("headimgurl");
        Object sex1 = session.getAttribute("sex");
        Integer sex = (Integer)sex1;

        String userSex = "";
        if (sex == 1){
            userSex = "男";
        }else if (sex == 1){
            userSex = "女";
        }else {
            userSex = "泰";
        }


        Map map = new HashMap();
        map.put("nickname",nickname);
        map.put("headimgurl",headimgurl);
        map.put("sex",userSex);

        return R.ok().data(map).message(ResultEnum.SUCCESS);
    }





}

