package com.amandarover.dogormuffin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener{
    int score = 0;
    ImageView imageGeneric;
    int [] dogImages;
    int [] muffinImages;
    enum ImageType {
        DOG, MUFFIN
    }
    ImageType choosed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initAllComponents();
        renderNextImage();
    }

    private void initAllComponents() {
        imageGeneric = findViewById(R.id.imageGeneric);
        findViewById(R.id.buttonDog).setOnClickListener(this);
        findViewById(R.id.buttonMuffin).setOnClickListener(this);
        dogImages = new int[]{R.drawable.dog1, R.drawable.dog2, R.drawable.dog3, R.drawable.dog4, R.drawable.dog5, R.drawable.dog6, R.drawable.dog7};
        muffinImages = new int[]{R.drawable.muffin1, R.drawable.muffin2, R.drawable.muffin3, R.drawable.muffin4, R.drawable.muffin5, R.drawable.muffin6, R.drawable.muffin7};
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
