package com.user.dao;

import com.user.entity.pojo.ExportUserParameter;
import com.user.entity.pojo.UserInfo;
import com.user.entity.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户处理持久层接口
 *
 * @author wanghognshen
 * @date 2020-03-03
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 添加用户
     *
     * @param userInfo 用户信息
     * @return Boolean
     */
    Boolean addUser(UserInfo userInfo);

    /**
     * 批量添加用户
     *
     * @param userInfos 用户信息
     * @return Boolean
     */
    Boolean batchAddUser(@Param("userInfos") List<UserInfo> userInfos);

    /**
     * 查询用户
     *
     * @param userInfo 用户信息
     * @return UserInfo
     */
    List<UserInfoVo> selectUser(UserInfo userInfo);


    /**
     * 修改用户信息
     * @param userInfo 用户信息
     * @return Boolean
     */
    Boolean updateUser(UserInfo userInfo);

    /**
     * 使用主键删除用户
     * @param id 主键
     * @return Boolean
     */
    Boolean deleteUserById(@Param("id") Long id);

    /**
     * 通过用户名查询用户一致性
     *
     * @param userName 用户名
     * @param id
     * @return List
     */
    List<UserInfoVo> selectUserByNameOrId(@Param("userName") String userName, @Param("id") Long id);

    /**
     * 查询导出用户信息
     *
     * @param userInfo 用户参数
     * @return
     */
    List<UserInfoVo> selectExportUser(ExportUserParameter userInfo);
}
