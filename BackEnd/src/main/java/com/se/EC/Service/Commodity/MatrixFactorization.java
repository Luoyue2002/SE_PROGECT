package com.se.EC.Service.Commodity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

/**
 * 三元组类，存储数据元组。user-item-rating
 */
class Triple {
    int user;
    int item;
    double rating;

    public Triple(int user, int item, double rating) {
        this.user = user;
        this.item = item;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Triple{" +
                "user=" + user +
                ", item=" + item +
                ", rating=" + rating +
                '}';
    }
}

public class MatrixFactorization {
    //数据集，按照user-item-rating存储
    private Triple[] dataset;
    //用户数量
    private Integer numUser;
    //电影数量
    private Integer numItem;
    //评分数量
    private Integer numRating;
    //随机数生成器
    private Random random = new Random();
    //控制学习规则的参数
    private Double alpha;
    //用于控制学习速率的参数
    private Double lambda;
    //隐空间维度k
    private Integer rank;
    //用户子空间
    private Double[][] userSubspace;
    //电影项目子空间
    private Double[][] itemSubspace;
    //预测评分最低边界
    private Double ratingLowerBound;
    //预测评分最高边界
    private Double ratingUpperBound;

    /**
     * 构造方法，初始化相应参数，读入数据集。
     *
     * @param datasetFileName  数据集地址
     * @param numUser          用户数量
     * @param numItem          电影数量
     * @param numRating        评分数量
     * @param ratingLowerBound 最低评分
     * @param ratingUpperBound 最高评分
     */
    public MatrixFactorization(String datasetFileName, int numUser,
                               int numItem, int numRating, double ratingLowerBound, double ratingUpperBound) {
        //初始化
        this.numItem = numItem;
        this.numUser = numUser;
        this.numRating = numRating;
        this.ratingLowerBound = ratingLowerBound;
        this.ratingUpperBound = ratingUpperBound;
        //读入数据集
        try {
            FileReader fileReader = new FileReader(datasetFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            dataset = new Triple[numRating];
            String tempString;
            String[] tempData;
            for (int i = 0; i < numRating; i++) {
                tempString = bufferedReader.readLine();
                tempData = tempString.split(",");
                dataset[i] = new Triple(Integer.parseInt(tempData[0]), Integer.parseInt(tempData[1]),
                        Double.parseDouble(tempData[2]));
            }

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置参数
     *
     * @param paraRank   隐空间维度k
     * @param paraAlpha  梯度下降参数
     * @param paraLambda
     */
    public void setParameters(int paraRank, double paraAlpha, double paraLambda) {
        rank = paraRank;
        alpha = paraAlpha;
        lambda = paraLambda;
    }

    /**
     * 初始化用户子空间和项目子空间，使用随机数填入
     */
    public void initalizeSubspace() {
        //初始化两个子空间
        userSubspace = new Double[numUser][rank];
        itemSubspace = new Double[numItem][rank];
        //用随机数填入
        for (int i = 0; i < numUser; i++) {
            for (int j = 0; j < rank; j++) {
                userSubspace[i][j] = random.nextDouble();
            }
        }

        for (int i = 0; i < numItem; i++) {
            for (int j = 0; j < rank; j++) {
                itemSubspace[i][j] = random.nextDouble();
            }
        }
    }

    /**
     * 使用子空间进行预测用户对项目的评分
     *
     * @param userId 用户ID
     * @param itemId 项目ID
     * @return 预测评分
     */
    public double predict(int userId, int itemId) {
        double result = 0;
        //两个矩阵相乘
        for (int i = 0; i < rank; i++) {
            result += userSubspace[userId][i] * itemSubspace[itemId][i];
        }
        return result;
    }

    /**
     * 按照梯度下降调整子空间
     */
    public void updateNoRegular() {
        for (int i = 0; i < numRating; i++) {
            int tempUserId = dataset[i].user;
            int tempItemId = dataset[i].item;
            double tempRating = dataset[i].rating;
            //计算误差
            double tempError = tempRating - predict(tempUserId, tempItemId);
            //更新矩阵
            //使用梯度下降法
            double tempValue = 0;
            for (int j = 0; j < rank; j++) {
                tempValue = 2 * tempError * itemSubspace[tempItemId][j];
                userSubspace[tempUserId][j] += tempValue * alpha;
            }

            for (int j = 0; j < rank; j++) {
                tempValue = 2 * tempError * userSubspace[tempUserId][j];
                itemSubspace[tempItemId][j] += tempValue * alpha;
            }
        }
    }

    /**
     * 使用已有数据集进行训练
     *
     * @param paraRounds 训练次数
     */
    public void train(int paraRounds) {
        initalizeSubspace();
        for (int i = 0; i < paraRounds; i++) {
            updateNoRegular();
            //输出MAE
            if (i % 50 == 0) {
                System.out.println("Round=" + i);
                System.out.println("MAE=" + MAE());
            }
        }
    }

    /**
     * 计算均方根误差
     *
     * @return 均方根误差
     */
    private double RSME() {
        double resultRSME = 0;
        int tempTestCount = 0;
        for (int i = 0; i < numRating; i++) {
            int tempUserId = dataset[i].user;
            int tempItemId = dataset[i].item;
            double tempRating = dataset[i].rating;
            double tempPredict = predict(tempUserId, tempItemId);
            //防止评分越界
            if (tempPredict < ratingLowerBound) {
                tempPredict = ratingLowerBound;
            } else if (tempPredict > ratingUpperBound) {
                tempPredict = ratingUpperBound;
            }
            double tempError = tempRating - tempPredict;
            resultRSME += tempError * tempError;
            tempTestCount++;
        }
        return Math.sqrt(resultRSME / tempTestCount);
    }

    /**
     * 平均绝对误差
     *
     * @return MAE
     */
    private double MAE() {
        double resultMAE = 0;
        int tempTestCount = 0;
        for (int i = 0; i < numRating; i++) {
            int tempUserId = dataset[i].user;
            int tempItemId = dataset[i].item;
            double tempRating = dataset[i].rating;
            double tempPredict = predict(tempUserId, tempItemId);

            if (tempPredict < ratingLowerBound) {
                tempPredict = ratingLowerBound;
            } else if (tempPredict > ratingUpperBound) {
                tempPredict = ratingUpperBound;
            }
            double tempError = tempRating - tempPredict;
            resultMAE += Math.abs(tempError);
            tempTestCount++;
        }
        return resultMAE / tempTestCount;
    }

    /**
     * 测试算法
     *
     * @param args
     */
    public static void main(String[] args) {
        //计算开始时间
        long startTime = System.currentTimeMillis();
        try {
            // Step 1. 读取数据集
            MatrixFactorization mf = new MatrixFactorization(
                    "E:\\DataSet\\movielens-943u1682m.txt",
                    943, 1682, 10000,
                    1, 5);

            //step 2. 设置参数
            mf.setParameters(5, 0.0001, 0.005);

            // Step 3. 进行训练
            System.out.println("Begin Training ! ! !");
            mf.train(2000);

            //输出相关误差
            double tempMAE = mf.MAE();
            double tempRSME = mf.RSME();
            System.out.println("Finally, MAE = " + tempMAE + ", RSME = " + tempRSME);
            //计算运行时间
            long endTime = System.currentTimeMillis();
            System.out.println("运行时间：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        } // Of try
    }
}
