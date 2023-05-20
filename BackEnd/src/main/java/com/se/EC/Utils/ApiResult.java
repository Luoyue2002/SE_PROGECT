package com.se.EC.Utils;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
public class ApiResult<T> implements Serializable {
        private static final long serialVersionUID = 1L;
        /**
         * 成功标志
         */
        private boolean success = true;
        /**
         * 返回处理消息
         */
        private String message = "";
        /**
         * 返回代码
         */
        private Integer code = 200;
        /**
         * 返回数据对象 data
         */
        private T data;
        /**
         * 时间戳
         */
        private long timestamp = System.currentTimeMillis();
        /**
         * 每页显示数量
         */
        private int pageSize;
        /**
         * 当前页
         */
        private int current;
        /**
         * 总页数
         */
        private int total;

        public ApiResult() {
        }

        public ApiResult(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public static <T> ApiResult<T> success() {
            ApiResult<T> apiResult = new ApiResult<>();
            apiResult.setSuccess(true);
            apiResult.setCode(StatusCodeConstant.STATUS_200);
            return apiResult;
        }

        public static <T> ApiResult<T> success(String msg) {
            ApiResult<T> apiResult = new ApiResult<>();
            apiResult.setSuccess(true);
            apiResult.setCode(StatusCodeConstant.STATUS_200);
            apiResult.setData((T) msg);
            apiResult.setMessage(msg);
            return apiResult;
        }

        public static <T> ApiResult<T> success(T data) {
            ApiResult<T> apiResult = new ApiResult<T>();
            apiResult.setSuccess(true);
            apiResult.setCode(StatusCodeConstant.STATUS_200);
            apiResult.setData(data);
            return apiResult;
        }

        public static <T> ApiResult<T> error(String msg, T data) {
            ApiResult<T> apiResult = new ApiResult<T>();
            apiResult.setSuccess(false);
            apiResult.setCode(StatusCodeConstant.STATUS_500);
            apiResult.setMessage(msg);
            apiResult.setData(data);
            return apiResult;
        }

        public static <T> ApiResult<T> error(String msg) {
            return error(StatusCodeConstant.STATUS_500, msg);
        }

        public static <T> ApiResult<T> error(int code, String msg) {
            ApiResult<T> apiResult = new ApiResult<T>();
            apiResult.setCode(code);
            apiResult.setMessage(msg);
            apiResult.setSuccess(false);
            return apiResult;
        }
        public ApiResult<T> error500(String message) {
            this.message = message;
            this.code = StatusCodeConstant.STATUS_500;
            this.success = false;
            return this;
        }

        public boolean isSuccess() {
            return success;
        }
        public void setSuccess(boolean success) {
            this.success = success;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public Integer getCode() {
            return code;
        }
        public void setCode(Integer code) {
            this.code = code;
        }
        public T getResult() {
            return data;
        }
        public void setData(T data) {
            this.data = data;
        }
        public long getTimestamp() {
            return timestamp;
        }
        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
        public int getPageSize() {
            return pageSize;
        }
        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
        public int getCurrent() {
            return current;
        }
        public void setCurrent(int current) {
            this.current = current;
        }
        public int getTotal() {
            return total;
        }
        public void setTotal(int total) {
            this.total = total;
        }

        public ApiResult<T> data(T data){
            this.setData(data);
            return this;
        }
}

