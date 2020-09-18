package com.powernode.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yellowhao
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *      AUTO(0),  自增
     *     NONE(1),  没有主键
     *     INPUT(2), 手动添加
     *     ID_WORKER(3),  默认  雪花算法  Long类型
     *     UUID(4),   uuid
     *     ID_WORKER_STR(5);   雪花算法  字符串类型
     */
    @ApiModelProperty(dataType = "主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "微信openid")
    private String openid;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic /*逻辑删除 注解 配置类EduConfig 需要配置插件*/
    private Integer deleted;

    @ApiModelProperty(value = "乐观锁")
    private Integer version;



    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)//自动填充
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)//自动填充
    private Date gmtModified;

}
