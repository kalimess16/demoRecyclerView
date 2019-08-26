package com.example.demorecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button mRecyclerV, mExit, mCardV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        recyclerView();
        cardView();
        exit();
    }

    private void cardView() {
        mCardV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void recyclerView() {
        mRecyclerV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Recycler_View.class);
                startActivity(intent);
            }
        });
    }

    private void exit() {
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void mapping() {
        mExit = findViewById(R.id.button_exit);
        mCardV = findViewById(R.id.button_CardView);
        mRecyclerV = findViewById(R.id.button_recyclerView);
    }
}
