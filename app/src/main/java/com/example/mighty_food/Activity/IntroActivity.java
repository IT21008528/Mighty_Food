package com.example.mighty_food.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mighty_food.R;

public class IntroActivity extends AppCompatActivity {
//////////////////////////////////////////////////////////////////////////////// (1)
    Button btnStart; // create button variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                     // will change this to login
                startActivity(new Intent(IntroActivity.this,LoginActivity.class));
            }
        });
    }
    /////////////////////////////////////////////////////////////////////////////////// (1/)
}