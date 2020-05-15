package com.user.service.userService.impl;

import com.user.common.utils.ExcelUtil;
import com.user.dao.UserMapper;
import com.user.entity.pojo.ExportUserParameter;
import com.user.entity.pojo.UserInfo;
import com.user.entity.vo.UserInfoVo;
import com.user.excel.imports.ImportExcel;
import com.user.service.userService.UserService;
import com.utils.exception.ConsistencyException;
import com.utils.exception.NullException;
import com.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.user.excel.export.ExportExcel.exportExcel;

/**
 * 用户服务实现类
 *
 * @author wanghongshen
 * @date 2020-03-03
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean addUser(UserInfo userInfo) throws ConsistencyException {
        log.info("UserService.addUser处理中, 处理参数: {}", JsonUtil.toJSONString(userInfo));
        List<UserInfoVo> info = userMapper.selectUserByNameOrId(userInfo.getUserName(), null);
        if(info.size() > 0){
            throw new ConsistencyException("用户已存在!");
        }
        Boolean result = userMapper.addUser(userInfo);
        log.info("UserService.addUser处理结束, 处理结果: result = {}", result);
        return result;
    }

    @Override
    public List<UserInfoVo> selectUserInfo(UserInfo userInfo) {
        log.info("UserService.selectUserInfo处理中, 处理参数: {}", JsonUtil.toJSONString(userInfo));
        List<UserInfoVo> pageResult = userMapper.selectUser(userInfo);
        log.info("UserService.selectUserInfo处理结束, 处理结果: {}", JsonUtil.toJSONString(pageResult));
        return pageResult;
    }

    @Override
    public Boolean updateUser(UserInfo userInfo) throws NullException, ConsistencyException {
        log.info("UserService.updateUser处理中, 处理参数: {}", JsonUtil.toJSONString(userInfo));
        List<UserInfoVo> info = userMapper.selectUserByNameOrId(null, userInfo.getId());
        if (info.size() == 0){
            throw new NullException("用户信息已被删除!");
        }
        List<UserInfoVo> repeatInfo = userMapper.selectUserByNameOrId(userInfo.getUserName(), null);
        if(repeatInfo.size() > 0){
            throw new ConsistencyException("用户已存在!");
        }
        Boolean result = userMapper.updateUser(userInfo);
        log.info("UserService.updateUser处理结束, 处理结果: result = {}", JsonUtil.toJSONString(result));
        return result;
    }

    @Override
    public Boolean deleteUser(Long id) throws NullException {
        log.info("UserService.deleteUser处理中, 处理参数: id = {}", id);
        List<UserInfoVo> info = userMapper.selectUserByNameOrId(null, id);
        if (info.size() == 0){
            throw new NullException("用户信息已被删除！");
        }
        Boolean result = userMapper.deleteUserById(id);
        log.info("UserService.deleteUser处理结束, 处理结果: result = {}", result);
        return result;
    }

    @Override
    public void exportUserInfo(ExportUserParameter userInfo) {
        log.info("开始进行用户信息导出。。。");
        List<UserInfoVo> pageResult = userMapper.selectExportUser(userInfo);
        String[] strs = new String[UserInfoVo.class.getDeclaredFields().length];
        for(int i=0;i<UserInfoVo.class.getDeclaredFields().length;i++){
            strs[i] = UserInfoVo.class.getDeclaredFields()[i].toString();
            strs[i] = strs[i].substring(strs[i].lastIndexOf(".")+1, strs[i].length());
        }
        exportExcel("用户信息", "用户信息", strs, pageResult, "/file_server/excel/userInfo"+System.currentTimeMillis()+".xls", "yyyy-MM-dd");
        log.info("用户信息到处结束");
    }

    @Override
    public void importUserInfo(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        log.info("开始进行用户信息导入。。。");
        //@Value("${file.root.path}")
        String rootPath="/D:";
        //@Value("${file.csv.path}")
        String tempFile = "/MyWorkDoc";
        String url = ExcelUtil.fileUpload(request, tempFile, rootPath,".xls");
        List<?> userInfos =  ImportExcel.importExcel(url, 0 , 0, UserInfo.class);
        Boolean result = userMapper.batchAddUser((List<UserInfo>) userInfos);
        if(result){
            log.info("导入成功");
        }else{
            log.info("导入失敗");
        }


    }
}
