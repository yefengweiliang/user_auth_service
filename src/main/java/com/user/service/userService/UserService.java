package com.user.service.userService;

import com.user.entity.pojo.ExportUserParameter;
import com.user.entity.pojo.UserInfo;
import com.user.entity.vo.UserInfoVo;
import com.utils.exception.ConsistencyException;
import com.utils.exception.NullException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用户服务接口
 *
 * @author wanghongshen
 * @date 2020-03-03
 */
public interface UserService {
    /**
     * 添加用户
     *
     * @param userInfo 用户信息
     * @return Boolean
     */
    Boolean addUser(UserInfo userInfo) throws ConsistencyException;

    /**
     * 查询用户信息
     *
     * @param userInfo 用户信息
     * @return UserInfoVo
     */
    List<UserInfoVo> selectUserInfo(UserInfo userInfo);

    /**
     * 修改用户
     *
     * @param userInfo 用户信息
     * @return Boolean
     */
    Boolean updateUser(UserInfo userInfo) throws NullException, ConsistencyException;

    /**
     * 删除用户
     *
     * @param id 用户id信息
     * @return Boolean
     */
    Boolean deleteUser(Long id) throws NullException;

    /**
     * 导出用户
     *
     * @param userInfo 用户参数
     */
    void exportUserInfo(ExportUserParameter userInfo);

    /**
     * 导入用户
     *
     */
    void importUserInfo(HttpServletRequest request,
                        HttpServletResponse response) throws IOException;
}
