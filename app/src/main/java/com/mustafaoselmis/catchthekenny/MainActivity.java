package com.mustafaoselmis.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    int score;

    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;
    ImageView imageView17;

    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize

        timeText = (TextView) findViewById(R.id.timeText);
        scoreText = (TextView) findViewById(R.id.scoreText);

        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);
        imageView15 = findViewById(R.id.imageView15);
        imageView16 = findViewById(R.id.imageView16);
        imageView17 = findViewById(R.id.imageView17);

        imageArray = new ImageView[] {imageView10,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16,imageView17};


        hideImages();




        score = 0;

        new CountDownTimer(10000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time : " + millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {

                timeText.setText("Time off");
                handler.removeCallbacks(runnable);

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Restart Game ?");
                alert.setMessage("Are you sure to restart game");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //restart

                        Intent intent = getIntent();
                        finish();

                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this,"Game Over",Toast.LENGTH_LONG).show();

                    }
                });

                alert.show();

            }
        }.start();



    }

    public void increaseScore(View view) {

        score++;

        scoreText.setText("Score :" + score);



    }

    public void hideImages() {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9); // 0 ile 9 arası bir rakam getirir
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,1000);
            }
        };

        handler.post(runnable);



    }
}