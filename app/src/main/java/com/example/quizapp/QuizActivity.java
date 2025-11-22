package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView questionText, numberText;
    RadioGroup optionsGroup;
    RadioButton optA, optB, optC, optD;
    Button nextBtn;

    List<QuestionModel> quizQuestions;

    int index = 0, score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // UI Hooks
        questionText = findViewById(R.id.questionText);
        numberText = findViewById(R.id.numberText);
        optionsGroup = findViewById(R.id.radioGroup);

        optA = findViewById(R.id.optionA);
        optB = findViewById(R.id.optionB);
        optC = findViewById(R.id.optionC);
        optD = findViewById(R.id.optionD);

        nextBtn = findViewById(R.id.nextBtn);

        // Load questions from CSV uploaded in MainActivity
        if (MainActivity.questionBank.size() < 30) {
            Toast.makeText(this, "Not enough questions! Upload CSV first.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Randomize question order
        Collections.shuffle(MainActivity.questionBank);

        // Pick first 30
        quizQuestions = MainActivity.questionBank.subList(0, 30);

        loadQuestion();

        nextBtn.setOnClickListener(view -> checkAnswer());
    }

    private void loadQuestion() {
        QuestionModel q = quizQuestions.get(index);

        numberText.setText("Question " + (index + 1) + "/30");

        questionText.setText(q.getQuestion());
        optA.setText(q.getOptionA());
        optB.setText(q.getOptionB());
        optC.setText(q.getOptionC());
        optD.setText(q.getOptionD());

        optionsGroup.clearCheck();
    }

    private void checkAnswer() {

        int selectedId = optionsGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selected = findViewById(selectedId);

        if (selected.getText().toString().equals(quizQuestions.get(index).getCorrectAns())) {
            score++;
        }

        index++;

        if (index < 30) {
            loadQuestion();
        } else {
            Intent i = new Intent(QuizActivity.this, ResultActivity.class);
            i.putExtra("score", score);
            startActivity(i);
            finish();
        }
    }
}
