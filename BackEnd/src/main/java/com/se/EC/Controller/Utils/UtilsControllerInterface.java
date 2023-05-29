package com.se.EC.Controller.Utils;

import com.se.EC.Utils.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

public interface UtilsControllerInterface {
    @PostMapping("/imageUpload")
    ApiResult<String> imageUpload(MultipartFile file, HttpServletRequest request);
}
