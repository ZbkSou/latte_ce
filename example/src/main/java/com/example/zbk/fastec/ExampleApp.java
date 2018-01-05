package com.example.zbk.fastec;

import android.app.Application;

import com.example.latte.app.Latte;
import com.example.latte.ec.database.DatabaseManger;
import com.example.latte.ec.icon.FontEcModule;
import com.example.latte.net.interceptors.DebugInterceptor;
import com.example.latte.net.interceptors.LoggingInterceptor;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by ZBK on 2017-10-31.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //配置core
        Latte.init(this)
            .withApiHost("http://10.3.201.166:3000/api/")
//      加入字体图标
            .withIcon(new FontAwesomeModule())
            .withIcon(new FontEcModule())
            //添加拦截器主意拦截器顺序
            .withInterceptor(new LoggingInterceptor())
            .withInterceptor(new DebugInterceptor("indexzz", R.raw.test))
//            .withWeChatAppId("xxx")
//            .withWeChatAppSecret("xx")
            .configure();
        //c初始化数据库
        DatabaseManger.getInstance().init(this);
//        初始化
        initStetho();
    }
    private void initStetho(){
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
            .build()
        );
    }
}
