package com.amandarover.dogormuffin;

import android.content.Intent;
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

        initImageActivityComponents();

        Score score = getScoreFromActivity();

        ScoreDao scoreDao = AppDatabase.getInstance(this).scoreDao();
        scoreDao.insert(score);

        showScoreOnTextView(score, scoreDao);
    }

    private void initImageActivityComponents() {
        findViewById(R.id.buttonPlayAgain).setOnClickListener(this);
        findViewById(R.id.buttonHighScores).setOnClickListener(this);
        findViewById(R.id.buttonBackToMenu).setOnClickListener(this);
    }

    private Score getScoreFromActivity(){
        Score score = (Score) getIntent().getSerializableExtra("score");
        return score;
    }

    private void showScoreOnTextView(Score score, ScoreDao scoreDao) {
        TextView finalScore = findViewById(R.id.textViewScore);
        finalScore.setText("Your Score: " + score.points);

        TextView highScore = findViewById(R.id.textViewHighScore);
        highScore.setText("High Score: " + scoreDao.fetchHighest().points);
    }

    @Override
    public void onClick(View clickedButtonView) {
        switch (clickedButtonView.getId()) {
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
