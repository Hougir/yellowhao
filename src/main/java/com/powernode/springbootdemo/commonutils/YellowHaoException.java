package com.powernode.springbootdemo.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PACKAGE_NAME: com.powernode.springbootdemo.commonutils
 * @NAME: YellowHaoException
 * @AUTHOR: 如意郎君
 * @DATE: 2020/9/16
 * @TIME: 15:09
 * @DAY_NAME_SHORT: 星期三
 * @VERSION: 1.0
 *
 * 自定义异常
 */

@Data
@AllArgsConstructor  //全参构造
@NoArgsConstructor  //无参构造
public class YellowHaoException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "状态码")
    private String message;

    @ApiModelProperty(value = "消息枚举")
    private ResultEnum resultEnum;


    public YellowHaoException(ResultEnum resultEnum){
        this.resultEnum = resultEnum;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }

    @Override
    public String toString() {
        return "YellowHaoException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", resultEnum=" + resultEnum +
                '}';
    }
}
