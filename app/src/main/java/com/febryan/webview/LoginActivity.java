package com.febryan.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.febryan.webview.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SessionManager sessionManager = new SessionManager(LoginActivity.this);

        binding.btnLogin.setOnClickListener(v -> {

            String username = binding.edtUsername.getText().toString();
            String email = binding.edtEmail.getText().toString();

            if (username.isEmpty()){
                Toast.makeText(this, "Harap masukkan username", Toast.LENGTH_SHORT).show();
            }
            if (email.isEmpty()){
                Toast.makeText(this, "Harap masukkan email", Toast.LENGTH_SHORT).show();
            }

            if (username.equals("Febryan") && email.equals("ryan@gmail.com")){

                //Input data ke session
                sessionManager.sessionLogin(username,email);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }else {
                Toast.makeText(this, "Kesalahan Auth", Toast.LENGTH_SHORT).show();
            }

        });

    }
}