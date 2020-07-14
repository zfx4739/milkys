package com.example.SecurityDemo.controller;
import com.example.SecurityDemo.domain.SysUser;
import com.example.SecurityDemo.domain.WeChatModel;
import com.example.SecurityDemo.domain.WeChatSession;
import com.example.SecurityDemo.util.EmptyUtil;
import com.example.SecurityDemo.util.Result;
import com.example.SecurityDemo.util.WechatAuthProperties;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
/**
 * @author ：zfx
 * @date ：Created in 2020/6/29 9:59
 * @description：doLogin
 * @modified By：
 * @version: 1.0$
 */

@RestController
@RequestMapping("/start")
public class DoLoginController {

    private static final String APPID= "wx40319bc835462cfe";
    private static final String SECRET = "79c83972e6770e4817155·2ba1b32550b";
    @Value("${auth.wechat.appId}")
    private  String  aa;
    @GetMapping("/logins")
//    @PreAuthorize("hasAnyRole('normal','admin')")
    public Result login(@RequestBody WeChatModel weChatModel) {
        System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+aa);
        Result result = new Result();
        WechatAuthProperties wxUtil=new WechatAuthProperties();
        String openid = null;
        String session_key = null;
        String errcode = null;
        String errmsg = null;
        WeChatSession weChatSession;

        //微信服务器的接口路径
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + wxUtil.getAppId() + "&secret=" + wxUtil.getSecret() + "&js_code=" + weChatModel.getCode() + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        //进行网络请求,访问微信服务器接口
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        //根据返回值进行后续操作

        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            String sessionData = responseEntity.getBody();
            Gson gson = new Gson();
            //将json字符串转化为实体类;
            weChatSession = gson.fromJson(sessionData, WeChatSession.class);
            //获取用户的唯一标识
            openid = weChatSession.getOpenid();
            //获取会话秘钥（具有时效性，用户登录的临时通行证）
            session_key = weChatSession.getSession_key();
            //获取错误码
            errcode = weChatSession.getErrcode();
            //获取错误信息
            errmsg = weChatSession.getErrmsg();
        }
        try {
            if (openid == null || session_key == null) {
                result.setError("errcode: " + errcode + ";errmsg: " + errmsg);
                return result;
            }
        //    UserModel user = userService.login(openid);
            SysUser user=new SysUser();
            if(EmptyUtil.isEmpty(user)){
                weChatModel.setOpenId(openid);
                //boolean flag = userService.insertUser(weChatModel);
                boolean flag = true;
                if(flag == true){
                    result.setData(weChatModel);
                    return result;
                }
                else {
                    result.setError("用户login失败！ ");
                    return result;
                }
            }
            else{
                result.setData(user);
                return result;
            }
        } catch (Exception e) {
            result.setError("用户login失败！ ");
            return result;
        }
        }
}
