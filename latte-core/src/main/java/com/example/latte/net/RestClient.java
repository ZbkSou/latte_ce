package com.example.latte.net;

import android.content.Context;
import android.test.mock.MockApplication;

import com.example.latte.net.callback.IError;
import com.example.latte.net.callback.IFailure;
import com.example.latte.net.callback.IRequest;
import com.example.latte.net.callback.ISuccess;
import com.example.latte.net.callback.RequsetCallbacks;
import com.example.latte.ui.LatteLoader;
import com.example.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import static com.example.latte.net.HttpMethod.GET;

/**
 * User: bkzhou
 * Date: 2017/11/15
 * Description:网络请求类，完成网络请i去方法
 */
public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private LoaderStyle LOADER_STYLE;
    private Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.BODY = body;
        this.FAILURE = failure;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.URL = url;
        this.ERROR = error;
        this.SUCCESS = success;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    /**
     * 用来构造RestClient
     *
     * @return
     */
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    /**
     * 构造请求
     */
    private void request(HttpMethod method){

        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        //如果存在REQUEST调用onRequestSrart（不在refit请求中）
        if(REQUEST!=null){
            REQUEST.onRequestSrart();
        }
        if(LOADER_STYLE!=null){
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            default:
                break;
        }
        //执行 call
        if(call!=null){
            call.enqueue(getRequestCallback());
        }

    }

    /**
     * 构造回调
     * @return
     */
    private Callback<String> getRequestCallback(){
        return new RequsetCallbacks(REQUEST,SUCCESS,FAILURE,ERROR,LOADER_STYLE);
    }

    /**
     * get 请求
     * 具体使用方式
     */
    public final void get(){
        request(HttpMethod.GET);
    }

    /**
     * post请求
     */
    public final void post(){
        request(HttpMethod.POST);
    }

    /**
     * put 请求
     */
    public final void put(){
        request(HttpMethod.PUT);
    }

    /**
     * delete 请求
     */
    public final void delete(){
        request(HttpMethod.DELETE);
    }


}

