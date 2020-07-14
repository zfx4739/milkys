package com.example.SecurityDemo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecurityDemo.domain.Orderitem;
import com.example.SecurityDemo.domain.Orderm;
import com.example.SecurityDemo.dto.AddOrderDto;
import com.example.SecurityDemo.service.orderitemService;
import com.example.SecurityDemo.service.ordermService;
import com.example.SecurityDemo.util.PageRequest;
import com.example.SecurityDemo.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 订单的基本信息 前端控制器
 * </p>
 *
 * @author zfx
 * @since 2020-07-01
 */

@RestController
@RequestMapping("/orderm")
public class OrdermController {
        @Autowired
    private ordermService ordermService;
    //订单详情
    @Autowired
    private orderitemService orderitemservice;

    @ApiOperation("查询订单方法")
    @GetMapping("getList")
    public Result getList(){
        Result result=new Result();
        QueryWrapper<Orderm> que=new QueryWrapper<Orderm>();
        que.orderByDesc("id");
        List<Orderm> orderms=ordermService.findAll();
        if(orderms.size()>0){
            for (Orderm p:orderms){
                QueryWrapper<Orderitem> que1=new QueryWrapper<Orderitem>();
                que1.eq("orderid",p.getOrderNumber());
                List<Orderitem> orderitems=orderitemservice.list(que1);
                //规格参数
                if(orderitems!=null){
                    p.setOrderitems(orderitems);
                }
            }
        }
        result.setData(orderms);
        result.setSuccess("获取成功！");
        return result;
    }
    /**
     * @description  分页查询订单方法
     *@params  pageRequest  member
     * @return  import com.kanq.platform.test.util.Result;
     * @author  zfx
     * @date  2020/6/19 9:24
     *
     */
    @ApiOperation("分页查询订单方法")
    @GetMapping("listOrderm")
    public Result listOrderm(PageRequest pageRequest, Orderm orderm){
        Result result=new Result();
        QueryWrapper<Orderm> wrapper = new QueryWrapper();
        //排序方式
        wrapper.orderByDesc("id");
        Page<Orderm> page = new Page<>(pageRequest.getPageNum(),pageRequest.getPageSize());
        IPage<Orderm> mapIPage = ordermService.GetOrdermList(page, wrapper);
        List<Orderm> list = mapIPage.getRecords();
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
     * @description 小程序添加订单信息
     *@params  Member member, BindingResult bindingResult
     * @return@RequestBody
     * @author  zfx
     * @date  2020/6/19 10:32
     *
     */
    @ApiOperation("小程序添加订单信息方法")
    @PostMapping("/wx/addOrderm")
    public Result WxaddOrderm(@RequestBody AddOrderDto addOrderDto, BindingResult bindingResult){
        Result result=new Result();
       // System.out.println(addOrderDto.());
        if( bindingResult.hasErrors()){
            String messages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .reduce((m1, m2) -> m1 + "；" + m2)
                    .orElse("参数输入有误！");
            throw new IllegalArgumentException(messages);
        }
        int count=ordermService.addOrderm(addOrderDto);

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
     * @description 添加订单信息
     *@params  Member member, BindingResult bindingResult
     * @return
     * @author  zfx
     * @date  2020/6/19 10:32
     *
     */
    @ApiOperation("添加订单信息方法")
    @PostMapping("addOrderm")
    public Result addOrderm(@Valid Orderm orderm, BindingResult bindingResult){
        Result result=new Result();
        if( bindingResult.hasErrors()){
            String messages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .reduce((m1, m2) -> m1 + "；" + m2)
                    .orElse("参数输入有误！");
            throw new IllegalArgumentException(messages);
        }
        int count=ordermService.addOrderm(orderm);

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
     * @description  修改订单信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:08
     *
     */
    @ApiOperation("修改订单信息方法")
    @PostMapping("/updateOrderm")
    public Result updateOrderm(Orderm orderm){
        Result result=new Result();
        int count=ordermService.updOrder(orderm);
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
     * @description  删除订单信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:11
     *
     */
    @ApiOperation("删除订单信息方法")
    @GetMapping("/deleteOrderm")
    public Result deteleOrderm(int id){
        Result result=new Result();
        int count=ordermService.delOrder(id);
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
    @ApiOperation("获取订单详情方法")
    @GetMapping("/detailOrderm")
    public Result detailOrderm(int id){
        Result result=new Result();
        Orderm meber=ordermService.detali(id);
        result.setCode(Result.RESULT_SUCCESS);
        result.setMessage("操作成功");
        result.setData(meber);
        return result;
    }
}

