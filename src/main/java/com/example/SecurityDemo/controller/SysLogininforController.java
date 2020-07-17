package com.example.SecurityDemo.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecurityDemo.domain.SysLogininfor;
import com.example.SecurityDemo.service.SysLogininforService;
import com.example.SecurityDemo.util.PageRequest;
import com.example.SecurityDemo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统访问记录 前端控制器
 * </p>
 *
 * @author zfx
 * @since 2020-07-17
 */
@RestController
@RequestMapping("monitor/logininfor")
public class SysLogininforController {
    @Autowired
    private SysLogininforService logininforService;

    //@PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
    @GetMapping("/list")
    public Result list(PageRequest pageRequest, SysLogininfor logininfor)
    {
        Result result=new Result();
        Page<SysLogininfor> page = new Page<>(pageRequest.getPageNum(),pageRequest.getPageSize());
        IPage<SysLogininfor> mapIPage = (IPage<SysLogininfor>) logininforService.selectLogininforList(page,logininfor);
        List<SysLogininfor> list = mapIPage.getRecords();
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

//    @Log(title = "登陆日志", businessType = BusinessType.EXPORT)
//    @PreAuthorize("@ss.hasPermi('monitor:logininfor:export')")
//    @GetMapping("/export")
//    public AjaxResult export(SysLogininfor logininfor)
//    {
//        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
//        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
//        return util.exportExcel(list, "登陆日志");
//    }

    /**
    * @description  删除日志
    *@params
    * @return
    * @author  zfx
    * @date  2020/7/17 11:24
    *
    */
    //@PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @GetMapping("/{infoIds}")
    public Result remove(@PathVariable Long[] infoIds)
    {
        Result result=new Result();
        int count=logininforService.deleteLogininforByIds(infoIds);

        if(count!=1){
            result.setMessage("删除失败！");
            result.setCode(Result.RESULT_ERROR);
            return result;
        }
        result.setMessage("删除成功！");
        result.setCode(Result.RESULT_SUCCESS);
        return result;
    }

  //  @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
  //  @Log(title = "登陆日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public Result clean()
    {
        Result result=new Result();
        logininforService.cleanLogininfor();
        result.setCode(Result.RESULT_SUCCESS);
        result.setMessage("操作成功！");
        return result;
    }
}
