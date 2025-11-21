package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView scoreText, messageText;
    Button restartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreText = findViewById(R.id.scoreText);
        messageText = findViewById(R.id.messageText);
        restartBtn = findViewById(R.id.restartBtn);

        int score = getIntent().getIntExtra("score", 0);

        scoreText.setText("Your Score: " + score + "/30");

        if (score >= 25)
            messageText.setText("Excellent! 🎉");
        else if (score >= 15)
            messageText.setText("Good job! 👍");
        else
            messageText.setText("Keep Practicing 😄");

        restartBtn.setOnClickListener(v ->
                startActivity(new Intent(ResultActivity.this, MainActivity.class)));
    }
}
