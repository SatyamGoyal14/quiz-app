package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView questionText, numberText;
    RadioGroup optionsGroup;
    RadioButton optA, optB, optC, optD;
    Button nextBtn;
    ProgressBar progressBar;

    List<QuestionModel> quizQuestions = new ArrayList<>();
    int index = 0, score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        numberText = findViewById(R.id.numberText);
        optionsGroup = findViewById(R.id.radioGroup);

        optA = findViewById(R.id.optionA);
        optB = findViewById(R.id.optionB);
        optC = findViewById(R.id.optionC);
        optD = findViewById(R.id.optionD);

        nextBtn = findViewById(R.id.nextBtn);
        progressBar = findViewById(R.id.progressBar);

        Collections.shuffle(MainActivity.questionBank);
        quizQuestions = MainActivity.questionBank.subList(0, 30);

        loadQuestion();

        nextBtn.setOnClickListener(view -> checkAnswer());
    }


    // ⭐ Smooth Animated Progress Bar ⭐
    private void animateProgressBar(int from, int to) {
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", from, to);
        animation.setDuration(500);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
    }


    private void loadQuestion() {
        QuestionModel q = quizQuestions.get(index);

        numberText.setText("Question " + (index + 1) + "/30");

        // animate progress
        int oldProgress = progressBar.getProgress();
        int newProgress = index + 1;
        animateProgressBar(oldProgress, newProgress);

        questionText.setText(q.getQuestion());
        optA.setText(q.getOptionA());
        optB.setText(q.getOptionB());
        optC.setText(q.getOptionC());
        optD.setText(q.getOptionD());

        // Clear previous selection
        optionsGroup.clearCheck();

        // Fade in question card smoothly
        View card = findViewById(R.id.questionCard);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        card.startAnimation(a);
    }


    private void checkAnswer() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selected = findViewById(selectedId);

        if (selected.getText().toString()
                .equals(quizQuestions.get(index).getCorrectAns())) {
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
