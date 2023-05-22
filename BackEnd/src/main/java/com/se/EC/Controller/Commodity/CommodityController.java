package com.se.EC.Controller.Commodity;

import com.se.EC.Service.Commodity.CommodityServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin // 跨域配置
@RestController // 表明是Controller层
@RequestMapping("/commodity") // url 指定
public class CommodityController implements CommodityControllerInterface {
    @Resource
    private CommodityServiceInterface commodityServiceInterface;

    @Override
    @RequestMapping("/recommend")
    public ApiResult<List<CommodityPreviewObject>> recommend(Integer id, Integer pageNum, Integer pageIndex) {
        return null;
    }

    @Override
    @RequestMapping("/search_by_content")
    public ApiResult<List<CommodityPreviewObject>> searchByContent(Integer id, String content, Integer pageNum, Integer pageIndex) {
        return null;
    }

    @Override
    @RequestMapping("/search_by_publisher")
    public ApiResult<List<CommodityPreviewObject>> searchByPublisher(Integer id, Integer publisherId, Integer pageNum, Integer pageIndex) {
        return null;
    }

    @Override
    @RequestMapping("/search_by_category")
    public ApiResult<List<CommodityPreviewObject>> searchByCategory(Integer id, String category, Integer pageNum, Integer pageIndex) {
        return null;
    }

    @Override
    @RequestMapping("/click")
    public ApiResult<CommodityObject> click(Integer id, Integer commodityId) {
        return null;
    }

    /**
     * 判断类别是否存在
     * @param category 前端传进来的类别
     * @return True/False
     */
    private Boolean checkCategory(String category) {
        try {
            Category.valueOf(category);
        } catch (Exception e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
