package com.example.latte.net;

import android.test.mock.MockApplication;

import com.example.latte.net.callback.IError;
import com.example.latte.net.callback.IFailure;
import com.example.latte.net.callback.IRequest;
import com.example.latte.net.callback.ISuccess;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * User: bkzhou
 * Date: 2017/11/15
 * Description:
 */
public class RestClient {

    private final String URL;
    private final Map<String, Object> PARAMS;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final RequestBody BODY;

    public RestClient(String URL,
                      Map<String, Object> PARAMS,
                      IRequest REQUEST,
                      ISuccess SUCCESS,
                      IFailure FAILURE,
                      IError ERROR,
                      RequestBody BODY) {
        this.BODY = BODY;
        this.FAILURE = FAILURE;
        this.PARAMS = PARAMS;
        this.REQUEST = REQUEST;
        this.URL = URL;
        this.SUCCESS = SUCCESS;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


}
