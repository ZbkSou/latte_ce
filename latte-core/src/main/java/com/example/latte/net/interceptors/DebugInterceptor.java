package com.example.latte.net.interceptors;

import android.support.annotation.RawRes;

import com.example.latte.util.file.FileUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ZBK on 2017-11-26.
 */

public class DebugInterceptor extends BaseInterceptor{
    private final String DEBUG_URl;

    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debug_urL, int debug_raw_id) {
        this.DEBUG_URl = debug_urL;
        this.DEBUG_RAW_ID = debug_raw_id;
    }
    private Response getResponse(Chain chain ,String json){
        return  new Response.Builder()
          .code(200)
          .addHeader("Content-Type","application/json")
          .body(ResponseBody.create(MediaType.parse("application/json"),json))
            .message("OK")
          .request(chain.request())
          .protocol(Protocol.HTTP_1_1)
          .build();
    }
    private Response debugResponse(Chain chain,@RawRes int rawId){
        final  String json = FileUtil.getRawFile(rawId);
        return getResponse(chain,json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if(url.contains(DEBUG_URl)){
            return  debugResponse(chain,DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
