package com.example.first_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SecondActivity extends AppCompatActivity {
    Button backButton;

    void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
    }

    void draw(int width, int height) {
        DrawingView drawingView = new DrawingView(this, width, height);
        ConstraintLayout layout = findViewById(R.id.second_layout);
        layout.addView(drawingView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        hideActionBar();

        Intent intent = getIntent();
        String[] values = intent.getStringArrayExtra("values");

        draw(Integer.parseInt(values[0]), Integer.parseInt(values[2]));

        backButton = findViewById(R.id.go_back_button);
        backButton.setOnClickListener((view -> {
            finish();
        }));
    }
}
