package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button startQuizBtn, uploadCsvBtn;
    public static ArrayList<QuestionModel> questionBank = new ArrayList<>();

    private static final int PICK_CSV = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 🔐 LOGIN CHECK: If user is NOT logged in → go to LoginActivity
        SharedPreferences prefs = getSharedPreferences("QuizAppPrefs", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("is_logged_in", false);

        if (!isLoggedIn) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        // 🌈 Optional: Animated background if your layout uses it
        LinearLayout root = findViewById(R.id.mainRoot);
        if (root != null && root.getBackground() instanceof AnimationDrawable) {
            AnimationDrawable anim = (AnimationDrawable) root.getBackground();
            anim.setEnterFadeDuration(1500);
            anim.setExitFadeDuration(1500);
            anim.start();
        }

        startQuizBtn = findViewById(R.id.startQuizBtn);
        uploadCsvBtn = findViewById(R.id.uploadCsvBtn);

        // Disable Start Quiz until CSV uploaded
        startQuizBtn.setEnabled(false);
        startQuizBtn.setAlpha(0.5f);

        uploadCsvBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");

            intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{
                    "text/csv",
                    "text/comma-separated-values",
                    "application/csv",
                    "application/vnd.ms-excel",
                    "text/plain"
            });

            startActivityForResult(intent, PICK_CSV);
        });

        startQuizBtn.setOnClickListener(v -> {
            if (questionBank.size() < 30) {
                Toast.makeText(this,
                        "Please upload a CSV with at least 30 questions.",
                        Toast.LENGTH_LONG).show();
                return;
            }
            startActivity(new Intent(MainActivity.this, QuizActivity.class));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CSV && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            readCsvFile(uri);
        }
    }

    private void readCsvFile(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            questionBank.clear();
            String line;

            reader.readLine(); // Skip header row

            while ((line = reader.readLine()) != null) {

                // Fix CSV splitting to handle commas safely
                String[] cols = line.split(",", -1);

                if (cols.length == 6) {
                    questionBank.add(new QuestionModel(
                            cols[0].trim(),
                            cols[1].trim(),
                            cols[2].trim(),
                            cols[3].trim(),
                            cols[4].trim(),
                            cols[5].trim()
                    ));
                }
            }

            reader.close();

            Toast.makeText(this,
                    "CSV Imported Successfully! Loaded: " + questionBank.size() + " questions",
                    Toast.LENGTH_LONG).show();

            startQuizBtn.setEnabled(true);
            startQuizBtn.setAlpha(1f);

        } catch (Exception e) {
            Toast.makeText(this,
                    "Error loading CSV file! Please check format.",
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
