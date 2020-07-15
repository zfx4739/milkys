package com.example.SecurityDemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户顾客信息表
 * </p>
 *
 * @author zfx
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//Swagger注解
@ApiModel("用户顾客信息实体类")
@TableName("sysuser")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 微信号
     */
    @ApiModelProperty("微信号")
    private String wxnumber;
    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String head;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /**
     * 帐号状态（0正常 1停用）
     */
    @ApiModelProperty("帐号状态（0正常 1停用）")
    private String status;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String username;
    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String sex;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;
    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String card;
    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private Date birthday;
    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码")
    private String phone;
    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;
    /**
     * 余额
     */
    @ApiModelProperty("余额")
    private String balance;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField("createTime")
    private Date createTime;
    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    private String creator;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @TableField("updateTime")
    private Date updateTime;
    /**
     * 修改者
     */
    @ApiModelProperty("修改者")
    private String mender;

    /**
     * openid
     */
    @ApiModelProperty("openid")
    private String openid;

    //账号是否可用。默认为1（可用）
    @TableField(exist = false)
    private Boolean enabled;
    //是否过期。默认为1（没有过期）
    @TableField(exist = false)
    private Boolean accountNonExpired;
    //账号是否锁定。默认为1（没有锁定）
    @TableField(exist = false)
    private Boolean accountNonLocked;
    //证书（密码）是否过期。默认为1（没有过期）
    @TableField(exist = false)
    private Boolean credentialsNonExpired;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
