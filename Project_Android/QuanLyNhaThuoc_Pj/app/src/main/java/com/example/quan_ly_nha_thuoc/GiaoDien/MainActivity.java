package com.example.quan_ly_nha_thuoc.GiaoDien;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.quan_ly_nha_thuoc.R;
public class MainActivity extends AppCompatActivity {
    boolean run = true;
    ImageView img1, img2,img3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.dichuyen);
                img2.startAnimation(animation);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.blink);
                img3.startAnimation(animation);
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, TrangChu.class);
                startActivity(intent);
            }
        }, 1000);
    }

    private void setEvent() {
        final AnimationDrawable runAnimationDrawable = (AnimationDrawable) img1.getDrawable();
        runAnimationDrawable.start();
    }

    private void setControl() {
        img1 = findViewById(R.id.imageView);
        img2 = findViewById(R.id.imageView2);
        img3 = findViewById(R.id.imageView3);
    }
}
