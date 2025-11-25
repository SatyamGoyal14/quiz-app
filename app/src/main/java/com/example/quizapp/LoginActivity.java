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

public class LoginActivity extends AppCompatActivity {

    EditText usernameEt, passwordEt;
    Button loginBtn;
    TextView gotoSignup;

    SharedPreferences prefs;
    private static final String PREFS_NAME = "QuizAppPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEt = findViewById(R.id.loginUsername);
        passwordEt = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginBtn);
        gotoSignup = findViewById(R.id.gotoSignup);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        loginBtn.setOnClickListener(v -> {
            String user = usernameEt.getText().toString().trim();
            String pass = passwordEt.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            String storedUser = prefs.getString("username", null);
            String storedHash = prefs.getString("password_hash", null);

            if (storedUser == null || storedHash == null) {
                Toast.makeText(this, "No account found. Please sign up first.", Toast.LENGTH_LONG).show();
                return;
            }

            if (!storedUser.equals(user)) {
                Toast.makeText(this, "Username not found.", Toast.LENGTH_SHORT).show();
                return;
            }

            String hash = sha256(pass);
            if (hash.equals(storedHash)) {
                prefs.edit().putBoolean("is_logged_in", true).apply();
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Incorrect password.", Toast.LENGTH_SHORT).show();
            }
        });

        gotoSignup.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });
    }

    // Simple SHA-256 hashing for storing password safely
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
