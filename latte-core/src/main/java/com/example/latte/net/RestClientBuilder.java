package com.example.latte.net;

import android.content.Context;

import com.example.latte.net.callback.IError;
import com.example.latte.net.callback.IFailure;
import com.example.latte.net.callback.IRequest;
import com.example.latte.net.callback.ISuccess;
import com.example.latte.ui.loader.LoaderStyle;

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

    private Context mContext;
    //url
    private String mUrl;
    //参数
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    //回调
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    //raw参数
    private RequestBody mBody;

    //loader
    private LoaderStyle mLoaderStyle;
    //上传文件
    private File mFile;
    //下载文件目录 mDownloadDir/mName.mExtension
    private String mDownloadDir = null;
    //拓展名  .png
    private String mExtension = null;
    //文件名 xxx
    private String mName = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 设置参数
     *
     * @param map
     * @return
     */
    public final RestClientBuilder params(WeakHashMap<String, Object> map) {
        PARAMS.putAll(map);
        return this;
    }

    /**
     * 设置参数
     *
     * @param key
     * @param value
     * @return
     */
    public final RestClientBuilder params(String key, Object value) {
        this.PARAMS.put(key, value);
        return this;
    }

    /**
     * 设置上传文件参数
     *
     * @param file
     * @return
     */
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    /**
     * 设置上传文件名参数
     *
     * @param file
     * @return
     */
    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    /**
     * 下载文件文件名
     */
    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    /**
     * 下载文件文件路径
     */
    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    /**
     * 下载文件后缀
     */
    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
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
     *
     * @param iSuccess
     * @return
     */
    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    /**
     * 设置请求失败回调
     *
     * @param iFailure
     * @return
     */
    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    /**
     * 设置请求前后回调
     *
     * @param iRequest
     * @return
     */
    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    /**
     * 设置请求错误回调
     *
     * @param iError
     * @return
     */
    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }


    /**
     * 设置 load
     *
     * @param context
     * @param style
     * @return
     */
    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    /**
     * 使用默认load
     *
     * @param context
     * @return
     */
    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }


    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mDownloadDir, mExtension, mName, mIRequest, mISuccess, mIFailure, mIError, mBody, mFile, mContext, mLoaderStyle);
    }
}
