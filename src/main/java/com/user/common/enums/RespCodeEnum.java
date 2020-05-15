package com.user.common.enums;

import com.user.entity.vo.RespInfo;

/**
 * 应答码枚举类
 *
 * @author wanghongshen
 * @date 2020-03-03
 */
public enum RespCodeEnum {
    SUCCESS(100, "成功"),

    MISSING_PARAMETERS(101, "参数缺失"),

    ILLEGAL_PARAMETERS(102, "参数不合法"),

    REPEAT_DATA(103, "数据重复"),

    ALREADY_DELETE(104, "数据已经被删除"),

    INTERNAL_EXCEPTION(500, "内部异常");

    /**
     * 类型编码
     */
    private Integer code;
    /**
     * 类型描述
     */
    private String desc;

    RespCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举
     *
     * @param code
     * @return
     */
    public static RespCodeEnum of(Integer code) {
        for(RespCodeEnum curEnum : RespCodeEnum.values()) {
            if (curEnum.getCode().equals(code)) {
                return curEnum;
            }
        }
        return null;
    }

    /**
     * 根据code 获取描述
     *
     * @param code
     * @return
     */
    public static String getDesc(Integer code) {
        RespCodeEnum curEnum = of(code);
        return null != curEnum ? curEnum.getDesc() : null;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取应答对象类
     */
    public static class RespObject{
        /**
         * 获取缺失参数对象
         *
         * @return RespInfo
         */
        public static RespInfo missingParam(){
            return new RespInfo(RespCodeEnum.MISSING_PARAMETERS.getCode(), RespCodeEnum.MISSING_PARAMETERS.getDesc() );
        }

        /**
         * 获取参数非法对象
         *
         * @return RespInfo
         */
        public static RespInfo illegalParam(){
            return new RespInfo(RespCodeEnum.ILLEGAL_PARAMETERS.getCode(), RespCodeEnum.ILLEGAL_PARAMETERS.getDesc());
        }

        /**
         * 获取数据重复对象
         *
         * @return RespInfo
         */
        public static RespInfo repeatData(){
            return new RespInfo(RespCodeEnum.REPEAT_DATA.getCode(), RespCodeEnum.REPEAT_DATA.getDesc());
        }

        /**
         * 获取已经被删除对象
         *
         * @return RespInfo
         */
        public static RespInfo alreadyDelete(){
            return new RespInfo(RespCodeEnum.ALREADY_DELETE.getCode(), RespCodeEnum.ALREADY_DELETE.getDesc());
        }

        /**
         * 获取成功应答对象
         * @param obj 应答对象
         * @return RespInfo
         */
        public static RespInfo success(Object obj){
            return new RespInfo(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getDesc(), obj);
        }

        /**
         * 获取内部异常对象
         *
         * @return RespInfo
         */
        public static  RespInfo internalException(){
            return new RespInfo(RespCodeEnum.INTERNAL_EXCEPTION.getCode(), RespCodeEnum.INTERNAL_EXCEPTION.getDesc());
        }

    }

}
