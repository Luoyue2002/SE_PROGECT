package com.se.EC.Controller.Commodity;

import com.se.EC.Pojo.CommodityObject;
import com.se.EC.Pojo.CommodityPreviewObject;
import com.se.EC.Pojo.PictureSearch;
import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CommodityControllerInterface {
    /**
     * 推荐商品，得到一系列商品预览
     *
     * @param id 用户id
     * @return List<CommodityPreviewObject>
     */
    @RequestMapping("/recommend")
    ApiResult<List<CommodityPreviewObject>> recommend(@RequestParam(value = "id") Integer id);

    /**
     * 搜索商品，得到一系列商品预览
     *
     * @param id      用户id
     * @param content 搜索内容
     * @return List<CommodityPreviewObject>
     */
    @RequestMapping("/searchByContent")
    ApiResult<List<CommodityPreviewObject>> searchByContent(@RequestParam(value = "id") Integer id,
                                                            @RequestParam(value = "content") String content);

    @PostMapping("/searchByPicture")
    ApiResult<List<CommodityPreviewObject>> searchByPicture(@RequestBody PictureSearch pictureSearch);

    /**
     * 搜索商品，得到一系列商品预览
     *
     * @param id          用户id
     * @param publisherId 搜索内容
     * @return List<CommodityPreviewObject>
     */
    @RequestMapping("/searchByPublisher")
    ApiResult<List<CommodityPreviewObject>> searchByPublisher(@RequestParam(value = "id") Integer id,
                                                              @RequestParam(value = "publisher_id") Integer publisherId);

    /**
     * 搜索商品，得到一系列商品预览
     *
     * @param id       用户id
     * @param category 商品类别
     * @return List<CommodityPreviewObject>
     */
    @RequestMapping("/searchByCategory")
    ApiResult<List<CommodityPreviewObject>> searchByCategory(@RequestParam(value = "id") Integer id,
                                                             @RequestParam(value = "category") String category);

    /**
     * 搜索商品，按照销量返回
     *
     * * @param id 用户id
     * @return List<CommodityPreviewObject>
     */
    @RequestMapping("/searchBySales")
    ApiResult<List<CommodityPreviewObject>> searchBySales(@RequestParam(value = "id") Integer id);

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

    /**
     * 点击购物车/收藏夹预览图进入详情界面
     *
     * @param userId 点击者id
     * @param itemId 细分类id
     * @return 该商品的详细信息
     */
    @RequestMapping("/clickItem")
    ApiResult<CommodityObject> clickItem(@RequestParam(value = "userId") Integer userId,
                                         @RequestParam(value = "item") Integer itemId);
}
