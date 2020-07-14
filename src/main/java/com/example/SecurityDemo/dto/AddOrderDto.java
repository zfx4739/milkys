package com.example.SecurityDemo.dto;

import com.example.SecurityDemo.domain.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ：zfx
 * @date ：Created in 2020/7/7 14:51
 * @description：
 * @modified By：
 * @version: $
 */
@ApiModel("添加订单请求参数封装")
@Data
public class AddOrderDto {
    //用户名
    @ApiModelProperty("用户名")
    private String username;
    //用户配送地址
    @ApiModelProperty("用户配送地址")
    private String useraddress;
    //支付状态
    @ApiModelProperty("支付状态:0：未支付1：已支付")
    private String payStatus;
    //总价
    @ApiModelProperty("总价")
    private Integer totalPrices;
    //用户Id
    @ApiModelProperty("用户Id")
    private Integer userId;
    //openId
    @ApiModelProperty("openId")
    private String openId;
    //购买数量
    @ApiModelProperty("购买数量")
    private Integer number;
    //购买商品(用户商品一对多)
    @ApiModelProperty("购买商品(用户商品一对多)")
    private List<Product> products;
}
