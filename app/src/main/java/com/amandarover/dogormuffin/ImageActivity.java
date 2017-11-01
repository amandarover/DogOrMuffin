package com.amandarover.dogormuffin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        findViewById(R.id.buttonDog).setOnClickListener(this);
        findViewById(R.id.buttonMuffin).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonDog:
                Intent readyActivity = new Intent(this, ReadyActivity.class);
                startActivity(readyActivity);
                break;
            case R.id.buttonMuffin:
                Intent highScoreActivity = new Intent(this, HighScoresActivity.class);
                startActivity(highScoreActivity);
                break;
        }
    }
}
