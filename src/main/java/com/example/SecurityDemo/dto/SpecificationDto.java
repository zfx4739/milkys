package com.example.SecurityDemo.dto;

import com.example.SecurityDemo.domain.Specification;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ：zfx
 * @date ：Created in 2020/7/7 14:20
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@ApiModel("添加订单请求参数封装")
public class SpecificationDto {

    //定制
    @ApiModelProperty("定制")
    private String custom;
    //是否多选
    @ApiModelProperty("是否多选")
    private String ismultiple;
    //规格集合
    @ApiModelProperty("listspf")
    private List<Specification> listspf;
}
