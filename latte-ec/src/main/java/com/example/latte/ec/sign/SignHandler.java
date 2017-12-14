package com.example.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.latte.ec.database.DatabaseManger;
import com.example.latte.ec.database.UserProfile;


/**
 * User: bkzhou
 * Date: 2017/12/13
 * Description:
 */
public class SignHandler {
    public static void onSignUp(String response){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        final UserProfile profile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManger.getInstance().getDao().insert(profile);
    }
}
