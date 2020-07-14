package com.example.SecurityDemo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecurityDemo.domain.Price;
import com.example.SecurityDemo.service.priceService;
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
 * 价位表 前端控制器
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */

@RestController
@RequestMapping("/price")
public class PriceController {
    @Autowired
    private priceService priceService;
    @ApiOperation("获取价位列表")
    @GetMapping("/getList")
    public Result getList(){
        Result result=new Result();
        List<Price> proList=priceService.findAll();
        result.setMessage("获取成功！");
        result.setData(proList);
        return result;
    }

    /**
     * @description 分页查询产品价格信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/30 16:20
     *
     */

    @ApiOperation("分页查询产品价格信息方法")
    @GetMapping("listPrice")
    public Result listPrice(PageRequest pageRequest, Price price){
        Result result=new Result();
        QueryWrapper<Price> wrapper = new QueryWrapper<Price>();
        //排序方式
        wrapper.orderByDesc("id");
        Page<Price> page = new Page<Price>(pageRequest.getPageNum(),pageRequest.getPageSize());
        IPage<Price> mapIPage = priceService.GetPriceList(page, wrapper);
        List<Price> list = mapIPage.getRecords();
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
     * @description   添加产品价格信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/30 16:23
     */
    @ApiOperation("添加产品价格信息")
    @PostMapping("addPrice")
    public Result addPrice(@Valid Price price, BindingResult bindingResult){
        Result result=new Result();
        if( bindingResult.hasErrors()){
            String messages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .reduce((m1, m2) -> m1 + "；" + m2)
                    .orElse("参数输入有误！");
            throw new IllegalArgumentException(messages);
        }
        int count=priceService.addPrice(price);
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
     * @description  修改产品价格信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:08
     *
     */
    @ApiOperation("修改产品价格信息")
    @PostMapping("/updatePrice")
    public Result updatePrice(Price price){
        Result result=new Result();
        int count=priceService.updPrice(price);
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
     * @description  删除产品价格信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:11
     *
     */
    @ApiOperation("删除产品价格信息")
    @GetMapping("/deletePrice")
    public Result deletePrice(int id){
        Result result=new Result();
        int count=priceService.delPrice(id);
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
     * @description  获取价位价格详情
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/30 11:14
     *
     */
    @ApiOperation("获取价位价格详情")
    @GetMapping("/detailPrice")
    public Result detailPrice(int id){
        Result result=new Result();
        Price price=priceService.detali(id);
        result.setCode(Result.RESULT_SUCCESS);
        result.setMessage("操作成功");
        result.setData(price);
        return result;
    }
}

