package com.se.EC.Controller.Commodity;

import com.se.EC.Entity.*;
import com.se.EC.Pojo.Category;
import com.se.EC.Pojo.CommodityObject;
import com.se.EC.Pojo.CommodityPreviewObject;
import com.se.EC.Pojo.ItemObject;
import com.se.EC.Service.Commodity.CommodityServiceInterface;
import com.se.EC.Service.Detail.DetailServiceInterface;
import com.se.EC.Service.History.HistoryServiceInterface;
import com.se.EC.Service.Item.ItemServiceInterface;
import com.se.EC.Service.User.UserServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/commodity")
public class CommodityController implements CommodityControllerInterface {
    @Resource
    private CommodityServiceInterface commodityServiceInterface;
    @Resource
    private HistoryServiceInterface historyServiceInterface;
    @Resource
    private UserServiceInterface userServiceInterface;
    @Resource
    private DetailServiceInterface detailServiceInterface;
    @Resource
    private ItemServiceInterface itemServiceInterface;
    private HistoryMatrix historyMatrix;

    @Override
    @RequestMapping("/recommend")
    public ApiResult<List<CommodityPreviewObject>> recommend(@RequestParam(value = "id") Integer id) {
        try {
            checkUser(id);
            List<Integer> idList = cutAndMend(historyMatrix.recommend(id));
            List<CommodityPreviewObject> commodityPreviewObjectList = new ArrayList<>();
            for (var item : idList) {
                commodityPreviewObjectList.add(packPreview(item));
            }
            return ApiResult.success(commodityPreviewObjectList);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    private CommodityPreviewObject packPreview(Integer id) {
        Commodity commodity = commodityServiceInterface.getCommodityDetailById(id);
        String name = commodity.getName();
        Integer sales = commodity.getSales();
        Float price = commodity.getPrice();
        String picture = commodity.getImage();
        return new CommodityPreviewObject(id, name, picture, price, sales);
    }

    /**
     * 删减或修补
     * @param idList 推荐商品的id列表
     * @return 裁剪之后的商品id列表
     */
    private List<Integer> cutAndMend(List<Integer> idList) {
        int maxSize = 500;
        int minSize = 50;
        if (idList.size() < minSize) {
            List<CommodityPreviewObject> commodityPreviewObjectList = rankBySales();
            List<Integer> commodityIdList = new ArrayList<>();
            for (var item : commodityPreviewObjectList) {
                commodityIdList.add(item.getId());
            }
            idList.addAll(commodityIdList);
        }
        if (idList.size() > maxSize) {
            return idList.subList(0, maxSize);
        }
        return idList;
    }

    /**
     * 将所有商品按照销量排序
     * @return 排序结果
     */
    private List<CommodityPreviewObject> rankBySales() {
        int maxSize = 500;
        List<Commodity> commodityList = commodityServiceInterface.getCommodities();
        commodityList.sort((o1, o2) -> {
            if (o1.getSales() > o2.getSales()) {
                return -1;
            } else if (o1.getSales().equals(o2.getSales())) {
                return 0;
            } else {
                return 1;
            }
        });
        if (commodityList.size() > maxSize) {
            commodityList = commodityList.subList(0, maxSize);
        }
        List<CommodityPreviewObject> commodityPreviewObjectList = new ArrayList<>();
        for (var item : commodityList) {
            Integer commodityId = item.getId();
            commodityPreviewObjectList.add(packPreview(commodityId));
        }
        return commodityPreviewObjectList;
    }

    @Override
    @RequestMapping("/searchBySales")
    public ApiResult<List<CommodityPreviewObject>> searchBySales(@RequestParam(value = "id") Integer id) {
        try {
            checkUser(id);
            return ApiResult.success(rankBySales());
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/searchByContent")
    public ApiResult<List<CommodityPreviewObject>> searchByContent(@RequestParam(value = "id") Integer id,
                                                                   @RequestParam(value = "content") String content) {
        try {
            checkUser(id);
            List<String> tokens = tokenizer(content);
            Map<Integer, Integer> commodityMap = new HashMap<>();
            List<CommodityPreviewObject> commodityPreviewObjectList = new ArrayList<>();
            for (var token : tokens) {
                List<Commodity> commodityList = commodityServiceInterface.getCommoditiesByFuzzyContent(token);
                for (var commodity : commodityList) {
                    Integer commodityId = commodity.getId();
                    if (commodityMap.containsKey(commodityId)) {
                        commodityMap.put(commodityId, commodityMap.get(commodityId) + 1);
                    } else {
                        commodityMap.put(commodityId, 1);
                    }
                }
            }
            List<Map.Entry<Integer, Integer>> sortList = new ArrayList<>(commodityMap.entrySet());
            sortList.sort((o1, o2) -> {
                if (o1.getValue() > o2.getValue()) {
                    return -1;
                } else if (o1.getValue().equals(o2.getValue())) {
                    return 0;
                } else {
                    return 1;
                }
            });
            for (var item : sortList) {
                Integer commodityId = item.getKey();
                commodityPreviewObjectList.add(packPreview(commodityId));
            }
            return ApiResult.success(commodityPreviewObjectList);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    /**
     * 分词
     * @param text 搜索内容
     * @return 分词结果
     */
    private List<String> tokenizer(String text) {
        StopRecognition stopRecognition = new StopRecognition();
        stopRecognition.insertStopNatures("w");
        stopRecognition.insertStopWords(" ");
        String analysedText = ToAnalysis.parse(text).recognition(stopRecognition).toStringWithOutNature();
        return Arrays.asList(analysedText.split(","));
    }

    @Override
    @RequestMapping("/searchByPublisher")
    public ApiResult<List<CommodityPreviewObject>> searchByPublisher(@RequestParam(value = "id") Integer id,
                                                                     @RequestParam(value = "publisher_id") Integer publisherId) {
        try {
            checkUser(id);
            List<Commodity> commodityList = commodityServiceInterface.getCommoditiesByPublisher(publisherId);
            List<CommodityPreviewObject> commodityPreviewObjectList = new ArrayList<>();
            for (var item : commodityList) {
                Integer commodityId = item.getId();
                commodityPreviewObjectList.add(packPreview(commodityId));
            }
            return ApiResult.success(commodityPreviewObjectList);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/searchByCategory")
    public ApiResult<List<CommodityPreviewObject>> searchByCategory(@RequestParam(value = "id") Integer id,
                                                                    @RequestParam(value = "category") String category) {
        try {
            checkUser(id);
            checkCategory(category);
            List<Commodity> commodityList = commodityServiceInterface.getCommoditiesByCategory(category);
            int maxSize = 500;
            if (commodityList.size() > maxSize) {
                commodityList = commodityList.subList(0, maxSize);
            }
            List<CommodityPreviewObject> commodityPreviewObjectList = new ArrayList<>();
            for (var item : commodityList) {
                Integer commodityId = item.getId();
                commodityPreviewObjectList.add(packPreview(commodityId));
            }
            return ApiResult.success(commodityPreviewObjectList);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/click")
    public ApiResult<CommodityObject> click(@RequestParam(value = "userId") Integer userId,
                                            @RequestParam(value = "commodityId") Integer commodityId) {
        try {
            checkUser(userId);
            checkCommodity(commodityId);
            historyMatrix.click(userId, commodityId);
            historyServiceInterface.click(userId, commodityId);
            Commodity commodity = commodityServiceInterface.getCommodityDetailById(commodityId);
            List<Detail> detailList = detailServiceInterface.getDetailsByParentId(commodityId);
            List<Item> itemList = itemServiceInterface.getItemsByParentId(commodityId);
            List<ItemObject> itemObjectList = new ArrayList<>();
            List<String> pictureList = new ArrayList<>();
            for (var item : itemList) {
                ItemObject itemObject = new ItemObject(item.getId(), item.getName(), item.getNumber(), item.getPrice());
                itemObjectList.add(itemObject);
            }
            for (var item : detailList) {
                pictureList.add(item.getImage());
            }
            CommodityObject commodityObject = new CommodityObject(commodityId, commodity.getPublisher(), commodity.getName(),
                    commodity.getDescription(), commodity.getCategory(), itemObjectList, pictureList, commodity.getImage(),
                    commodity.getSales(), commodity.getPrice());
            return ApiResult.success(commodityObject);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    /**
     * 检查商品是否存在
     * @param id 商品id
     */
    private void checkCommodity(Integer id) {
        if (!commodityServiceInterface.ifCommodityExists(id)) {
            throw new RuntimeException("Commodity " + id + " does not exist");
        }
    }

    /**
     * 检查商品细分类是否存在
     * @param itemId 商品id
     */
    private void checkItem(Integer itemId) {
        if (!itemServiceInterface.ifItemExists(itemId)) {
            throw new RuntimeException("Item " + itemId + " does not exist");
        }
    }

    @Override
    @RequestMapping("/clickItem")
    public ApiResult<CommodityObject> clickItem(@RequestParam(value = "userId") Integer userId,
                                         @RequestParam(value = "item") Integer itemId) {
        try {
            checkUser(userId);
            checkItem(itemId);
            Integer commodityId = itemServiceInterface.getParentId(itemId);
            return click(userId, commodityId);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
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
     * 判断类别是否存在
     *
     * @param category 前端传进来的类别
     */
    private void checkCategory(String category) {
        try {
            Category.valueOf(category);
        } catch (Exception e) {
            throw new RuntimeException("Unknown category");
        }
    }

    @Component
    class HistoryMatrix {
        private final Integer[][] activeUserCommodityMatrix;    // 动态的用户-商品矩阵
        private Integer[][] staticMatrix;                       // 静态的用户-商品矩阵，每次协同过滤将动态赋值给静态
        private final double[][] similarityMatrix;              // 用户-用户相似度矩阵
        private final Integer userNumber;                       // 用户数量
        private final Integer commodityNumber;                  // 商品数量
        private final Map<Integer, Integer> userToIdx;          // 用户id到下标映射
        private final Map<Integer, Integer> idxToCommodity;     // 下标到商品id映射
        private final Map<Integer, Integer> commodityToIdx;     // 商品id到下标映射
        private final static double neighborRatio = 0.1;        // 邻居占比

        public HistoryMatrix(List<Integer> userIdList, List<Integer> commodityIdList, List<History> historyList) {
            userNumber = userIdList.size();
            commodityNumber = commodityIdList.size();
            activeUserCommodityMatrix = new Integer[userNumber][commodityNumber];
            similarityMatrix = new double[userNumber][userNumber];
            userToIdx = new HashMap<>();
            idxToCommodity = new HashMap<>();
            commodityToIdx = new HashMap<>();
            for (int i = 0; i < userNumber; i++) {
                Arrays.fill(activeUserCommodityMatrix[i], 0);
                userToIdx.put(userIdList.get(i), i);
            }
            for (int i = 0; i < commodityNumber; i++) {
                idxToCommodity.put(i, commodityIdList.get(i));
                commodityToIdx.put(commodityIdList.get(i), i);
            }
            for (var history : historyList) {
                Integer userId = history.getUserId();
                Integer commodityId = history.getCommodityId();
                Integer number = history.getNumber();
                activeUserCommodityMatrix[userToIdx.get(userId)][commodityToIdx.get(commodityId)] = number;
            }
        }

        /**
         * 每次服务器启动时加载数据库历史记录信息到内存，协同过滤算法将只读取内存而不读取数据库，每次click同时更新数据库和内存
         */
        @PostConstruct
        public void loadHistory() {
            // 读取用户列表
            List<User> userList = userServiceInterface.getUsers();
            List<Integer> userIdList = new ArrayList<>();
            for (var user : userList) {
                userIdList.add(user.getId());
            }

            // 读取商品列表
            List<Commodity> commodityList = commodityServiceInterface.getCommodities();
            List<Integer> commodityIdList = new ArrayList<>();
            for (var commodity : commodityList) {
                commodityIdList.add(commodity.getId());
            }

            // 读取历史消息列表
            List<History> historyList = historyServiceInterface.getHistories();
            historyMatrix = new HistoryMatrix(userIdList, commodityIdList, historyList);
        }

        public void click(Integer userId, Integer commodityId) {
            activeUserCommodityMatrix[userToIdx.get(userId)][commodityToIdx.get(commodityId)] += 1;
        }

        /**
         * 寻找相似用户，看详细用户有而其没有浏览的商品
         *
         * @return 商品 id list
         */
        public List<Integer> recommend(Integer userId) {
            @Data
            @AllArgsConstructor
            class SimilarityInformation {
                private Integer index;
                private Double similarity;
            }

            // 人数小于三，直接退出；新增的一个用户，直接退出
            Integer userIndex = userToIdx.get(userId);
            if (userNumber < 3 || userToIdx.get(userId) == null) {
                return null;
            }

            // 获取相似度数组
            double[] similarity = similarityMatrix[userIndex];
            List<SimilarityInformation> similarityInformationList = new ArrayList<>();
            for (int i = 0; i < userNumber; i++) {
                if (i != userIndex) {
                    similarityInformationList.add(new SimilarityInformation(i, similarity[i]));
                }
            }

            // 排序，降序
            similarityInformationList.sort((o1, o2) -> {
                if (o1.similarity > o2.similarity) {
                    return -1;
                } else if (o1.similarity.equals(o2.similarity)) {
                    return 0;
                } else {
                    return 1;
                }
            });

            // 加入邻居的浏览物品
            int neighborNumber = Math.max((int) (userNumber * neighborRatio), 2);
            Map<Integer, Integer> commodityClickCount = new TreeMap<>();
            for (int i = 0; i < neighborNumber; i++) {
                Integer[] userCommodityList = staticMatrix[similarityInformationList.get(i).getIndex()];
                for (int j = 0; j < commodityNumber; j++) {
                    if (userCommodityList[j] != 0) {
                        if (commodityClickCount.containsKey(j)) {
                            commodityClickCount.put(j, commodityClickCount.get(j) + userCommodityList[j]);
                        } else {
                            commodityClickCount.put(j, userCommodityList[j]);
                        }
                    }
                }
            }

            // 排序并返回
            List<Map.Entry<Integer, Integer>> mapList = new ArrayList<>(commodityClickCount.entrySet());
            mapList.sort((o1, o2) -> {
                if (o1.getValue() > o2.getValue()) {
                    return -1;
                } else if (o1.getValue().equals(o2.getValue())) {
                    return 0;
                } else {
                    return 1;
                }
            });
            List<Integer> commodityList = new ArrayList<>();
            for (var item : mapList) {
                commodityList.add(idxToCommodity.get(item.getKey()));
            }

            return commodityList;
        }

        /**
         * 协同过滤算法，定时计算
         */
        @Scheduled(cron = "0/20 * * * * ?")
        public void collaborativeFiltering() {
            staticMatrix = activeUserCommodityMatrix.clone();
            for (int i = 0; i < userNumber; i++) {
                for (int j = i + 1; j < userNumber; j++) {
                    double distance = calculateSimilarity(staticMatrix[i], staticMatrix[j]);
                    similarityMatrix[i][j] = distance;
                    similarityMatrix[j][i] = distance;
                }
                similarityMatrix[i][i] = 1.0;
            }
        }

        /**
         * 计算两个用户之间的相似度 pearson correlation coefficient
         *
         * @param x 第一个用户
         * @param y 第二个用户
         * @return 相似度
         */
        private double calculateSimilarity(Integer[] x, Integer[] y) {
            int n = x.length;
            double averageX = 0.0;
            double averageY = 0.0;
            double a = 0.0;
            double b = 0.0;
            double c = 0.0;
            for (int i = 0; i < n; i++) {
                averageX += x[i];
                averageY += y[i];
            }
            averageX /= n;
            averageY /= n;
            for (int i = 0; i < n; i++) {
                a += (x[i] - averageX) * (y[i] - averageY);
                b += (x[i] - averageX) * (x[i] - averageX);
                c += (y[i] - averageY) * (y[i] - averageY);
            }
            return a / (Math.sqrt(b * c) + 1e-6);
        }
    }
}