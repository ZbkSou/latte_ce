package com.example.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.latte.app.Latte;
import com.example.latte.delegate.LatteDelegate;
import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte.net.RestClient;
import com.example.latte.net.callback.ISuccess;
import com.example.latte.util.logger.LatteLogger;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * User: bkzhou
 * Date: 2017/12/11
 * Description:
 */
public class SignUpDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText editSignUpName;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText editSignUpPhone;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText editSignUpEmail;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText editSignUpPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText editSignUpRePassword;
    @BindView(R2.id.btn_sign_up)
    AppCompatButton btnSignUp;
    @BindView(R2.id.tv_link_sign_in)
    AppCompatTextView tvLinkSignIn;

    private ISignListener mISignListener;
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        if(activity instanceof ISignListener){
            mISignListener = (ISignListener)activity;
        }
    }
    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        if(checkForm()){
            RestClient.builder()
                .url("http://10.3.201.166:3000/api/user_profile")
                .params("name",editSignUpName.getText().toString())
                .params("email",editSignUpEmail.getText().toString())
                .params("phone",editSignUpEmail.getText().toString())
                .params("password",editSignUpPassword.getText().toString())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        LatteLogger.json("USER_PROFILE",response);
                        SignHandler.onSignUp(response,mISignListener);
                    }
                })
                .build()
                .post();
            Toast.makeText(getContext(),"成功",Toast.LENGTH_LONG).show();
        }
    }
    @OnClick(R2.id.tv_link_sign_in)
    void onClickSignInLink(){
        start(new SigninDelegate());
    }

    /**
     * 校验
     */
    private boolean checkForm(){
        final String name = editSignUpName.getText().toString();
        final String email = editSignUpEmail.getText().toString();
        final String phone = editSignUpPhone.getText().toString();
        final String password = editSignUpPassword.getText().toString();
        final String repassword = editSignUpRePassword.getText().toString();

        boolean isPass = true;
        if(name.isEmpty()){
            editSignUpName.setError("请输入姓名");
            isPass = false;
        }else {
            editSignUpName.setError(null);
        }
        if(email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editSignUpEmail.setError("错误的邮箱格式");
            isPass = false;
        }else {
            editSignUpEmail.setError(null);
        }
        if(phone.isEmpty()||phone.length()!=11){
            editSignUpPhone.setError("手机格式错误");
            isPass = false;
        }else {
            editSignUpPhone.setError(null);
        }
        if(password.isEmpty()||password.length()<6){
            editSignUpPassword.setError("密码格式错误");
            isPass = false;
        }else {
            editSignUpPassword.setError(null);
        }
        LatteLogger.d("repassword",repassword +password);
        if(repassword.isEmpty()||repassword.length()<6||(!repassword.equals(password))){
            editSignUpRePassword.setError("密码格式错误");
            isPass = false;
        }else {
            editSignUpRePassword.setError(null);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


}
