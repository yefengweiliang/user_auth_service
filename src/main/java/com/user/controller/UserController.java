package com.user.controller;

import com.user.common.enums.RespCodeEnum;
import com.user.common.utils.CommonUtil;
import com.user.entity.pojo.ExportUserParameter;
import com.user.entity.pojo.UserInfo;
import com.user.entity.validator.First;
import com.user.entity.validator.Second;
import com.user.entity.vo.UserInfoVo;
import com.user.service.userService.UserService;
import com.utils.exception.ConsistencyException;
import com.utils.exception.NullException;
import com.utils.exception.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用户管理控制层
 *
 * @author wanghongshen
 * @date 2020-03-03
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @param userInfo 用户查询参数
     * @return Object
     */
    @PostMapping(value = "/getUser")
    public Object getUserInfo(@RequestBody  UserInfo userInfo) {
        List<UserInfoVo> userInfoVo = userService.selectUserInfo(userInfo);
        return RespCodeEnum.RespObject.success(userInfoVo);
    }

    /**
     * 添加用户信息
     *
     * @param userInfo      用户查询参数
     * @param bindingResult 参数校验结果
     * @return Object
     */
    @PostMapping(value = "/addUser")
    public Object addUserInfo(@Validated({First.class}) UserInfo userInfo, BindingResult bindingResult) {

        Boolean result = false;
        try {
            //打印校验结果
            CommonUtil.validLog(bindingResult);
            result = userService.addUser(userInfo);

            if (result) {
                return RespCodeEnum.RespObject.success(result);
            } else {
                return RespCodeEnum.RespObject.internalException();
            }
        } catch (ConsistencyException e) {
            log.error("数据插入错误,原因:{}", e.getMessage());
            return RespCodeEnum.RespObject.repeatData();
        } catch (ParamException e) {
            return RespCodeEnum.RespObject.missingParam();
        }
    }

    /**
     * 修改用户信息
     *
     * @param userInfo      用户查询参数
     * @param bindingResult 参数校验结果
     * @return Object
     */
    @PostMapping(value = "/updateUser")
    public Object updateUser(@Validated({Second.class}) UserInfo userInfo, BindingResult bindingResult) {
        Boolean result = false;
        try {
            //打印校验结果
            CommonUtil.validLog(bindingResult);
            result = userService.updateUser(userInfo);
            if (result) {
                return RespCodeEnum.RespObject.success(result);
            } else {
                return RespCodeEnum.RespObject.internalException();
            }
        } catch (ParamException e) {
            return RespCodeEnum.RespObject.missingParam();
        } catch (NullException e) {
            log.error("数据修改错误,原因:{}", e.getMessage());
            return RespCodeEnum.RespObject.alreadyDelete();
        } catch (ConsistencyException e) {
            log.error("数据修改错误,原因:{}", e.getMessage());
            return RespCodeEnum.RespObject.repeatData();
        }
    }

    /**
     * 删除用户信息
     *
     * @param userInfo      用户
     * @param bindingResult 参数校验结果
     * @return Object
     */
    @PostMapping(value = "/deleteUser")
    public Object deleteUser(@Validated({Second.class}) UserInfo userInfo, BindingResult bindingResult) {
        Boolean result = false;
        try {
            //打印校验结果
            CommonUtil.validLog(bindingResult);
            result = userService.deleteUser(userInfo.getId());
            if (result) {
                return RespCodeEnum.RespObject.success(result);
            } else {
                return RespCodeEnum.RespObject.internalException();
            }
        } catch (ParamException e) {
            return RespCodeEnum.RespObject.missingParam();
        } catch (NullException e) {
            log.error("数据删除错误,原因:{}", e);
            return RespCodeEnum.RespObject.alreadyDelete();
        }
    }

    /**
     * 导出用户信息
     *
     * @param userInfo
     * @return
     */
    @PostMapping(value = "/exportUserInfo")
    public Object exportUserInfo(ExportUserParameter userInfo) {
        userService.exportUserInfo(userInfo);
        return RespCodeEnum.RespObject.success(null);
    }

    /**
     * 导入用戶信息
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/importUserInfo")
    public Object importUserInfo(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
        userService.importUserInfo(request, response);
        return RespCodeEnum.RespObject.success(null);
    }



}
