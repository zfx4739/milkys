package com.example.SecurityDemo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecurityDemo.domain.Store;
import com.example.SecurityDemo.service.storeService;
import com.example.SecurityDemo.util.PageRequest;
import com.example.SecurityDemo.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
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
 * 门店信息表 前端控制器
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */

@RestController
@RequestMapping("/store")
//@PreAuthorize("hasAnyRole('5')")
public class StoreController {
    @Autowired
    private storeService storeService;

    @ApiOperation("获取门店列表")
    @GetMapping("/getList")
    public Result getList(){
        Result result=new Result();
        QueryWrapper<Store> que=new QueryWrapper<Store>();
        que.orderByDesc("id");
        List<Store> members=storeService.list(que);
        List<Store> memberss=storeService.findAll();
        result.setData(memberss);
        result.setSuccess("获取成功！");
        return result;
    }

    /**
     * @description 分页查询门店信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/30 16:20
     *
     */
    @ApiOperation("分页查询门店信息")
    @GetMapping("listStore")
    public Result listStore(PageRequest pageRequest, Store store){
        Result result=new Result();
        QueryWrapper<Store> wrapper = new QueryWrapper();
        //排序方式
        wrapper.orderByDesc("id");
        Page<Store> page = new Page<>(pageRequest.getPageNum(),pageRequest.getPageSize());
        IPage<Store> mapIPage = storeService.GetStoreList(page, wrapper);
        List<Store> list = mapIPage.getRecords();
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
     * @description   添加门店信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/30 16:23
     */
    @ApiOperation("添加门店信息")
    @PostMapping("addStore")
    public Result addStore(@Valid Store store, BindingResult bindingResult){
        Result result=new Result();
        if( bindingResult.hasErrors()){
            String messages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .reduce((m1, m2) -> m1 + "；" + m2)
                    .orElse("参数输入有误！");
            throw new IllegalArgumentException(messages);
        }
        int count=storeService.addStore(store);
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
    @ApiOperation("修改门店信息")
    @PostMapping("/updateStore")
    public Result updateStore(Store store){
        Result result=new Result();
        int count=storeService.updStore(store);
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
     * @description  删除门店信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:11
     *
     */
    @ApiOperation("删除门店信息")
    @GetMapping("/deleteStore")
    public Result deleteStore(int id){
        Result result=new Result();
        int count=storeService.delStore(id);
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
     * @description  获取门店详情
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/30 11:14
     *
     */
    @ApiOperation("获取门店详情")
    @GetMapping("/detailStore")
    public Result detailStore(int id){
        Result result=new Result();
        Store user=storeService.detali(id);
        result.setCode(Result.RESULT_SUCCESS);
        result.setMessage("操作成功");
        result.setData(user);
        return result;
    }

}

