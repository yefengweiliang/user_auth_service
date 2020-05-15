package com.user.common.utils;

import com.utils.exception.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
@Slf4j
public class CommonUtil {
    /**
     * 验证参数是否合法
     *
     * @param bindingResult
     * @throws ParamException
     */
    public static void validLog(BindingResult bindingResult) throws ParamException {
            if (bindingResult.hasErrors()) {
                if (log.isWarnEnabled()) {
                    StringBuilder content = new StringBuilder();
                    for (FieldError item : bindingResult.getFieldErrors()) {
                        content.append(item.getField()).append(":").append(item.getDefaultMessage()).append(",");
                    }
                    log.info("传入非法参数，原因：[{}]", content.substring(0, content.length() - 1));
                    throw new ParamException("传入参数非法异常");

                }
            }
    }
}
