package com.user.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回数据信息
 *
 * @author wanghongshen
 * @date 2020-03-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespInfo {
    /**
     * 返回码
     */
    private Integer respCode;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回对象
     */
    private Object respVo;

    public RespInfo(Integer respCode, String message){
        this.respCode = respCode;
        this.message = message;
    }
}
