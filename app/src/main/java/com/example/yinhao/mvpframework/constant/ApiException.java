package com.example.yinhao.mvpframework.constant;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * @author flymegoc
 * @date 2017/12/26
 */

public class ApiException extends Exception {
    private static final String TAG = ApiException.class.getSimpleName();
    private static final int BADREQUEST = 400;
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int METHOD_NOT_ALLOWED = 405;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    private final int code;

    public static final int UNKNOWN = 1000;
    public static final int PARSE_ERROR = 1001;
    private String message;

    private ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
        this.message = throwable.getMessage();
    }

    public int getCode() {
        return code;
    }


    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ApiException(httpException, httpException.code());
            ex.message = httpException.getMessage();
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof JsonSerializer
                || e instanceof NotSerializableException
                || e instanceof ParseException) {
            ex = new ApiException(e, Error.PARSE_ERROR);
            ex.message = "数据解析错误";
            return ex;
        } else if (e instanceof ClassCastException) {
            ex = new ApiException(e, Error.CAST_ERROR);
            ex.message = "类型转换错误";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ApiException(e, Error.NETWORD_ERROR);
            ex.message = "连接失败";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ApiException(e, Error.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else if (e instanceof ConnectTimeoutException) {
            ex = new ApiException(e, Error.TIMEOUT_ERROR);
            ex.message = "网络连接超时";
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new ApiException(e, Error.TIMEOUT_ERROR);
            ex.message = "网络连接超时";
            return ex;
        } else if (e instanceof UnknownHostException) {
            ex = new ApiException(e, Error.UNKNOWNHOST_ERROR);
            ex.message = "无法解析该域名";
            return ex;
        } else if (e instanceof NullPointerException) {
            ex = new ApiException(e, Error.NULLPOINTER_EXCEPTION);
            ex.message = "NullPointerException";
            return ex;
        } else {
            ex = new ApiException(e, Error.UNKNOWN);
            ex.message = "未知错误：" + e.getMessage();
            return ex;
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 约定异常
     */
    public static class Error {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = UNKNOWN + 1;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = PARSE_ERROR + 1;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = NETWORD_ERROR + 1;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = HTTP_ERROR + 1;

        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = SSL_ERROR + 1;

        /**
         * 调用错误
         */
        public static final int INVOKE_ERROR = TIMEOUT_ERROR + 1;
        /**
         * 类转换错误
         */
        public static final int CAST_ERROR = INVOKE_ERROR + 1;
        /**
         * 请求取消
         */
        public static final int REQUEST_CANCEL = CAST_ERROR + 1;
        /**
         * 未知主机错误
         */
        public static final int UNKNOWNHOST_ERROR = REQUEST_CANCEL + 1;

        /**
         * 空指针错误
         */
        public static final int NULLPOINTER_EXCEPTION = UNKNOWNHOST_ERROR + 1;
        /**
         * 解析视频链接错误
         */
        private static final int PARSE_VIDEO_URL_ERROR = NULLPOINTER_EXCEPTION + 1;
        /**
         * 解析视频链接错误
         */
        private static final int FAVORITE_VIDEO_ERROR = PARSE_VIDEO_URL_ERROR + 1;

        private static final int GREEN_DAO_ERROR = FAVORITE_VIDEO_ERROR + 1;

        private static final int COMMON_MESSAGE_ERROR = GREEN_DAO_ERROR + 1;
    }
}
