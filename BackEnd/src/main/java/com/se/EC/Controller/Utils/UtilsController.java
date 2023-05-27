package com.se.EC.Controller.Utils;


import com.se.EC.Utils.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/utils")
public class UtilsController {

    @PostMapping("/imageUpload")
    public ApiResult<String> imageUpload(MultipartFile file, HttpServletRequest request) throws IOException {

        String filePath = "src\\main\\resources\\static\\image";
        File folder = new File(filePath);
        System.out.println(folder.getPath());
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        ApiResult<String> res = null;
        try {
            file.transferTo(new File(folder, newName));
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/image/" + newName;
            return  ApiResult.success(url);
        } catch (IOException e) {

        }

        return ApiResult.error("unknow error!");
    }

}
