package com.zenbarrier.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    View startButton;
    View gameLayout;


    final long GAME_LENGTH = 30;

    public void startGame(View view){
        startButton.setVisibility(View.GONE);
        gameLayout.setVisibility(View.VISIBLE);

        final View retryButton = findViewById(R.id.retryButton);
        retryButton.setVisibility(View.INVISIBLE);

        final TextView timerText = (TextView)findViewById(R.id.timerText);

        createEquation();

        new CountDownTimer(GAME_LENGTH * 1000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(millisUntilFinished/1000 +"s");
            }

            @Override
            public void onFinish() {
                timerText.setText("0s");
                retryButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void submitAnswer(View view){

    }

    void createEquation(){
        Random randomGenerator = new Random();
        int leftAddend = randomGenerator.nextInt(30);
        int rightAddend = randomGenerator.nextInt(30);
        int sum = leftAddend + rightAddend;

        ArrayList answers = new ArrayList();
        answers.add(sum);

        while(answers.size() < 4){
            int offset = randomGenerator.nextInt(21) - 10;
            int wrongAnswer = sum + offset;
            if(!answers.contains(wrongAnswer)){
                answers.add(wrongAnswer);
            }
        }
        Collections.shuffle(answers);

        Button answerButton1 = (Button)findViewById(R.id.answerButton1);
        answerButton1.setText(answers.get(0)+"");
        Button answerButton2 = (Button)findViewById(R.id.answerButton2);
        answerButton2.setText(answers.get(1)+"");
        Button answerButton3 = (Button)findViewById(R.id.answerButton3);
        answerButton3.setText(answers.get(2)+"");
        Button answerButton4 = (Button)findViewById(R.id.answerButton4);
        answerButton4.setText(answers.get(3)+"");

        TextView questionText = (TextView)findViewById(R.id.questionText);
        questionText.setText(leftAddend+" + "+rightAddend);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        gameLayout = findViewById(R.id.gameLayout);

        gameLayout.setVisibility(View.GONE);
        startButton.setVisibility(View.VISIBLE);
    }
}
