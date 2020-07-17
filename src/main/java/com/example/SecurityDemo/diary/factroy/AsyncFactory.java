package com.example.SecurityDemo.diary.factroy;
import com.example.SecurityDemo.domain.SysLogininfor;
import com.example.SecurityDemo.service.SysLogininforService;
import com.example.SecurityDemo.util.*;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.TimerTask;

/**
 * @author ：zfx
 * @date ：Created in 2020/7/17 14:32
 * @description：
 * @modified By：
 * @version: $
 */
public class AsyncFactory
{


    /**
     * 记录登陆信息
     *
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return
     */
    public  Result recordLogininfor(final String username, final String status, final String message,
                                             final Object... args)
    {
        Result result=new Result();
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());

                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
              System.err.println(s.toString());
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLogininfor logininfor = new SysLogininfor();
                logininfor.setUser_name(username);
                logininfor.setIpaddr(ip);
                logininfor.setLogin_location(address);
                logininfor.setBrowser(browser);
                logininfor.setOs(os);
                logininfor.setMsg(message);
                // 日志状态
                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status))
                {
                    logininfor.setStatus(Constants.SUCCESS);
                }
                else if (Constants.LOGIN_FAIL.equals(status))
                {
                    logininfor.setStatus(Constants.FAIL);
                }
                // 插入数据
                System.err.println("");
                SpringUtils.getBean(SysLogininforService.class) .insertLogininfor(logininfor);
                System.err.println("插入日志2");
              result.setMessage("插入日志");
           return result;

    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
//    public static TimerTask recordOper(final SysOperLog operLog)
//    {
//        return new TimerTask()
//        {
//            @Override
//            public void run()
//            {
//                // 远程查询操作地点
//                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
//                SpringUtils.getBean(ISysOperLogService.class).insertOperlog(operLog);
//            }
//        };
//    }
}
