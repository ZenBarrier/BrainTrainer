package com.zenbarrier.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

    public void submitAnswer(){

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
