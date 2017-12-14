package com.example.alam.pvsatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        Intent intent = new Intent(InstructionActivity.this,PacedTestActivity.class);
        startActivity(intent);
    }
}
