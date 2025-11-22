package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
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
        setContentView(R.layout.activity_main);

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

            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(",");

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

            // Enable StartQuiz after CSV is loaded
            startQuizBtn.setEnabled(true);
            startQuizBtn.setAlpha(1f);

        } catch (Exception e) {
            Toast.makeText(this, "Error loading CSV file!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
