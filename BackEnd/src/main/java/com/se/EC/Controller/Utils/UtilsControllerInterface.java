package com.se.EC.Controller.Utils;

import com.aliyuncs.exceptions.ClientException;
import com.se.EC.Utils.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UtilsControllerInterface {
    @PostMapping("/imageUpload")
    ApiResult<String> imageUpload(MultipartFile file, HttpServletRequest request);

    /**
     * 发送短信
     * @param phone 手机号
     * @param request 请求
     * @return 手机号和验证码
     */
    @RequestMapping(value = "/smsXxs")
    @ResponseBody
    ApiResult<Map<String,Object>> smsXxs(String phone, HttpServletRequest request) throws ClientException;
}
