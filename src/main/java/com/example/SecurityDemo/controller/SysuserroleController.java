package com.example.SecurityDemo.controller;
import com.example.SecurityDemo.service.sysuserroleService;
import com.example.SecurityDemo.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zfx
 * @since 2020-07-07
 */

@RestController
@RequestMapping("/sysUserRoles")
public class SysuserroleController {
    @Autowired
    private sysuserroleService sysuserroleService;

    /**
     * 获取用户角色中间表数据
     * @return
     */
    @ApiOperation("获取用户角色中间表数据")
    @GetMapping("getList")
    public Result getList(){
        Result result=new Result();
        result.setData(sysuserroleService.list());
        result.setMessage("获取成功");
        result.setCode(1);
        return  result;
    }


}

