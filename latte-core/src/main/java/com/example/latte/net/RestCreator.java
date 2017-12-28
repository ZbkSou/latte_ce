package com.example.latte.net;

import android.util.Log;

import com.example.latte.app.ConfigType;
import com.example.latte.app.Latte;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by ZBK on 2017-11-19.
 */

public final class RestCreator {
    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }




    /**
     * okhttp
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        //获取拦截器
        private static final ArrayList<Interceptor> INTERCEPTORS =
           Latte.getConfiguration(ConfigType.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor() {
            Log.d("OkHttpClient",INTERCEPTORS.size()+"");
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }


        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
          .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
          .build();
    }

    /**
     * 初始化retrofit
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL =
            (String) Latte.getConfigurations().get(ConfigType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OKHttpHolder.OK_HTTP_CLIENT)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();
    }


    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
          RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }
}
