package com.se.EC.Controller.Utils;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.se.EC.Utils.ApiResult;
import com.se.EC.Utils.CodeUtils;
import com.se.EC.Utils.SmsUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("/utils")
public class UtilsController implements UtilsControllerInterface {
    @PostMapping("/imageUpload")
    public ApiResult<String> imageUpload(MultipartFile file, HttpServletRequest request) {
        try {
            String filePath = "E:\\WorkField\\BS\\ElectronicCommerce\\BackEnd\\src\\main\\resources\\static\\image\\";
            File folder = new File(filePath);
            String oldName = file.getOriginalFilename();
            assert oldName != null;
            String newName = UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));
            file.transferTo(new File(folder, newName));
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/image/" + newName;
            return ApiResult.success(url);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/smsXxs")
    @ResponseBody
    public Map<String,Object> smsXxs(String phone, HttpServletRequest request) throws ClientException {
        Map<String,Object> map = new HashMap<>();
        // 验证码（指定长度的随机数）
        String code = CodeUtils.generateVerifyCode(6);
        String TemplateParam = "{\"code\":\""+code+"\"}";
        // 短信模板id
        String TemplateCode = "SMS_152440521";
        SendSmsResponse response = SmsUtils.sendSms(phone,TemplateParam,TemplateCode);
        map.put("verifyCode",code);
        map.put("phone",phone);
        request.getSession().setAttribute("CodePhone",map);
        if( response.getCode().equals("OK")) {
            map.put("isOk","OK");
        }
        return map;
    }
}
