package com.se.EC.Utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public interface StatusCodeConstant {
        /**
         * 正常状态
         */
        Integer STATUS_NORMAL = 0;
        /**
         * {@code 200 OK} (HTTP/1.0 - RFC 1945)
         * 请求成功。一般用于GET与POST请求
         */
        Integer STATUS_200 = 200;
        /**
         * Found
         */
        Integer STATUS_302 = 302;
        /**
         * Bad Request 客户端请求的语法错误，服务器无法理解
         */
        Integer STATUS_400 = 400;
        /**
         * 401 Unauthorized 请求要求用户的身份认证
         */
        Integer STATUS_401 = 401;
        /**
         * Forbidden 服务器理解请求客户端的请求，但是拒绝执行此请求
         */
        Integer STATUS_403 = 403;
        /**
         * Not Found 服务器无法根据客户端的请求找到资源（网页）。通过此代码，网站设计人员可设
         置"您所请求的资源无法找到"的个性页面
         */
        Integer STATUS_404 = 404;
        /**
         * Method Not Allowed 客户端请求中的方法被禁止
         */
        Integer STATUS_405 = 405;
        /**
         * {@code 500 Internal Server Error} (HTTP/1.0 - RFC 1945)
         * 服务器内部错误，无法完成请求
         */
        Integer STATUS_500 = 500;
    }

