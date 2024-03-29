package com.enokoo.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
TextView totalquiz;
TextView quiztv;
Button submitBtn;
Button ansA,ansB,ansC,ansD;
int score=0;
int totalQuestion=QuestionAnswer.question.length;
int currentQuestionIndex=0;
String selectedAnswer="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalquiz=findViewById(R.id.total_quiz);
        quiztv=findViewById(R.id.question);
        submitBtn=findViewById(R.id.submit_btn);
        ansA=findViewById(R.id.ans_A);
        ansB=findViewById(R.id.ans_B);
        ansC=findViewById(R.id.ans_C);
        ansD=findViewById(R.id.ans_D);
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        totalquiz.setText("Total Questions is : " + totalQuestion);
        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);
Button clickedButton=(Button) view;
if(clickedButton.getId() == R.id.submit_btn){
    if (selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])) {
        score ++;
    }
    currentQuestionIndex ++;
    loadNewQuestion();

}else{
    selectedAnswer=clickedButton.getText().toString();
    clickedButton.setBackgroundColor(Color.BLUE);
}
    }
    void loadNewQuestion(){
        if (currentQuestionIndex==totalQuestion){
            finishQuiz();
            return;
        }
        quiztv.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }
    void finishQuiz(){
        String success="";
        if(score > totalQuestion * 0.5){
            success = "You passed the Test.";
        }
        else{
            success="You Failed the Test. Please Repeat!";
        }
        new AlertDialog.Builder(this)
                .setTitle(success)
                .setMessage("Score is" + score + " out of" + totalQuestion)
                .setPositiveButton("Restart", (dialog, which) -> restartQuiz())
                .setCancelable(false)
                .show();
    }
    void restartQuiz(){
       score=0;
       currentQuestionIndex =0;
       loadNewQuestion();
    }
}