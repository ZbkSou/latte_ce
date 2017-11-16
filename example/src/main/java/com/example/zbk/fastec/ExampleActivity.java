package com.example.zbk.fastec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.latte.activites.ProxyActivity;
import com.example.latte.app.Latte;
import com.example.latte.delegate.LatteDelegate;
import com.joanzapata.iconify.widget.IconTextView;

public class ExampleActivity extends ProxyActivity{


  @Override
  public LatteDelegate setRootDelegare() {
    return new ExampleDelegate();
  }
}
