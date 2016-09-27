package com.zenbarrier.braintrainer;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    View startButton;
    View gameLayout;

    int sum;
    int questionCount;
    int score;

    boolean isGameInProgress;

    final long GAME_LENGTH = 30;

    public void startGame(View view){
        startButton.setVisibility(View.GONE);
        gameLayout.setVisibility(View.VISIBLE);
        final TextView feedbackText = (TextView)findViewById(R.id.feedbackText);
        feedbackText.setText("");

        ((TextView)findViewById(R.id.scoreText)).setText("");

        isGameInProgress = true;

        score = 0;
        questionCount = 0;

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
                isGameInProgress = false;
                int percentRight = (int)((double)score/questionCount*100);
                feedbackText.setText("Your score: "+ percentRight +"%");
                feedbackText.setTextColor(Color.DKGRAY);
                feedbackText.setAlpha(1f);
                retryButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void submitAnswer(View view){
        if(isGameInProgress) {
            int submittedAnswer = Integer.parseInt(((Button) view).getText().toString());
            questionCount++;
            TextView feedbackText = (TextView) findViewById(R.id.feedbackText);
            if (submittedAnswer == sum) {
                score++;
                feedbackText.setText("Correct!");
                feedbackText.setTextColor(Color.GREEN);
            } else {
                feedbackText.setText("Wrong");
                feedbackText.setTextColor(Color.RED);
            }
            feedbackText.setAlpha(1f);
            feedbackText.animate().alpha(0f).setDuration(1000);

            TextView scoreText = (TextView) findViewById(R.id.scoreText);
            scoreText.setText(score + "/" + questionCount);

            createEquation();
        }
    }

    void createEquation(){
        Random randomGenerator = new Random();
        int leftAddend = randomGenerator.nextInt(30);
        int rightAddend = randomGenerator.nextInt(30);
        sum = leftAddend + rightAddend;

        ArrayList answers = new ArrayList();
        answers.add(sum);

        while(answers.size() < 4){
            int offset = randomGenerator.nextInt(11) - 5;
            int wrongAnswer = sum + offset;
            if(!answers.contains(wrongAnswer)){
                answers.add(wrongAnswer);
            }
        }
        Collections.shuffle(answers);

        TableLayout answerTable = (TableLayout)findViewById(R.id.answerTable);
        for(int i = 0 ; i < answerTable.getChildCount(); i++){
            TableRow answerRow = (TableRow) answerTable.getChildAt(i);
            for(int j =0 ; j < answerRow.getChildCount(); j++){
                Button answerButton = (Button) answerRow.getChildAt(j);
                answerButton.setText(answers.get(i+j*2)+"");
            }
        }

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
