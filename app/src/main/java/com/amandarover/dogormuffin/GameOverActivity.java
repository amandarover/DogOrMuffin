package com.amandarover.dogormuffin;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.amandarover.dogormuffin.data.AppDatabase;
import com.amandarover.dogormuffin.data.Score;
import com.amandarover.dogormuffin.data.ScoreDao;

public class GameOverActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Score score = new Score();
        score.points = getIntent().getIntExtra("points", 0);

        AppDatabase db = AppDatabase.getInstance(this);
        ScoreDao dao = db.scoreDao();
        dao.insert(score);

        TextView finalScore = findViewById(R.id.textViewScore);
        finalScore.setText("Your Score: " + score.points);

        TextView highScore = findViewById(R.id.textViewHighScore);
        highScore.setText("High Score: " + dao.fetchHighest().points);

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
