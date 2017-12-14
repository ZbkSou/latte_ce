package com.example.latte.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.latte.delegate.LatteDelegate;
import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * User: bkzhou
 * Date: 2017/12/12
 * Description:
 */
public class SigninDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText editSignInEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText editSignInPassword;
    @BindView(R2.id.btn_sign_in)
    AppCompatButton btnSignIn;
    @BindView(R2.id.tv_link_sign_up)
    AppCompatTextView tvLinkSignUp;
    @BindView(R2.id.icon_sign_in_wechat)
    IconTextView iconSignInWechat;


    

    /**
     * 校验
     */
    private boolean checkForm(){
        final String email = editSignInEmail.getText().toString();
        final String password = editSignInPassword.getText().toString();
        boolean isPass = true;
        if(email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editSignInEmail.setError("错误的邮箱格式");
            isPass = false;
        }else {
            editSignInEmail.setError(null);
        }

        if(password.isEmpty()||password.length()<6){
            editSignInPassword.setError("密码格式错误");
            isPass = false;
        }else {
            editSignInPassword.setError(null);
        }
     return isPass;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
    @OnClick({R2.id.btn_sign_in, R2.id.tv_link_sign_up, R2.id.icon_sign_in_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.btn_sign_in:
                if(checkForm()){

                }
                break;
            case R2.id.tv_link_sign_up:
                start(new SignUpDelegate());
                break;
            case R2.id.icon_sign_in_wechat:
                break;
        }
    }
}
