package com.example.latte.net;

import com.example.latte.net.callback.IError;
import com.example.latte.net.callback.IFailure;
import com.example.latte.net.callback.IRequest;
import com.example.latte.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by ZBK on 2017-11-19.
 */

public class RestClientBuilder {
    private  String mUrl;
    private Map<String, Object> mParams;
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private RequestBody mBody;
    RestClientBuilder(){

    }
    public final RestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }
    public final RestClientBuilder params(Map<String ,Object>map){
        this.mParams = map;
        return this;
    }
    public final RestClientBuilder params(String key,Object value){
        if(mParams == null){
            mParams = new WeakHashMap<>();
        }
        this.mParams.put(key,value);
        return this;
    }

    /**
     * 穿入原始数据
     * @param raw
     * @return
     */
    public final RestClientBuilder raw(String raw){
      this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }
    public final RestClientBuilder success(ISuccess iSuccess ){
        this.mISuccess = iSuccess;
        return this;
    }
    public final RestClientBuilder failure(IFailure iFailure){
        this.mIFailure = iFailure;
        return this;
    }
    public final RestClientBuilder error(IError iError ){
        this.mIError = iError;
        return this;
    }
}
