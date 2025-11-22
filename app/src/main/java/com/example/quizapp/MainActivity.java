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

    // Store all questions here
    public static ArrayList<QuestionModel> questionBank = new ArrayList<>();

    private static final int PICK_CSV = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startQuizBtn = findViewById(R.id.startQuizBtn);
        uploadCsvBtn = findViewById(R.id.uploadCsvBtn);

        // Upload CSV button
        uploadCsvBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");

            // Enable CSV files on all phones
            intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{
                    "text/csv",
                    "text/comma-separated-values",
                    "application/csv",
                    "application/vnd.ms-excel",
                    "text/plain"
            });

            startActivityForResult(intent, PICK_CSV);
        });

        // Start Quiz button
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

    // Handle selected CSV file
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CSV && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            readCsvFile(uri);
        }
    }

    // Read the CSV file
    private void readCsvFile(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            questionBank.clear();

            String line;

            // Skip header
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                // Split CSV values
                String[] cols = line.split(",");

                // Check we have 6 columns
                if (cols.length == 6) {
                    questionBank.add(new QuestionModel(
                            cols[0].trim(),  // question
                            cols[1].trim(),  // A
                            cols[2].trim(),  // B
                            cols[3].trim(),  // C
                            cols[4].trim(),  // D
                            cols[5].trim()   // correct
                    ));
                }
            }

            reader.close();

            Toast.makeText(this,
                    "CSV Imported Successfully! Loaded: " + questionBank.size() + " questions",
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this,
                    "Error loading CSV file!",
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
