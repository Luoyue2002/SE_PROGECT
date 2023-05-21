package com.se.EC.Controller.Commodity;

import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CommodityControllerInterface {
    /**
     * 推荐商品，得到一系列商品预览
     *
     * @param id        用户id
     * @param pageNum   每页商品数量
     * @param pageIndex 第几页的商品
     * @return List<CommodityPreviewObject>
     */
    @RequestMapping("/recommend")
    ApiResult<List<CommodityPreviewObject>> recommend(@RequestParam(value = "id") Integer id,
                                                      @RequestParam(value = "page_num") Integer pageNum,
                                                      @RequestParam(value = "page_index") Integer pageIndex);

    /**
     * 搜索商品，得到一系列商品预览
     *
     * @param id        用户id
     * @param content   搜索内容
     * @param pageNum   每页商品数量
     * @param pageIndex 第几页的商品
     * @return List<CommodityPreviewObject>
     */
    @RequestMapping("/search_by_content")
    ApiResult<List<CommodityPreviewObject>> searchByContent(@RequestParam(value = "id") Integer id,
                                                            @RequestParam(value = "content") String content,
                                                            @RequestParam(value = "page_num") Integer pageNum,
                                                            @RequestParam(value = "page_index") Integer pageIndex);

    /**
     * 搜索商品，得到一系列商品预览
     *
     * @param id          用户id
     * @param publisherId 搜索内容
     * @param pageNum     每页商品数量
     * @param pageIndex   第几页的商品
     * @return List<CommodityPreviewObject>
     */
    @RequestMapping("/search_by_publisher")
    ApiResult<List<CommodityPreviewObject>> searchByPublisher(@RequestParam(value = "id") Integer id,
                                                              @RequestParam(value = "publisher_id") Integer publisherId,
                                                              @RequestParam(value = "page_num") Integer pageNum,
                                                              @RequestParam(value = "page_index") Integer pageIndex);

    /**
     * 搜索商品，得到一系列商品预览
     *
     * @param id          用户id
     * @param category    商品类别
     * @param pageNum     每页商品数量
     * @param pageIndex   第几页的商品
     * @return List<CommodityPreviewObject>
     */
    @RequestMapping("/search_by_category")
    ApiResult<List<CommodityPreviewObject>> searchByCategory(@RequestParam(value = "id") Integer id,
                                                              @RequestParam(value = "category") String category,
                                                              @RequestParam(value = "page_num") Integer pageNum,
                                                              @RequestParam(value = "page_index") Integer pageIndex);

    /**
     * 点击预览图进入详情界面
     *
     * @param id          点击者id
     * @param commodityId 商品id
     * @return 该商品的详细信息
     */
    @RequestMapping("/click")
    ApiResult<CommodityObject> click(@RequestParam(value = "id") Integer id,
                                     @RequestParam(value = "commodity_id") Integer commodityId);
}
