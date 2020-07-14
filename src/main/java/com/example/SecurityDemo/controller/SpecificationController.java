package com.example.SecurityDemo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecurityDemo.domain.Specification;
import com.example.SecurityDemo.service.specificationService;
import com.example.SecurityDemo.util.PageRequest;
import com.example.SecurityDemo.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.util.List;
/**
 * <p>
 * 规格表 前端控制器
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */

@Controller
@RequestMapping("/specification")
public class SpecificationController {
    @Autowired
    private specificationService specificationService;

    @ApiOperation("获取规格列表")
    @GetMapping("/getList")
    public Result getList(){
        Result result=new Result();
        QueryWrapper<Specification> que=new QueryWrapper<Specification>();
        que.orderByDesc("id");
        List<Specification> members= specificationService.list(que);
        List<Specification> memberss=specificationService.findAll();
        result.setData(memberss);
        result.setSuccess("获取成功！");
        return result;
    }

    /**
     * @description 分页查询规格信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/30 16:20
     *
     */
    @ApiOperation("分页查询规格信息")
    @GetMapping("listSpecification")
    public Result listSpecification(PageRequest pageRequest, Specification specification){
        Result result=new Result();
        QueryWrapper<Specification> wrapper = new QueryWrapper();
        //排序方式
        wrapper.orderByDesc("id");
        Page<Specification> page = new Page<>(pageRequest.getPageNum(),pageRequest.getPageSize());
        IPage<Specification> mapIPage = specificationService.GetSpecificationList(page, wrapper);
        List<Specification> list = mapIPage.getRecords();
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
     * @description   添加规格信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/30 16:23
     */
    @ApiOperation("添加规格信息")
    @PostMapping("addSpecification")
    public Result addSpecification(@Valid Specification specification, BindingResult bindingResult){
        Result result=new Result();
        if( bindingResult.hasErrors()){
            String messages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .reduce((m1, m2) -> m1 + "；" + m2)
                    .orElse("参数输入有误！");
            throw new IllegalArgumentException(messages);
        }
        int count=specificationService.addSpecification(specification);
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
     * @description  修改规格信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:08
     *
     */
    @ApiOperation("修改规格信息")
    @PostMapping("/updateSpecification")
    public Result updateSpecification(Specification specification){
        Result result=new Result();
        int count=specificationService.updSpecification(specification);
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
     * @description  删除规格信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:11
     *
     */
    @ApiOperation("删除规格信息")
    @GetMapping("/deleteSpecification")
    public Result deleteSpecification(int id){
        Result result=new Result();
        int count=specificationService.delspecificationService(id);
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
    @ApiOperation("获取门店规格信息")
    @GetMapping("/detailSpecification")
    public Result detailSpecification(int id){
        Result result=new Result();
        Specification specification=specificationService.detali(id);
        result.setCode(Result.RESULT_SUCCESS);
        result.setMessage("操作成功");
        result.setData(specification);
        return result;
    }
}

