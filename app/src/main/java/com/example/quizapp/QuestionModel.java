package com.example.quizapp;

public class QuestionModel {
    private String question, optionA, optionB, optionC, optionD, correctAns;

    public QuestionModel(String q, String a, String b, String c, String d, String ans) {
        question = q;
        optionA = a;
        optionB = b;
        optionC = c;
        optionD = d;
        correctAns = ans;
    }

    public String getQuestion() { return question; }
    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }
    public String getOptionD() { return optionD; }
    public String getCorrectAns() { return correctAns; }
}
