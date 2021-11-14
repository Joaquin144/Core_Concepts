package com.vibhu.nitJsr.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vibhu.nitJsr.viewpager.activities.ToDoListActivity;

public class MainActivity extends AppCompatActivity {

    private Button idBtnMotionLayout,apiBtn,toDOBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idBtnMotionLayout=findViewById(R.id.idBtnMotionLayout);
        apiBtn=findViewById(R.id.hotelAPI);
        toDOBtn=findViewById(R.id.toDOBtn);

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

        idBtnMotionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MotionLayoutActivity.class);
                startActivity(intent);
            }
        });

        apiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,APIActivity.class);
                startActivity(intent);
            }
        });

        toDOBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, ToDoListActivity.class);
                startActivity(intent);
            }
        });


    }
}