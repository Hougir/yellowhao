package com.powernode.springbootdemo.commonutils.wx;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @PACKAGE_NAME: com.powernode.springbootdemo.commonutils.wx
 * @NAME: ConstantPropertiesUtil
 * @AUTHOR: 如意郎君
 * @DATE: 2020/9/17
 * @TIME: 16:53
 * @DAY_NAME_SHORT: 星期四
 * @VERSION: 1.0
 */

@Component
public class ConstantPropertiesUtil implements InitializingBean {
    @Value("${wx.open.app_id}")
    private String appId;

    @Value("${wx.open.app_secret}")
    private String appSecret;

    @Value("${wx.open.redirect_url}")
    private String redirectUrl;

    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_APP_SECRET;
    public static String WX_OPEN_REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_OPEN_APP_ID = appId;
        WX_OPEN_APP_SECRET = appSecret;
        WX_OPEN_REDIRECT_URL = redirectUrl;
    }


}
