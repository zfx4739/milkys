package com.example.SecurityDemo;
import com.example.SecurityDemo.util.WechatAuthProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.web.bind.annotation.Mapping;

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
@MapperScan("com.example.SecurityDemo.mapper")
public class SecurityDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecurityDemoApplication.class, args);
	}
}
