package com.se.EC.Controller.Utils;

import com.se.EC.Utils.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("/utils")
public class UtilsController implements UtilsControllerInterface {
    @PostMapping("/imageUpload")
    public ApiResult<String> imageUpload(MultipartFile file, HttpServletRequest request) {
        try {
            String filePath = "src\\main\\resources\\static\\image";
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
}
