package com.example.SecurityDemo.domain;
/**
 * @author ：zfx
 * @date ：Created in 2020/6/29 16:01
 * @description：
 * @modified By：
 * @version: $
 */

public class WeChatModel {
    private String code;
    private String avatarUrl;
    private String nickName;
    private String openId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}