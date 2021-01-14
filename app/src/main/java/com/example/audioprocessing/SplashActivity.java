package com.example.audioprocessing;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class SplashActivity extends AppCompatActivity {

    ImageView imageViewTop, imageViewCenter, imageViewGIF, imageViewBottom;
    TextView textViewAppName;
    CharSequence charSequence;
    int index;
    long delay = 100;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        imageViewTop = findViewById(R.id.imageViewTop);
        imageViewCenter = findViewById(R.id.imageViewCenter);
//        imageViewGIF = findViewById(R.id.imageViewGIF);
        imageViewBottom = findViewById(R.id.imageViewBottom);
        textViewAppName = findViewById(R.id.textViewAppName);

        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.top_wave);

        imageViewTop.setAnimation(animation1);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                imageViewCenter,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        );

        objectAnimator.setDuration(500);

        objectAnimator.setRepeatCount(1);

        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);

        objectAnimator.start();

        animateText("Audio Processing");

//        Glide.with(this).load(R.raw.giphy)
//                .asGif()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imageViewGIF);

        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.bottom_wave);

        imageViewBottom.setAnimation(animation2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        }, 4000);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            textViewAppName.setText(charSequence.subSequence(0,index++));

            if (index<=charSequence.length()){
                handler.postDelayed(runnable,delay);
            }
        }
    };

    public void animateText(CharSequence cs){
        charSequence = cs;

        index = 0;

        textViewAppName.setText("");

        handler.removeCallbacks(runnable);

        handler.postDelayed(runnable,delay);
    }
}