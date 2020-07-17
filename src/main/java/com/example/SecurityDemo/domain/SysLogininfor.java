package com.example.SecurityDemo.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统访问记录
 * </p>
 *
 * @author zfx
 * @since 2020-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("系统访问记录实体类")
public class SysLogininfor extends Model<SysLogininfor> {

    private static final long serialVersionUID = 1L;

    /**
     * 访问ID
     */
    @ApiModelProperty("访问ID")
    @TableId(value = "info_id", type = IdType.AUTO)
    private Long info_id;
    /**
     * 用户账号
     */
    @ApiModelProperty("用户账号")
    private String user_name;
    /**
     * 登录IP地址
     */
    @ApiModelProperty("登录IP地址")
    private String ipaddr;
    /**
     * 登录地点
     */
    @ApiModelProperty("登录地点")
    private String login_location;
    /**
     * 浏览器类型
     */
    @ApiModelProperty("浏览器类型")
    private String browser;
    /**
     * 操作系统
     */
    @ApiModelProperty("操作系统")
    private String os;
    /**
     * 登录状态（0成功 1失败）
     */
    @ApiModelProperty("登录状态（0成功 1失败）")
    private String status;
    /**
     * 提示消息
     */
    @ApiModelProperty("提示消息")
    private String msg;
    /**
     * 访问时间
     */
    @ApiModelProperty("访问时间")
    private Date login_time;



    /** 搜索值 */
    @TableField(exist = false)
    private String searchValue;


    /** 开始时间 */

    @JsonIgnore
    @TableField(exist = false)
    private String beginTime;

    /** 结束时间 */
    @JsonIgnore
    @TableField(exist = false)
    private String endTime;

    @Override
    protected Serializable pkVal() {
        return this.info_id;
    }

}
