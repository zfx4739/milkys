package com.example.SecurityDemo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecurityDemo.domain.Product;
import com.example.SecurityDemo.domain.Specification;
import com.example.SecurityDemo.dto.SpecificationDto;
import com.example.SecurityDemo.service.productService;
import com.example.SecurityDemo.service.specificationService;
import com.example.SecurityDemo.util.PageRequest;
import com.example.SecurityDemo.util.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * 产品信息表 前端控制器
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
//Swagger@ApiOperation()接口注释


@RequestMapping("/product")
public class productController {

    @Autowired
   private  productService  productservice;
    //规格
    @Autowired
    private specificationService specificationService;
/**
* @description 获取树形数据(小程序)
*@params
* @return
* @author  zfx
* @date  2020/7/1 15:20
*
*/@ApiOperation("获取商品树形数据")
    @GetMapping("/wx/getList")
    public Result getList(){
        Result result=new Result();
        QueryWrapper<Product> que=new QueryWrapper<Product>();
        que.eq("parents",0);
        List<Product> plits=productservice.list(que);
        if(plits.size()>0){
            for (Product p:plits){
                QueryWrapper<Product> que1=new QueryWrapper<Product>();
                que1.eq("parents",p.getId());
                List<Product> plits2=productservice.list(que1);
                for (Product p2:plits2){
                    //添加自定义规格
                            String[]  arr=p2.getSpecificationid().split(",");
                            List<Specification> Lists=new ArrayList<Specification>();
                            List<SpecificationDto> Listspd=new ArrayList<SpecificationDto>();
                            for (int i = 0; i < arr.length; i++) {
                                System.err.println("---------------------"+arr[i]);
                                QueryWrapper<Specification> q=new QueryWrapper<Specification>();
                                q.eq("type",arr[i]);
                                //规格参数
                                List<Specification>  spLists=specificationService.list(q);
                                if(spLists.size()>0){
                                    Lists.addAll(spLists);
                                    SpecificationDto s=new SpecificationDto();
                                    System.err.println("pei"+spLists.get(0).getCustom());
                                    s.setCustom(spLists.get(0).getCustom());
                                    s.setIsmultiple(spLists.get(0).getIsmultiple());
                                    s.setListspf(spLists);
                                    Listspd.add(s);
                                      }
                                }
                            if(Lists.size()>0){
                                p2.setSpecifications(Listspd);
                            }
                     }
                //子集
                if(plits2.size()>0){
                    p.setProgs(plits2);
                }
            }
        }
        result.setCode(1);
        result.setMessage("获取成功！");
        result.setData(plits);
        return result;
    }

    /**
     * 根据名字查询商品(小程序)
     * @param name
     * @return
     */

    @GetMapping("/wx/queryProduct")
    public Result queryProduct(String name){
        Result result=new Result();
        List<Product> ProList=productservice.queryProduct(name);
        result.setCode(1);
        result.setData(ProList);
        result.setMessage("数据获取成功！");
        return result;
    }

    /**
     * @description 分页查询产品信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/30 16:20
     *
     */
    @GetMapping("listProduct")
    public Result listProduct(PageRequest pageRequest, Product product){
        Result result=new Result();
        QueryWrapper<Product> wrapper = new QueryWrapper<Product>();
        //排序方式
        wrapper.orderByDesc("id");
        //wrapper.eq("name",product.getName());
        Page<Product> page = new Page<Product>(pageRequest.getPageNum(),pageRequest.getPageSize());
        IPage<Product> mapIPage = productservice.GetProductList(page, wrapper);
        List<Product> list = mapIPage.getRecords();
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
     * @description   添加产品信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/30 16:23
     */
    @ApiOperation("添加产品信息信息")
    @PostMapping("addProduct")
    public Result addProduct(@Valid Product product, BindingResult bindingResult){
        Result result=new Result();
        if( bindingResult.hasErrors()){
            String messages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .reduce((m1, m2) -> m1 + "；" + m2)
                    .orElse("参数输入有误！");
            throw new IllegalArgumentException(messages);
        }
        int count=productservice.addProduct(product);
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
     * @description  修改产品信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:08
     *
     */
    @ApiOperation("修改产品信息")
    @PostMapping("/updateProduct")
    public Result updateProduct(@ApiParam("商品实体类") Product product){
        Result result=new Result();
        int count=productservice.updProdect(product);
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
     * @description  删除产品信息
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:11
     *
     */
    @ApiOperation("删除产品信息")
    @GetMapping("/deleteProduct")
    public Result deleteProduct(int id){
        Result result=new Result();
        int count=productservice.delProduct(id);
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
     * @description  获取产品详情
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/30 11:14
     *
     */
    @ApiOperation("获取产品详情")
    @GetMapping("/detailProduct")
    public Result detailProduct(int id){
        Result result=new Result();
        Product product=productservice.detali(id);
        result.setCode(Result.RESULT_SUCCESS);
        result.setMessage("操作成功");
        result.setData(product);
        return result;
    }
}

