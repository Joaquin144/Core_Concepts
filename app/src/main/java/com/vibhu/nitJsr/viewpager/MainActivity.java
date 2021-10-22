package com.vibhu.nitJsr.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] images = new int[8];
        images[0] = R.drawable.a1;
        images[1] = R.drawable.a2;
        images[2] = R.drawable.a3;
        images[3] = R.drawable.a4;
        images[4] = R.drawable.a5;
        images[5] = R.drawable.a6;
        images[6] = R.drawable.a7;
        images[7] = R.drawable.a8;

        ViewPager viewPager = findViewById(R.id.idAct_Main_VP1);
        viewPager.setAdapter(new VPAdapter(images,MainActivity.this));


    }
}