//package com.example.SecurityDemo.controller;
//import com.example.SecurityDemo.domain.Server;
//import com.example.SecurityDemo.util.AjaxResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 服务器监控
// *
// * @author zfx
// */
//@RestController
//@RequestMapping("/monitor")
//public class ServerController
//{
//   // @PreAuthorize("@ss.hasPermi('monitor:server:list')")
//    @GetMapping("/server")
//    public AjaxResult getInfo() throws Exception {
//        System.err.println("********************服务器监控**********************");
//        Server server = new Server();
//        server.copyTo()
//        ;
//        return AjaxResult.success(server);
//    }
//}
