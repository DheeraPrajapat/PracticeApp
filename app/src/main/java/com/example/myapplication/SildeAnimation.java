package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SildeAnimation extends AppCompatActivity {
    ImageView imageView;
    Animation slideUp,slideDown,slideLeft,slideRight;
    ImageView down,up,left,right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silde_animation);
        initViews();

        slideUp= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
        slideDown= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        slideLeft= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_left);
        slideRight= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_right);

        up.setOnClickListener(v->imageView.startAnimation(slideUp));
        down.setOnClickListener(v->imageView.startAnimation(slideDown));
        left.setOnClickListener(v->imageView.startAnimation(slideLeft));
        right.setOnClickListener(v->imageView.startAnimation(slideRight));

    }

    private void initViews() {
        imageView=findViewById(R.id.imageView);
        up=findViewById(R.id.upImage);
        down=findViewById(R.id.downimage);
        left=findViewById(R.id.leftImage);
        right=findViewById(R.id.rightImage);
    }
}