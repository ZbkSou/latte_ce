package com.example.latte.net;

import android.content.Context;

import com.example.latte.net.callback.IError;
import com.example.latte.net.callback.IFailure;
import com.example.latte.net.callback.IRequest;
import com.example.latte.net.callback.ISuccess;
import com.example.latte.net.callback.RequsetCallbacks;
import com.example.latte.net.download.DownloadHandler;
import com.example.latte.ui.loader.LatteLoader;
import com.example.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;


/**
 * User: bkzhou
 * Date: 2017/11/15
 * Description:网络请求类，完成网络请i去方法
 * RestClient.builder()
 * .url("https://api.douban.com/v2/book/1220562")
 * .loader(getContext())
 * .success(new ISuccess() {
 * @Override public void onSuccess(String response) {
 * Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
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
 * .build()//完成构建
 * .get();//调用请求
 */
public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final File FILE;
    public RestClient(String url,
                      Map<String, Object> params,
                     String downloadDir,
                      String extension,
                      String name,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
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
        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
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
    private void request(HttpMethod method) {

        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        //如果存在REQUEST调用onRequestSrart（不在refit请求中）
        if (REQUEST != null) {
            REQUEST.onRequestSrart();
        }
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                  RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body =
                  MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call =service.upload(URL,body);
                break;
            default:
                break;
        }
        //执行 call
        if (call != null) {
            //异步请求
            call.enqueue(getRequestCallback());
        }

    }

    /**
     * 构造回调
     *
     * @return
     */
    private Callback<String> getRequestCallback() {
        return new RequsetCallbacks(REQUEST, SUCCESS, FAILURE, ERROR, LOADER_STYLE);
    }

    /**
     * get 请求
     * 具体使用方式
     */
    public final void get() {
        request(HttpMethod.GET);
    }

    /**
     * post请求
     */
    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must  be null ");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    /**
     * put 请求
     */
    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must  be null ");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    /**
     * delete 请求
     */
    public final void delete() {
        request(HttpMethod.DELETE);
    }

    /**
     * 上传文件
     */
    public final void upload(){
        request(HttpMethod.UPLOAD);
    }

    /**
     *
     */
    public final void download(){
        new DownloadHandler(URL,REQUEST,DOWNLOAD_DIR,
          EXTENSION,NAME,SUCCESS,FAILURE,ERROR).handleDownload();

    }


}

