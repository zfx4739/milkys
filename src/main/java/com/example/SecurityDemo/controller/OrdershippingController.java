package com.example.SecurityDemo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecurityDemo.domain.Ordershipping;
import com.example.SecurityDemo.service.ordershippingService;
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
 * 订单配送表 前端控制器
 * </p>
 *
 * @author zfx
 * @since 2020-07-01
 */

@RestController
@RequestMapping("/ordershipping")
public class OrdershippingController {
    @Autowired
    private ordershippingService ordershippingservice;

    @ApiOperation("查询订单地址信息方法")
    @GetMapping("getList")
    public Result getList(){
        Result result=new Result();
        QueryWrapper<Ordershipping> que=new QueryWrapper<Ordershipping>();
        List<Ordershipping> ordershipp=ordershippingservice.findAll();
        result.setData(ordershipp);
        result.setSuccess("获取成功！");
        return result;
    }
    /**
     * @description  分页查询订单地址信息方法
     *@params  pageRequest  member
     * @return  import com.kanq.platform.test.util.Result;
     * @author  zfx
     * @date  2020/6/19 9:24
     *
     */
    @ApiOperation("分页查询订单地址信息方法")
    @GetMapping("listOrdershipping")
    public Result listOrdershipping(PageRequest pageRequest, Ordershipping ordershipping){
        Result result=new Result();
        QueryWrapper<Ordershipping> wrapper = new QueryWrapper();
        //排序方式
        wrapper.orderByDesc("id");
        Page<Ordershipping> page = new Page<Ordershipping>(pageRequest.getPageNum(),pageRequest.getPageSize());
        IPage<Ordershipping> mapIPage = ordershippingservice.GetOrdershippingList(page, wrapper);
        List<Ordershipping> list = mapIPage.getRecords();
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
     * @description 添加订单地址信息方法
     *@params  Member member, BindingResult bindingResult
     * @return
     * @author  zfx
     * @date  2020/6/19 10:32
     *
     */
    @ApiOperation("添加订单地址信息方法")
    @PostMapping("addOrdershipping")
    public Result addOrdershipping(@Valid Ordershipping ordershipping, BindingResult bindingResult){
        Result result=new Result();
        if( bindingResult.hasErrors()){
            String messages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .reduce((m1, m2) -> m1 + "；" + m2)
                    .orElse("参数输入有误！");
            throw new IllegalArgumentException(messages);
        }
        int count= ordershippingservice.addOrdershipping(ordershipping);
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
     * @description  修改订单地址信息方法
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:08
     *
     */
    @ApiOperation("修改订单地址信息方法")
    @PostMapping("/updateOrdershipping")
    public Result updateOrdershipping(Ordershipping ordershipping){
        Result result=new Result();
        int count=ordershippingservice.updOrdershipping(ordershipping);
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
     * @description  删除订单地址信息方法
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:11
     *
     */
    @ApiOperation("删除订单地址信息方法")
    @GetMapping("/deleteOrdershipping")
    public Result deteleOrdershipping(int id){
        Result result=new Result();
        int count=ordershippingservice.delOrdershipping(id);
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
     * @description  获取订单地址详情方法
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:14
     *
     */
    @ApiOperation("获取订单地址详情方法")
    @GetMapping("/detailOrdershipping")
    public Result detailOrdershipping(int id){
        Result result=new Result();
        Ordershipping meber=ordershippingservice.detali(id);
        result.setCode(Result.RESULT_SUCCESS);
        result.setMessage("操作成功");
        result.setData(meber);
        return result;
    }

}

