package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.security.MessageDigest;

public class SignupActivity extends AppCompatActivity {

    EditText usernameEt, passwordEt, confirmEt;
    Button signupBtn;
    TextView gotoLogin;

    SharedPreferences prefs;
    private static final String PREFS_NAME = "QuizAppPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameEt = findViewById(R.id.signupUsername);
        passwordEt = findViewById(R.id.signupPassword);
        confirmEt = findViewById(R.id.signupConfirm);
        signupBtn = findViewById(R.id.signupBtn);
        gotoLogin = findViewById(R.id.gotoLogin);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        signupBtn.setOnClickListener(v -> {
            String user = usernameEt.getText().toString().trim();
            String pass = passwordEt.getText().toString();
            String conf = confirmEt.getText().toString();

            if (user.isEmpty() || pass.isEmpty() || conf.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass.equals(conf)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Store username & hashed password
            String hash = sha256(pass);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("username", user);
            editor.putString("password_hash", hash);
            editor.putBoolean("is_logged_in", true); // auto login after signup
            editor.apply();

            Toast.makeText(this, "Signup successful! Logged in.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignupActivity.this, MainActivity.class));
            finish();
        });

        gotoLogin.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });
    }

    private String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            ex.printStackTrace();
            return "";
        }
    }
}
