package com.zenbarrier.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    View startButton;
    View gameLayout;

    public void startGame(View view){
        startButton.setVisibility(View.GONE);
        gameLayout.setVisibility(View.VISIBLE);
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
