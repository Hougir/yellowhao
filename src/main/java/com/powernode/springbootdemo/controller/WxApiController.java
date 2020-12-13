package com.powernode.springbootdemo.controller;

import com.google.gson.Gson;
import com.powernode.springbootdemo.commonutils.ConstantWxUtils;
import com.powernode.springbootdemo.commonutils.R;
import com.powernode.springbootdemo.commonutils.ResultEnum;
import com.powernode.springbootdemo.commonutils.YellowHaoException;
import com.powernode.springbootdemo.commonutils.wx.ConstantPropertiesUtil;
import com.powernode.springbootdemo.commonutils.wx.HttpClientUtils;
import com.powernode.springbootdemo.entity.User;
import com.powernode.springbootdemo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @PACKAGE_NAME: com.powernode.springbootdemo.controller
 * @NAME: WxApiController
 * @AUTHOR: 如意郎君
 * @DATE: 2020/9/17
 * @TIME: 16:54
 * @DAY_NAME_SHORT: 星期四
 * @VERSION: 1.0
 */
@SuppressWarnings("all")
@CrossOrigin
@Controller//注意这里没有配置 @RestController
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String genQrConnect(HttpSession session) {

        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?self_redirect=%s" +
                "&id=%s" +
                "&appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "&href=%s" +
                "#wechat_redirect";
        boolean self_redirect = true;
        String id = "login_container";

        // 回调地址
        String redirectUrl = ConstantPropertiesUtil.WX_OPEN_REDIRECT_URL; //获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            throw new YellowHaoException(ResultEnum.FAILED);
        }

        // 防止csrf攻击（跨站请求伪造攻击）
        //String state = UUID.randomUUID().toString().replaceAll("-", "");//一般情况下会使用一个随机数
        String state = "atguigu";//为了让大家能够使用我搭建的外网的微信回调跳转服务器，这里填写你在ngrok的前置域名
        System.out.println("state = " + state);

        // 采用redis等进行缓存state 使用sessionId为key 30分钟后过期，可配置
        //键："wechar-open-state-" + httpServletRequest.getSession().getId()
        //值：satte
        //过期时间：30分钟

        System.out.println(redirectUrl);


        String href = "data:text/css;base64,LmltcG93ZXJCb3ggLnFyY29kZSB7d2lkdGg6IDIwMHB4O30NCi5pbXBvd2VyQm94IC50aXRsZSB7ZGlzcGxheTogbm9uZTt9DQouaW1wb3dlckJveCAuaW5mbyB7d2lkdGg6IDIwMHB4O30NCi5zdGF0dXNfaWNvbiB7ZGlzcGxheTogbm9uZX0NCi5pbXBvd2VyQm94IC5zdGF0dXMge3RleHQtYWxpZ246IGNlbnRlcjt9";
        //生成qrcodeUrl
        String qrcodeUrl = String.format(
                baseUrl,
                self_redirect,
                id,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                redirectUrl,
                state,
                href);

        return "redirect:" + qrcodeUrl;
    }


    //2 获取扫描人信息，添加数据
    @GetMapping("callback")
    public String callback(String code,HttpSession session,String state) {


        System.out.println("========callback======");

            //1 获取code值，临时票据，类似于验证码
            //2 拿着code请求 微信固定的地址，得到两个值 accsess_token 和 openid
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            //拼接三个参数 ：id  秘钥 和 code值
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    ConstantWxUtils.WX_OPEN_APP_SECRET,
                    code
            );
            //请求这个拼接好的地址，得到返回两个值 accsess_token 和 openid
            //使用httpclient发送请求，得到返回结果
        String accessTokenInfo = null;
        try {
            accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //从accessTokenInfo字符串获取出来两个值 accsess_token 和 openid
            //把accessTokenInfo字符串转换map集合，根据map里面key获取对应值
            //使用json转换工具 Gson
            Gson gson = new Gson();
            HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
            String access_token = (String)mapAccessToken.get("access_token");
            String openid = (String)mapAccessToken.get("openid");


            //3 拿着得到accsess_token 和 openid，再去请求微信提供固定的地址，获取到扫描人信息
            //访问微信的资源服务器，获取用户信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            //拼接两个参数
            String userInfoUrl = String.format(
                    baseUserInfoUrl,
                    access_token,
                    openid
            );
            //发送请求
        String userInfo = null;
        try {
            userInfo = HttpClientUtils.get(userInfoUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取返回userinfo字符串扫描人信息
            HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
            String nickname = (String)userInfoMap.get("nickname");//昵称
            String headimgurl = (String)userInfoMap.get("headimgurl");//头像
            Object sex = userInfoMap.get("sex");//性别

            /*System.out.println("nickname ===>" + nickname);
            System.out.println("headimgurl ===>" + headimgurl);
            System.out.println("openid ===>" + openid);*/

            session.setAttribute("nickname",nickname);
            session.setAttribute("headimgurl",headimgurl);
            session.setAttribute("sex",sex);



            /*User member = new User();
            member = new User();
            member.setOpenid(openid);
            member.setNickname(nickname);
            member.setAvatar(headimgurl);
            userService.save(member);*/


            //把扫描人信息添加数据库里面
            //判断数据表里面是否存在相同微信信息，根据openid判断
           /* User member = userService.getOpenIdMember(openid);
            if(member == null) {//memeber是空，表没有相同微信数据，进行添加

                //3 拿着得到accsess_token 和 openid，再去请求微信提供固定的地址，获取到扫描人信息
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                //拼接两个参数
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        access_token,
                        openid
                );
                //发送请求
                String userInfo = HttpClientUtils.get(userInfoUrl);
                //获取返回userinfo字符串扫描人信息
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String)userInfoMap.get("nickname");//昵称
                String headimgurl = (String)userInfoMap.get("headimgurl");//头像

                System.out.println("nickname ===>" + nickname);
                System.out.println("headimgurl ===>" + headimgurl);
                System.out.println("openid ===>" + openid);


                member = new User();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                userService.save(member);
            }*/

            //使用jwt根据member对象生成token字符串


            /*String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());*/


            //最后：返回首页面，通过路径传递token字符串
            /*return "redirect:http://localhost:8150?token="+jwtToken;*/


            System.out.println("成功");


            return "redirect:http://localhost:8150/test";

    }





}
