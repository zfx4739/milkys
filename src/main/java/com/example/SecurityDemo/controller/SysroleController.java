package com.example.SecurityDemo.controller;
import com.example.SecurityDemo.domain.SysRole;
import com.example.SecurityDemo.service.sysroleService;
import com.example.SecurityDemo.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zfx
 * @since 2020-07-07
 */

@RestController
@RequestMapping("/sysRole")
public class SysroleController {
@Autowired
   private sysroleService sysroleService;

/**
* @description 
*@params  获取角色列表
* @return
* @author  zfx
* @date  2020/7/7 15:38
*
*/
    @ApiOperation("获取角色列表")
    @GetMapping("getRoleList")
    public Result getRoleList(){
        Result result=new Result();
        List<SysRole> sysRoleList=  sysroleService.findAll();
            result.setData(sysRoleList);
        result.setCode(1);
        result.setMessage("获取成功");
        return result;
    }
}

