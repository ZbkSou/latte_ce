package com.example.latte.net.interceptors;

import android.util.Log;

import com.example.latte.util.logger.LatteLogger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpEngine;
import okio.Buffer;
import okio.BufferedSource;

/**
 * User: bkzhou
 * Date: 2017/11/30
 * Description:
 */
public class LoggingInterceptor extends BaseInterceptor{
    private final String TAG = "LoggingInterceptor";
    private final Charset UTF8 = Charset.forName("UTF-8");
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody requestBody = request.body();
        String body = null;
        if(requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            body = buffer.readString(charset);
        }
        Log.e(TAG,"发送请求\nmethod："+ request.method()+
                "\nurl："+request.url()+
                "\nheaders: "+request.headers()+
                "\nbody："+body);
        long startNs = System.nanoTime();
        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        ResponseBody responseBody = response.body();
        String rBody = null;
        if(HttpEngine.hasBody(response)) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    e.printStackTrace();
                }
            }
            rBody = buffer.clone().readString(charset);
        }
        Log.e(TAG,"收到响应: "+response.code()+response.message()+"耗时:"+tookMs+"ms" +
                "\n请求url:" +response.request().url()+
                "\n请求body:"+body+
                "\n响应body:"+rBody);

        return response;

    }
}
