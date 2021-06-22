package com.febryan.webview;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SessionManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Context context;

    // 2. Langkah kedua
    private String keyUsername = "username";
    private String keyEmail = "email";

    // 1. Buat Konstruktor
    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("id.webview.session-manager", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // 3. Langkah ketiga
    public void sessionLogin(String username, String email){
        editor.putString(keyUsername, username);
        editor.putString(keyEmail, email);
        editor.apply();
    }

    // 4. Langkah keempat
    public void sessionLogout(){
        editor.remove(keyUsername);
        editor.remove(keyEmail);
        editor.apply();
    }

//    // 5. Langkah kelima
    public String getUsername(){
        return sharedPreferences.getString(keyUsername, null);
    }

    public String getEmail(){
        return sharedPreferences.getString(keyEmail, null);
    }

    public boolean isLogin(){
        return !TextUtils.isEmpty(getUsername()) && !TextUtils.isEmpty(getEmail());
    }
}
