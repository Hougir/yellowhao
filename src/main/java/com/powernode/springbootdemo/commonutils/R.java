package com.powernode.springbootdemo.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
//统一返回结果的类
public class R {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有
    private R() {
    }

    //成功静态方法
    public static R ok(ResultEnum resultEnum) {
        R r = new R();
        r.setSuccess(true);
        r.setCode(resultEnum.getCode());
        r.setMessage(resultEnum.getMessage());
        return r;
    }

    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage("操作成功");
        return r;
    }

    //失败静态方法
    public static R error(ResultEnum resultEnum) {
        R r = new R();
        r.setSuccess(false);
        r.setCode(resultEnum.getCode());
        r.setMessage(resultEnum.getMessage());
        return r;
    }

    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(444);
        r.setMessage("操作失败");
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(ResultEnum resultEnum){
        this.setMessage(resultEnum.getMessage());
        return this;
    }

    public R message(YellowHaoException e){
        this.setMessage(e.getMessage());
        return this;
    }

    public R code(ResultEnum resultEnum){
        this.setCode(resultEnum.getCode());
        return this;
    }

    public R code(YellowHaoException e){
        this.setCode(e.getCode());
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
