package com.example.SecurityDemo.config;
import com.example.SecurityDemo.domain.SysRole;
import com.example.SecurityDemo.domain.SysUser;
import com.example.SecurityDemo.service.UserService;
import com.example.SecurityDemo.service.sysroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zfx
 * @date ：Created in 2020/7/6 9:17
 * @description：
 * @modified By：
 * @version: $
 */

public class Service {
    /**
     * @Author: zhoufengxi
     * @Description: 实现UserDetails完成用户认证或赋予角色权限
     * @Date Create in 2020/7/614:36
     */
    public static class UserDetailsServiceImpl implements UserDetailsService {
        @Autowired
        private UserService sysUserService;
        @Autowired
        private sysroleService sysroleService;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            if (username == null || "".equals(username)) {
                throw new RuntimeException("用户不能为空");
            }
            //根据用户名查询用户
            SysUser sysUser = sysUserService.selectByName(username);
            if (sysUser == null) {
                throw new RuntimeException("用户不存在");
            }
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            if (sysUser != null) {
                //获取该用户所拥有的权限
                List<SysRole> sysPermissions=sysroleService.getSysRolesByUserId(sysUser.getId());
                // 声明用户授权
                sysPermissions.forEach(sysPermission -> {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getName());
                    grantedAuthorities.add(grantedAuthority);
                });
            }

            User user;
            user = new User(sysUser.getUsername(), sysUser.getPassword(), true, true, true, true,       grantedAuthorities);
            return user;

       }
    }
}
