package com.example.latte.app;

import android.app.Activity;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * Created by ZBK on 2017-10-30.
 * Description: 配置类,保存配置信息,通过Latter 调用
 */

public class Configurator {
  //WeakHashMap键值对在不使用的时候会回收,存储大量数据时试用
  private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
  //用来存储字体图标
  private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
//配置连接器
  private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

  private static final Handler HANDLER = new Handler();
  private Configurator() {
    //踢出配置完成,即配置尚未完成
    LATTE_CONFIGS.put(ConfigType.CONFIG_READY, false);
    LATTE_CONFIGS.put(ConfigType.HANDLER, HANDLER);
  }

  //静态内部类+ getIinstance  = 完美懒汉单利
  public static Configurator getInstance() {
    return Holder.INSTANCE;
  }

  final HashMap<Object, Object> getLatteConfigs() {
    return LATTE_CONFIGS;
  }

  //通过静态内部类完成懒汉单利,(多线程懒汉单利必出问题需要同步锁,枚举类处理)
  private static class Holder {
    private static final Configurator INSTANCE = new Configurator();
  }

  /**
   *  配置完成
   */
  public final void configure() {
    //统一初始化图标
    initIcons();
    LATTE_CONFIGS.put(ConfigType.CONFIG_READY, true);
  }



  /**
   * 检查配置完成,未完成报错
   */
  private void  checkConfiguration(){
    final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY);
    if(!isReady){
      throw  new RuntimeException("Configuration is not ready ,call configure!!");
    }
  }

  /**
   * 获取配置
   * 配置前检查配置是否完成
   * @param key
   * @param <T>
   * @return
   */
  @SuppressWarnings("unchecked")
  final <T> T getConfiguration(Object key){
    checkConfiguration();
    final Object value = LATTE_CONFIGS.get(key);
    if (value == null) {
      throw new NullPointerException(key.toString() + " IS NULL");
    }
    return (T) LATTE_CONFIGS.get(key);
  }

  /**
   * 初始化图标
   * 用来在配置完成时调用初始化所有 icon
   */
  private void initIcons(){
    if(ICONS.size()>0){
      final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
      for(int i =1 ; i<ICONS.size();i++){
        initializer.with(ICONS.get(i));
      }
    }
  }

  /**
   * 配置 icon
   * @param descriptor
   * @return
   */
  public final Configurator withIcon(IconFontDescriptor descriptor){
    ICONS.add(descriptor);
    return this;
  }

  /**
   * 配置拦截器
   * @param interceptor
   * @return
     */
  public final Configurator withInterceptor(Interceptor interceptor){
    INTERCEPTORS.add(interceptor);
    LATTE_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
    return this;
  }
  /**
   * 配置拦截器
   * @param interceptors
   * @return
   */
  public final Configurator withInterceptor(ArrayList<Interceptor> interceptors){
    INTERCEPTORS.addAll(interceptors);
    LATTE_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
    return this;
  }


  /**
   *   配置host
   */
  public final  Configurator withApiHost(String host){
    LATTE_CONFIGS.put(ConfigType.API_HOST,host);
    return this;
  }
  /**
   *   配置wechat app id
   */
  public final  Configurator withWeChatAppId(String appId){
    LATTE_CONFIGS.put(ConfigType.WE_CHAT_APP_ID,appId);
    return this;
  }
  /**
   *   配置wechat app secret
   */
  public final  Configurator withWeChatAppSecret(String secret){
    LATTE_CONFIGS.put(ConfigType.WE_CHAT_APP_SECRET,secret);
    return this;
  }

  public final  Configurator withActivity(Activity activity ){
    LATTE_CONFIGS.put(ConfigType.ACTIVITY,activity);
    return this;
  }

}
