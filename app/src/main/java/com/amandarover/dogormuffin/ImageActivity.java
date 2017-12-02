package com.amandarover.dogormuffin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amandarover.dogormuffin.data.Score;

import java.util.Locale;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener{
    Score score;
    ImageView imageGeneric;
    TextView textViewRuningTime;
    Intent gameOverIntent;
    CountDownTimer countDown;
    int [] dogImages;
    int [] muffinImages;
    enum ImageType {
        DOG, MUFFIN
    }
    ImageType currentImageType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initImageActivityComponents();
        renderNextImage();
        ImageActivity contextActivity = this;
        setupCountDown();
    }

    private void initImageActivityComponents() {
        score = new Score();
        imageGeneric = findViewById(R.id.imageGeneric);
        findViewById(R.id.buttonDog).setOnClickListener(this);
        findViewById(R.id.buttonMuffin).setOnClickListener(this);
        dogImages = new int[]{R.drawable.dog1, R.drawable.dog2, R.drawable.dog3, R.drawable.dog4,
                R.drawable.dog5, R.drawable.dog6, R.drawable.dog7};
        muffinImages = new int[]{R.drawable.muffin1, R.drawable.muffin2, R.drawable.muffin3,
                R.drawable.muffin4, R.drawable.muffin5, R.drawable.muffin6, R.drawable.muffin7};
        textViewRuningTime = findViewById(R.id.textViewRuningTime);
        gameOverIntent = new Intent(this, GameOverActivity.class);
    }

    @Override
    public void onClick(View buttonView) {
        ImageType choosedButton = whichButtonClicked(buttonView);
        if (currentImageType == choosedButton) {
            setupCountDown();
            incrementScore();
            renderNextImage();
        } else {
            cancelCountDown();
            showInputNameDialog();
        }
    }

    private ImageType whichButtonClicked(View buttonView) {
        if (buttonView.getId() == R.id.buttonDog) {
            return ImageType.DOG;
        } else if (buttonView.getId() == R.id.buttonMuffin) {
            return ImageType.MUFFIN;
        }
        return null;
    }

    private void renderNextImage() {
        imageGeneric.setImageResource(getNextRandomImageRes());
    }

    private int getNextRandomImageRes() {
        if (Math.random()*2 > 1) {
            currentImageType = ImageType.DOG;
            return dogImages[(int)(Math.random()*dogImages.length)];
        } else {
            currentImageType = ImageType.MUFFIN;
            return muffinImages[(int)(Math.random()*muffinImages.length)];
        }
    }

    private void incrementScore() {
        score.points++;
    }

    private void setupCountDown() {
        cancelCountDown();
        countDown = new CountDownTimer(5000, 10) {
            public void onTick(long millisUntilFinished) {
                textViewRuningTime.setText(String.format(Locale.US,"Time left: %.2f", millisUntilFinished / 1000.0));
            }
            public void onFinish() {
                showInputNameDialog();
            }
        }.start();
    }

    private void cancelCountDown() {
        if (countDown != null) { countDown.cancel(); }
    }

    private void showInputNameDialog() {
        showInputNameDialog(false);
    }

    private void showInputNameDialog(boolean showWarning) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.input_name_dialog, null);
        if (showWarning) {
            TextView warningText = dialogView.findViewById(R.id.warningInput);
            warningText.setText("Insert your name");
        }
        builder.setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText username = dialogView.findViewById(R.id.username);
                        if (username.getText().toString().trim().equals("")) {
                            showInputNameDialog(true);
                        } else {
                            score.userName = username.getText().toString().trim();
                            gameOverIntent.putExtra("score", score);
                            startActivity(gameOverIntent);
                            finish();
                        }
                    }
                });
        builder.create().show();
    }
}
