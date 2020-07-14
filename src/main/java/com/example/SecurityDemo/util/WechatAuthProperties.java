package com.example.SecurityDemo.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ：zfx
 * @date ：Created in 2020/6/29 16:58
 * @description：
 * @modified By：
 * @version: $
 */
//交给Spring容器配置类
@Component
@Data
public class WechatAuthProperties {
//    @Value("${auth.wechat.sessionHost}")
//    private String sessionHost;
    @Value("${auth.wechat.appId}")
    private String appId;
    @Value("${auth.wechat.secret}")
    private String secret;
//    @Value("${auth.wechat.grantType}")
    private String grantType;
    //省略getter setter


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}