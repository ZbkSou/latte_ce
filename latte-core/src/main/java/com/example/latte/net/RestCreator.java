package com.example.latte.net;

import com.example.latte.app.ConfigType;
import com.example.latte.app.Latte;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by ZBK on 2017-11-19.
 */

public class RestCreator {
  public static  RestService getRestService(){
    return RestServiceHolder.REST_SERVICE;
  }
  /**
   * 初始化retrofit
   */
  private static final class RetrofitHolder{
    private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST);
    private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(ScalarsConverterFactory.create())
      .build();
  }

  /**
   * okhttp
   */
  private static final  class OKHttpHolder{
    private  static  final int TIME_OUT=60;
    private static  final  OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
      .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
      .build();
  }
  private static final class RestServiceHolder{
    private static final RestService REST_SERVICE =
      RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
  }


}
