package com.amandarover.dogormuffin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amandarover.dogormuffin.data.AppDatabase;
import com.amandarover.dogormuffin.data.Score;
import com.amandarover.dogormuffin.data.ScoreDao;

import java.util.List;

public class HighScoresActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        findViewById(R.id.buttonBack).setOnClickListener(this);
        ListView highScoreListView = findViewById(R.id.listViewHigsScores);

        AppDatabase db = AppDatabase.getInstance(this);
        ScoreDao dao = db.scoreDao();
        List<Score> highScoreList = dao.fetchAll();

        ArrayAdapter<Score> highScoreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, highScoreList);
        highScoreListView.setAdapter(highScoreAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonBack:
                Intent startActivity = new Intent(this, StartActivity.class);
                startActivity(startActivity);
                finish();
                break;
        }
    }
}
