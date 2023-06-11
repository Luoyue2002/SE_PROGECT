package com.se.EC.Utils;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.json.JSONObject;

/**
 * @Description : 阿里翻译  描述
 * @program: demo      程序
 * @ClassName AliTranslate.java       类名
 * @author: Mr.Wang               作者
 * @date: 2021-12-18 12:08 // 生成时间
 **/
public class Translate {

    // 使用您的阿里云访问密钥  阿里云账号的AccessKey ID
    static String accessKeyId = "LTAI5tFb1VJAgV4uRNyGz4X1";
    // 使用您的阿里云访问密钥  阿里云账号Access Key Secret
    static String accessKeySecret = "DpzcHiiEIQphpsX6BwnpyyGJar3tQH";

    public static void main(String[] args) {
        String content = "It's a bit chilly today";
        String aliTranslate = aliTranslate(content);
        System.out.println(aliTranslate);
    }

    public static String aliTranslate(String content) {

        // 创建DefaultAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("mt.cn-hangzhou.aliyuncs.com");
        request.setVersion("2018-10-12");
        request.setAction("TranslateGeneral");

        request.putQueryParameter("FormatType", "text");
        request.putQueryParameter("Scene", "general");
        request.putQueryParameter("SourceLanguage", "en");
        request.putQueryParameter("SourceText", content);
        request.putQueryParameter("TargetLanguage", "zh");

        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject jsonObject = new JSONObject(response.getData());
            return jsonObject.getJSONObject("Data").getString("Translated");
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return null;
    }
}
