package com.user.entity.pojo;

import com.user.entity.validator.First;
import com.user.entity.validator.Second;
import com.utils.pojo.PageVo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 *  用户信息实体
 *
 * @author wanghongshen
 * @date 2019-03-02
 */
public class UserInfo extends PageVo {
    /**
     * 主键id
     */
    @NotNull(groups = { Second.class }, message = "主键id不能为空")
    private Long id;
    /**
     * 用户名
     */
    @NotNull(groups = { First.class }, message = "用户名不能为空")
    private String userName;
    /**
     * 密码
     */
    @NotNull(groups = { First.class }, message = "密码不能为空")
    private String password;
    /**
     * 邮箱
     */
    @Email(groups = { First.class }, message = "邮箱格式不对")
    private String email;
    /**
     * 性别：女：0，男：1
     */
    private  Integer gender;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 电话
     */
    private String phone;
    /**
     * 用户等级(0:普通用户；1:管理员)
     */
    private Integer level;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", level=" + level +
                '}';
    }

}
