package com.amandarover.dogormuffin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameOverActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        findViewById(R.id.buttonPlayAgain).setOnClickListener(this);
        findViewById(R.id.buttonHighScores).setOnClickListener(this);
        findViewById(R.id.buttonBackToMenu).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonPlayAgain:
                Intent readyActivity = new Intent(this, ReadyActivity.class);
                startActivity(readyActivity);
                finish();
                break;
            case R.id.buttonHighScores:
                Intent highScoreActivity = new Intent(this, HighScoresActivity.class);
                startActivity(highScoreActivity);
                finish();
                break;
            case R.id.buttonBackToMenu:
                Intent startActivity = new Intent(this, StartActivity.class);
                startActivity(startActivity);
                finish();
        }
    }
}
