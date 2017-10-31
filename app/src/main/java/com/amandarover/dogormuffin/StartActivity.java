package com.amandarover.dogormuffin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViewById(R.id.buttonStart).setOnClickListener(this);
        findViewById(R.id.buttonHighScores).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonStart:
                Intent readyActivity = new Intent(this, ReadyActivity.class);
                startActivity(readyActivity);
                break;
            case R.id.buttonHighScores:
                Intent highScoreActivity = new Intent(this, HighScoresActivity.class);
                startActivity(highScoreActivity);
                break;
        }
    }
}
