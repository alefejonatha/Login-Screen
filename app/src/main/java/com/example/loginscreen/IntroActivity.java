package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class IntroActivity extends AppCompatActivity {

    ImageView logo, backgraund;
    LottieAnimationView lottieAnimationView;
    static int SPLASH_SCREEN = 4800;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Retirar getWindow()
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

        logo = findViewById(R.id.logo);
        backgraund = findViewById(R.id.image_back);
        lottieAnimationView = findViewById(R.id.lottie);

        backgraund.animate().translationY(-2800).setDuration(800).setStartDelay(4000);
        logo.animate().translationY(1400).setDuration(800).setStartDelay(4000);
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
        lottieAnimationView.animate().translationY(1400).setDuration(800).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);

    }
}
