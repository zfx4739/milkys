package com.example.SecurityDemo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecurityDemo.domain.Member;
import com.example.SecurityDemo.domain.SysUser;
import com.example.SecurityDemo.service.UserService;
import com.example.SecurityDemo.util.PageRequest;
import com.example.SecurityDemo.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 用户顾客信息表 前端控制器
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */

@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
  private UserService userService;

    @ApiOperation("查询用户信息")
    @GetMapping("/getList")
    public Result getList(){
        Result result=new Result();
        QueryWrapper<SysUser> que=new QueryWrapper<SysUser>();
        que.orderByDesc("id");
        List<SysUser> memberss=userService.findAll();
        result.setData(memberss);
        result.setSuccess("获取成功！");
        return result;
    }

    /**
    * @description 分页查询用户信息
    *@params  
    * @return
    * @author  zfx
    * @date  2020/6/30 16:20
    *
    */
    @ApiOperation("分页查询用户信息")
    @GetMapping("listUser")
    public Result listUser(PageRequest pageRequest, Member member){
        Result result=new Result();
        QueryWrapper<SysUser> wrapper = new QueryWrapper();
        //排序方式
        wrapper.orderByDesc("id");
        Page<SysUser> page = new Page<SysUser>(pageRequest.getPageNum(),pageRequest.getPageSize());
        IPage<SysUser> mapIpage = userService.GetUserList(page, wrapper);
        List<SysUser> list = mapIpage.getRecords();
        result.setData(mapIpage.getRecords());
        result.setPages(mapIpage.getPages());
        result.setTotal(mapIpage.getTotal());
        result.setSize(mapIpage.getSize());
        result.setCurrent(mapIpage.getCurrent());
        result.setPages(mapIpage.getPages());
        result.setData(mapIpage.getRecords());
        result.setCode(Result.RESULT_SUCCESS);
        result.setMessage("RESULT_SUCCESS");
        return result;
    }

    /**
    * @description   添加用户信息
    *@params
    * @return
    * @author  zfx
    * @date  2020/6/30 16:23
    */
    @ApiOperation("添加用户信息")
    @PostMapping("addUser")
    public Result addUser(@Valid SysUser user, BindingResult bindingResult){
        Result result=new Result();
        if( bindingResult.hasErrors()){
            String messages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .reduce((m1, m2) -> m1 + "；" + m2)
                    .orElse("参数输入有误！");
            throw new IllegalArgumentException(messages);
        }
        int count=userService.addUser(user);
        if(count!=1){
            result.setMessage("添加失败！");
            result.setCode(Result.RESULT_ERROR);
            return result;
        }
        result.setMessage("添加成功！");
        result.setCode(Result.RESULT_SUCCESS);
        return result;
    }

    /**
     * @description  修改会员信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:08
     *
     */
    @ApiOperation("修改会员信息")
    @PostMapping("/updateUser")
    public Result updateUser(SysUser user){
        Result result=new Result();
        int count=userService.updUser(user);
        if(count!=1){
            result.setMessage("修改失败！");
            result.setCode(Result.RESULT_ERROR);
            return result;
        }
        result.setMessage("修改成功！");
        result.setCode(Result.RESULT_SUCCESS);
        return result;
    }

    /**
     * @description  删除会员信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:11
     *
     */
    @ApiOperation("删除会员信息")
    @GetMapping("/deleteUserr")
    public Result deleteUserr(int id){
        Result result=new Result();
        int count=userService.delUser(id);
        if(count!=1){
            result.setMessage("删除失败！");
            result.setCode(Result.RESULT_ERROR);
            return result;
        }
        result.setMessage("删除成功！");
        result.setCode(Result.RESULT_SUCCESS);
        return result;
    }

    /**
     * @description  获取用户详情
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/30 11:14
     *
     */
    @ApiOperation("获取用户详情")
    @GetMapping("/detailUser")
    public Result detailUser(int id){
        Result result=new Result();
        SysUser user=userService.detali(id);
        result.setCode(Result.RESULT_SUCCESS);
        result.setMessage("操作成功");
        result.setData(user);
        return result;
    }
}

