package com.example.latte.net;

import android.content.Context;

import com.example.latte.net.callback.IError;
import com.example.latte.net.callback.IFailure;
import com.example.latte.net.callback.IRequest;
import com.example.latte.net.callback.ISuccess;
import com.example.latte.ui.LatteLoader;
import com.example.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by ZBK on 2017-11-19.
 * RestClient的建造者
 * RestClient.builder()
 * .url("")
 * .params("","")
 * .success(new ISuccess() {
 *
 * @Override public void onSuccess(String response) {
 * <p>
 * }
 * })
 * .failure(new IFailure() {
 * @Override public void onFailure() {
 * <p>
 * }
 * })
 * .error(new IError() {
 * @Override public void onError(int code, String msg) {
 * <p>
 * }
 * })
 * .build()
 * .get/post/put/detele
 */

public class RestClientBuilder {
    private String mUrl;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private RequestBody mBody;
    private Context mContext;
    private LoaderStyle mLoaderStyle;
    private File mFile;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 设置参数
     * @param map
     * @return
     */
    public final RestClientBuilder params(WeakHashMap<String, Object> map) {
        PARAMS.putAll(map);
        return this;
    }

    /**
     * 设置参数
     * @param key
     * @param value
     * @return
     */
    public final RestClientBuilder params(String key, Object value) {
        this.PARAMS.put(key, value);
        return this;
    }

    /**
     * 设置参数
     * @param file
     * @return
     */
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }
    /**
     * 设置参数
     * @param file
     * @return
     */
    public final RestClientBuilder file(String  file) {
        this.mFile = new File(file);
        return this;
    }

    /**
     * 穿入初始数据 bady
     *
     * @param raw
     * @return
     */
    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    /**
     * 成功回调
     * @param iSuccess
     * @return
     */
    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    /**
     * 设置请求失败回调
     * @param iFailure
     * @return
     */
    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }
    /**
     * 设置请求前后回调
     * @param iRequest
     * @return
     */
    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    /**
     * 设置请求错误回调
     * @param iError
     * @return
     */
    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }


    /**
     * 设置 load
     * @param context
     * @param style
     * @return
     */
    public final RestClientBuilder loader(Context  context,LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    /**
     * 使用默认load
     * @param context
     * @return
     */
    public final RestClientBuilder loader(Context  context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }


    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure, mIError, mBody,mFile,mContext,mLoaderStyle);
    }
}
