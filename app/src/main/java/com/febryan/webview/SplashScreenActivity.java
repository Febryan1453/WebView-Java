package com.febryan.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.febryan.webview.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.lottieAnim.setAnimation("box.json");
        binding.lottieAnim.playAnimation();

        SessionManager sessionManager = new SessionManager(SplashScreenActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent;

                if (sessionManager.isLogin()){
                    intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                }else {
                    intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                }

                startActivity(intent);
                finish();

            }
        }, 4000);

    }
}