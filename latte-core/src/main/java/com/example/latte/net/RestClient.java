package com.example.latte.net;

import android.test.mock.MockApplication;

import com.example.latte.net.callback.IError;
import com.example.latte.net.callback.IFailure;
import com.example.latte.net.callback.IRequest;
import com.example.latte.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * User: bkzhou
 * Date: 2017/11/15
 * Description:
 */
public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body) {
        this.BODY = body;
        this.FAILURE = failure;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.URL = url;
        this.ERROR = error;
        this.SUCCESS = success;
    }

    /**
     * 用来构造RestClient
     *
     * @return
     */
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


}
