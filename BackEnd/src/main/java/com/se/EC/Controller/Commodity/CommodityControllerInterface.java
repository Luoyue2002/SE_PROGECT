package com.se.EC.Controller.Commodity;

import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CommodityControllerInterface {
    /**
     * 推荐商品，得到一系列商品预览
     *
     * @param id         用户id
     * @param pageNumber 每页商品数量
     * @param pageIndex  第几页的商品
     * @return List<CommodityPreviewObject>
     */
    @RequestMapping("/recommend")
    ApiResult<List<CommodityPreviewObject>> recommend(@RequestParam(value = "id") Integer id,
                                                      @RequestParam(value = "pageNumber") Integer pageNumber,
                                                      @RequestParam(value = "pageIndex") Integer pageIndex);

    /**
     * 搜索商品，得到一系列商品预览
     *
     * @param id         用户id
     * @param content    搜索内容
     * @param pageNumber 每页商品数量
     * @param pageIndex  第几页的商品
     * @return List<CommodityPreviewObject>
     */
    @RequestMapping("/searchByContent")
    ApiResult<List<CommodityPreviewObject>> searchByContent(@RequestParam(value = "id") Integer id,
                                                            @RequestParam(value = "content") String content,
                                                            @RequestParam(value = "pageNumber") Integer pageNumber,
                                                            @RequestParam(value = "pageIndex") Integer pageIndex);

    /**
     * 搜索商品，得到一系列商品预览
     *
     * @param id          用户id
     * @param publisherId 搜索内容
     * @param pageNumber  每页商品数量
     * @param pageIndex   第几页的商品
     * @return List<CommodityPreviewObject>
     */
    @RequestMapping("/searchByPublisher")
    ApiResult<List<CommodityPreviewObject>> searchByPublisher(@RequestParam(value = "id") Integer id,
                                                              @RequestParam(value = "publisher_id") Integer publisherId,
                                                              @RequestParam(value = "pageNumber") Integer pageNumber,
                                                              @RequestParam(value = "pageIndex") Integer pageIndex);

    /**
     * 搜索商品，得到一系列商品预览
     *
     * @param id         用户id
     * @param category   商品类别
     * @param pageNumber 每页商品数量
     * @param pageIndex  第几页的商品
     * @return List<CommodityPreviewObject>
     */
    @RequestMapping("/searchByCategory")
    ApiResult<List<CommodityPreviewObject>> searchByCategory(@RequestParam(value = "id") Integer id,
                                                             @RequestParam(value = "category") String category,
                                                             @RequestParam(value = "pageNumber") Integer pageNumber,
                                                             @RequestParam(value = "pageIndex") Integer pageIndex);

    /**
     * 点击预览图进入详情界面
     *
     * @param userId      点击者id
     * @param commodityId 商品id
     * @return 该商品的详细信息
     */
    @RequestMapping("/click")
    ApiResult<CommodityObject> click(@RequestParam(value = "userId") Integer userId,
                                     @RequestParam(value = "commodityId") Integer commodityId);
}
