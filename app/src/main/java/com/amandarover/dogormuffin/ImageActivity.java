package com.amandarover.dogormuffin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener{
    int score = 0;
    ImageView imageGeneric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageGeneric = (ImageView) findViewById(R.id.imageGeneric);

        renderNextImage();

        findViewById(R.id.buttonDog).setOnClickListener(this);
        findViewById(R.id.buttonMuffin).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonDog:
                if (choosed == ImageType.DOG) {
                    renderNextImage();
                    incrementScore();
                } else {
                    Intent gameOverIntent = new Intent(this, GameOverActivity.class);
                    gameOverIntent.putExtra("points", score);
                    startActivity(gameOverIntent);
                    finish();
                }
                break;
            case R.id.buttonMuffin:
                if (choosed == ImageType.MUFFIN) {
                    renderNextImage();
                    incrementScore();
                } else {
                    Intent gameOverIntent = new Intent(this, GameOverActivity.class);
                    gameOverIntent.putExtra("points", score);
                    startActivity(gameOverIntent);
                    finish();
                }
                break;
        }
    }

    private void renderNextImage() {
        imageGeneric.setImageResource(getNextRandomImageRes());
    }

    int [] dogImages = new int[]{R.drawable.dog1, R.drawable.dog2};
    int [] muffinImages = new int[]{R.drawable.muffin1, R.drawable.muffin2};
    enum ImageType {
        DOG, MUFFIN
    }
    ImageType choosed;

    private int getNextRandomImageRes() {
        if (Math.random()*2 > 1) {
            choosed = ImageType.DOG;
            return dogImages[(int)(Math.random()*dogImages.length)];
        } else {
            choosed = ImageType.MUFFIN;
            return muffinImages[(int)(Math.random()*muffinImages.length)];
        }
    }

    private void incrementScore() {
        score++;
    }
}
