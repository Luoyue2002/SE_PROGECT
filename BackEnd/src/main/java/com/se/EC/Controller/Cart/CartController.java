package com.se.EC.Controller.Cart;

import com.se.EC.Entity.Cart;
import com.se.EC.Entity.Commodity;
import com.se.EC.Entity.Item;
import com.se.EC.Pojo.CommodityPreviewObject;
import com.se.EC.Pojo.ItemObject;
import com.se.EC.Service.Cart.CartServiceInterface;
import com.se.EC.Service.Commodity.CommodityServiceInterface;
import com.se.EC.Service.Item.ItemServiceInterface;
import com.se.EC.Service.User.UserServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController implements CartControllerInterface {
    @Resource
    private CartServiceInterface cartServiceInterface;
    @Resource
    private ItemServiceInterface itemServiceInterface;
    @Resource
    private UserServiceInterface userServiceInterface;
    @Resource
    private CommodityServiceInterface commodityServiceInterface;

    @Override
    @RequestMapping("/addCart")
    public ApiResult<Boolean> addCart(@RequestParam(value = "userId") Integer userId,
                                      @RequestParam(value = "itemId") Integer itemId,
                                      @RequestParam(value = "itemNumber") Integer itemNumber) {
        try {
            checkUser(userId);
            checkItem(itemId);
            checkItemNumber(itemNumber);

            cartServiceInterface.addCart(userId, itemId, itemNumber);
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/modifyCart")
    public ApiResult<Boolean> modifyCart(@RequestParam(value = "userId") Integer userId,
                                         @RequestParam(value = "itemId") Integer itemId,
                                         @RequestParam(value = "itemNumber") Integer itemNumber) {
        try {
            checkUser(userId);
            checkItem(itemId);
            checkItemNumber(itemNumber);
            if(itemNumber==0){
                cartServiceInterface.deleteCart(userId,itemId);
            }
            else{
                cartServiceInterface.modifyCart(userId, itemId, itemNumber);
            }
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/deleteCart")
    public ApiResult<Boolean> deleteCart(@RequestParam(value = "userId") Integer userId,
                                         @RequestParam(value = "itemId") Integer itemId) {
        try {
            checkUser(userId);
            checkItem(itemId);
            cartServiceInterface.deleteCart(userId, itemId);
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/getCart")
    public ApiResult<List<CommodityPreviewObject>> getCart(@RequestParam(value = "userId") Integer userId) {
        try {
            checkUser(userId);
            List<Cart> cartList = cartServiceInterface.getCart(userId);
            List<CommodityPreviewObject> commodityPreviewObjectList = new ArrayList<>();
            for (var cart : cartList) {
                Integer id = cart.getItem();
                Item item = itemServiceInterface.getById(id);
                String name = item.getName();
                Float price = item.getPrice();
                Integer count = cart.getNumber();
                Integer commodityId = itemServiceInterface.getParentId(id);
                Commodity commodity = commodityServiceInterface.getCommodityDetailById(commodityId);
                String url = commodity.getImage();
                CommodityPreviewObject commodityPreviewObject = new CommodityPreviewObject(commodityId, name, url, price, count);
                commodityPreviewObjectList.add(commodityPreviewObject);
            }
            return ApiResult.success(commodityPreviewObjectList);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @RequestMapping("/getCartItem")
    public ApiResult<List<ItemObject>> getCartItem(@RequestParam(value = "userId") Integer userId){
        try {
            checkUser(userId);
            List<Cart> cartList = cartServiceInterface.getCart(userId);
            List<ItemObject> itemObjectList = new ArrayList<>();
            for (var cart : cartList) {
                Integer id = cart.getItem();
                Item item = itemServiceInterface.getById(id);
                String name = item.getName();
                Float price = item.getPrice();
                Integer count = cart.getNumber();
                Integer commodityId = itemServiceInterface.getParentId(id);
                Commodity commodity = commodityServiceInterface.getCommodityDetailById(commodityId);
                Integer publisherId = commodity.getPublisher();
//                String url = commodity.getImage();
                ItemObject itemObject = new ItemObject(id, name, count, price, publisherId);
                itemObjectList.add(itemObject);

//                private Integer itemId;  // 商品子分类id，前端传入不需要填
//                private String name;  // 子分类名称
//                private Integer number;  // 库存数量
//                private Float price;  // 价格
            }
            return ApiResult.success(itemObjectList);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 检查用户是否存在
     * @param userId 用户 id
     */
    private void checkUser(Integer userId) {
        if (!userServiceInterface.ifUserExists(userId)) {
            throw new RuntimeException("User " + userId + " does not exist");
        }
    }

    /**
     * 检查商品是否存在
     * @param itemId 商品id
     */
    private void checkItem(Integer itemId) {
        if (!itemServiceInterface.ifItemExists(itemId)) {
            throw new RuntimeException("Item " + itemId + " does not exist");
        }
    }

    /**
     * 检查商品数目是否合法
     * @param number 数目
     */
    private void checkItemNumber(Integer number) {
        if (number <= 0) {
            throw new RuntimeException("The number of item is less than 0");
        }
        if (number >= 1000) {
            throw new RuntimeException("The number of item is too large");
        }
    }
}
