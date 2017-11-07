package com.example.zbk.fastec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.latte.app.Latte;
import com.joanzapata.iconify.widget.IconTextView;

public class MainActivity extends AppCompatActivity {

  private IconTextView textView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    textView = (IconTextView) findViewById(R.id.icon);
//    textView.setText("\uF000");
    Toast.makeText(Latte.getApplication(),"穿入context",Toast.LENGTH_LONG).show();
  }
}
