package com.example.SecurityDemo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecurityDemo.domain.Member;
import com.example.SecurityDemo.service.memberService;
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
 * 会员信息表 前端控制器
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private memberService memberservice;


    @ApiOperation("获取会员列表方法")
    @GetMapping("getList")
    public Result getList(){
        Result result=new Result();
        QueryWrapper<Member> que=new QueryWrapper<Member>();
        que.orderByDesc("id");
        List<Member> members=memberservice.list(que);
        List<Member> memberss=memberservice.findAll();
        result.setData(memberss);
        result.setSuccess("获取成功！");
        return result;
    }
    /**
     * @description  分页查询会员方法
     *@params  pageRequest  member
     * @return  import com.kanq.platform.test.util.Result;
     * @author  zfx
     * @date  2020/6/19 9:24
     *
     */
 //   @PreAuthorize("hasAnyRole('5')")
    @ApiOperation("分页获取会员列表方法")
    @GetMapping("listMember")
    public Result listMember(PageRequest pageRequest, Member member){
        Result result=new Result();
        QueryWrapper<Member> wrapper = new QueryWrapper();
        //排序方式
        wrapper.orderByDesc("id");
        Page<Member> page = new Page<>(pageRequest.getPageNum(),pageRequest.getPageSize());
        IPage<Member> mapIPage = memberservice.GetMemberList(page, wrapper);
        List<Member> list = mapIPage.getRecords();
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
    @ApiOperation("添加会员信息方法")
    @PostMapping("addMember")
    public Result addMember(@Valid Member member, BindingResult bindingResult){
        Result result=new Result();
        if( bindingResult.hasErrors()){
            String messages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .reduce((m1, m2) -> m1 + "；" + m2)
                    .orElse("参数输入有误！");
            throw new IllegalArgumentException(messages);
        }
        int count=memberservice.addMember(member);
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
    @ApiOperation("修改会员信息方法")
    @PostMapping("/updateMember")
    public Result updateMember(Member member){
        Result result=new Result();
        int count=memberservice.updMember(member);
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
    @ApiOperation("删除会员信息方法")
    @GetMapping("/deleteMember")
    public Result deteleMeber(int id){
        Result result=new Result();
        int count=memberservice.delMember(id);
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
     * @description  获取会员详情
     *@params
     * @return
     * @author  zfx
     * @date  2020/6/19 11:14
     *
     */
    @ApiOperation("获取会员详情方法")
    @GetMapping("/detailMember")
    public Result detailMember(int id){
        Result result=new Result();
        Member meber=memberservice.detali(id);
        result.setCode(Result.RESULT_SUCCESS);
        result.setMessage("操作成功");
        result.setData(meber);
        return result;
    }


}

