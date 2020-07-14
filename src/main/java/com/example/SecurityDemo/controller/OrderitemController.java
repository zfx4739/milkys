package com.example.SecurityDemo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecurityDemo.domain.Orderitem;
import com.example.SecurityDemo.service.orderitemService;
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
 * 订单详情表：订单的详情主要就是购买商品的信息，通过订单的id来实现关联 前端控制器
 * </p>
 *
 * @author zfx
 * @since 2020-07-01
 */

@RestController
@RequestMapping("/orderitem")
public class OrderitemController {
    @Autowired
    private orderitemService orderitemservice;


    @ApiOperation("获取订单详情方法")
    @GetMapping("getList")
    public Result getList(){
        Result result=new Result();
        QueryWrapper<Orderitem> que=new QueryWrapper<Orderitem>();
        List<Orderitem> memberss=orderitemservice.findAll();
        result.setData(memberss);
        result.setSuccess("获取成功！");
        return result;
    }
    /**
     * @description  分页查询获取订单详情方法
     *@params  pageRequest  member
     * @return  import com.kanq.platform.test.util.Result;
     * @author  zfx
     * @date  2020/6/19 9:24
     *
     */
    @ApiOperation("分页查询获取订单详情方法")
    @GetMapping("listOrderitem")
    public Result listOrderitem(PageRequest pageRequest, Orderitem orderitem){
        Result result=new Result();
        QueryWrapper<Orderitem> wrapper = new QueryWrapper();
        //排序方式
        wrapper.orderByDesc("id");
        Page<Orderitem> page = new Page<Orderitem>(pageRequest.getPageNum(),pageRequest.getPageSize());
        IPage<Orderitem> mapIPage = orderitemservice.GetOrderitemList(page, wrapper);
        List<Orderitem> list = mapIPage.getRecords();
        result.setData(mapIPage.getRecords());
        result.setPages(mapIPage.getPages());
        result.setTotal(mapIPage.getTotal());
        result.setSize(mapIPage.getSize());
        result.setCurrent(mapIPage.getCurrent());
        result.setPages(mapIPage.getPages());
        result.setData(mapIPage.getRecords());
        result.setCode(Result.RESULT_SUCCESS);
        result.setMessage("RESULT_SUCCESS");
        return result;
    }

    /**
     * @description 添加会员信息
     *@params  Member member, BindingResult bindingResult
     * @return
     * @author  zfx
     * @date  2020/6/19 10:32
     *
     */
    @ApiOperation("添加订单信息详情方法")
    @PostMapping("addOrderitem")
    public Result addOrderitem(@Valid Orderitem orderitem, BindingResult bindingResult){
        Result result=new Result();
        if( bindingResult.hasErrors()){
            String messages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .reduce((m1, m2) -> m1 + "；" + m2)
                    .orElse("参数输入有误！");
            throw new IllegalArgumentException(messages);
        }
        int count= orderitemservice.addrderitem(orderitem);
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
     * @description  修改订单详情信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:08
     *
     */
    @ApiOperation("修改订单信息详情方法")
    @PostMapping("/updateOrderitem")
    public Result updateOrderitem(Orderitem orderitem){
        Result result=new Result();
        int count=orderitemservice.updOrderitem(orderitem);
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
     * @description  删除订单信息详情
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:11
     *
     */
    @ApiOperation("删除订单信息详情方法")
    @GetMapping("/deleteOrderitem")
    public Result deteleOrderitem(int id){
        Result result=new Result();
        int count=orderitemservice.delrderitem(id);
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
     * @description  获取订单详情
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:14
     *
     */
    @ApiOperation("获取订单详情方法（条件查询）")
    @GetMapping("/detailOrderitem")
    public Result detailOrderitem(int id){
        Result result=new Result();
        Orderitem meber=orderitemservice.detali(id);
        result.setCode(Result.RESULT_SUCCESS);
        result.setMessage("操作成功");
        result.setData(meber);
        return result;
    }

}

