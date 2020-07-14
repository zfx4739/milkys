package com.example.SecurityDemo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SecurityDemo.domain.SysUser;
import com.example.SecurityDemo.domain.WeChatModel;
import com.example.SecurityDemo.domain.WeChatSession;
import com.example.SecurityDemo.service.UserService;
import com.example.SecurityDemo.util.Result;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;
/**
 * @author ：zfx
 * @date ：Created in 2020/6/30 9:22
 * @description：
 * @modified By：
 * @version: $
 */

@RestController
@RequestMapping("/start")
public class LoginController {
    @Value("${auth.wechat.appId}")
    private  String  appId;
    @Value("${auth.wechat.secret}")
    private  String  secret;
    @Autowired
    private UserService userService;
    @ApiOperation("启动微信小程序登录方法")
    @GetMapping("/wx/login")
   //@PreAuthorize("hasAnyRole('admin')")
    public Result login(WeChatModel weChatModel) {
        Result result = new Result();
        String openid = null;
        String session_key = null;
        String errcode = null;
        String errmsg = null;
        String phone = null;
        WeChatSession weChatSession = null;
        //微信服务器的接口路径
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + weChatModel.getCode() + "&grant_type=authorization_code";
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
            System.err.println("获取会话秘钥:"+session_key);
            //获取错误码
            errcode = weChatSession.getErrcode();
            //获取错误信息
            errmsg = weChatSession.getErrmsg();
        }
      //  try {
            if (openid == null || session_key == null) {
                result.setError("errcode: " + errcode + ";errmsg: " + errmsg);
                return result;
            }
            //通过openid查询数据库是否有此用户
        SysUser user=userService.findOpenId(openid);
            if(user!=null){ //用户已存在
                if(user.getPhone()==null) {
                    weChatSession.setPhone("");
                }else {
                    weChatSession.setPhone(user.getPhone());
                }
                result.setCode(1);
                result.setMessage("登录成功！");
                result.setData(weChatSession);
                return result;
            }
        SysUser userin = new SysUser();
        userin.setOpenid(openid);
            userService.save(userin);
            QueryWrapper<SysUser> que=new QueryWrapper<SysUser>();
            que.eq("openid",openid);
            List<SysUser> newUser = userService.list(que);
            if(newUser.get(0).getPhone()==null) {
                weChatSession.setPhone("");
            }else {
                weChatSession.setPhone(user.getPhone());
            }
            result.setMessage("注册成功！");
            result.setData(weChatSession);
            return result;
//        } catch (Exception e) {
//            result.setError("用户login失败！ ");
//            return result;
//        }
    }
}
