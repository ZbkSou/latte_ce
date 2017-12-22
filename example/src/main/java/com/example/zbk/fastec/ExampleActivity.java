package com.example.zbk.fastec;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.latte.activites.ProxyActivity;
import com.example.latte.app.Latte;
import com.example.latte.delegate.LatteDelegate;
import com.example.latte.ec.launcher.LauncherDelegate;
import com.example.latte.ec.launcher.LauncherScrollerDelegate;
import com.example.latte.ec.sign.ISignListener;
import com.example.latte.ec.sign.SignUpDelegate;
import com.example.latte.ec.sign.SigninDelegate;
import com.example.latte.ui.launcher.ILauncherListener;
import com.example.latte.ui.launcher.OnLauncherFinishTag;
import com.joanzapata.iconify.widget.IconTextView;

public class ExampleActivity extends ProxyActivity implements ISignListener,ILauncherListener{

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActionBar actionBar = getSupportActionBar();
    if(actionBar!=null){
      actionBar.hide();
    }
    Latte.getConfigurator().withActivity(this);
  }

  @Override
  public LatteDelegate setRootDelegare() {
    return new LauncherDelegate();
  }

  @Override
  public void onSignInSuccess() {
    Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
  }

  @Override
  public void onSignUpSuccess() {
    Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
  }

  @Override
  public void onLauncherFinish(OnLauncherFinishTag tag) {
    switch (tag){
      case SIGNED:
        Toast.makeText(this,"启动结束,登录了",Toast.LENGTH_LONG).show();
        startWithPop(new ExampleDelegate());
        break;
      case NOT_SIGNED:
        Toast.makeText(this,"启动结束,没有登录",Toast.LENGTH_LONG).show();
        startWithPop(new SigninDelegate());
        break;
      default:
        break;
    }
  }
}
