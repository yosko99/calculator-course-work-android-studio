package com.example.first_project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textViewCurrent, textViewAnswer;
    Integer equationIndex = 0;
    String[] equation = new String[3];

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideActionBar();

        textViewCurrent = findViewById(R.id.text_current);
        textViewAnswer = findViewById(R.id.text_result);
    }

    @SuppressLint("SetTextI18n")
    private void showResult() {
        textViewCurrent.setText("0");

        float answer = (float) 0;

        try {
            switch (equation[1]) {
                case "+":
                    answer = Float.parseFloat(equation[0]) + Float.parseFloat(equation[2]);
                    break;
                case "-":
                    answer = Float.parseFloat(equation[0]) - Float.parseFloat(equation[2]);
                    break;
                case "/":
                    answer = Float.parseFloat(equation[0]) / Float.parseFloat(equation[2]);
                    break;
                case "*":
                    answer = Float.parseFloat(equation[0]) * Float.parseFloat(equation[2]);
                    break;
            }

            textViewAnswer.setText(equation[0] + ' ' + equation[1] + ' ' + equation[2] + " = " + answer);
        } catch (Exception e) {
            textViewAnswer.setText("Dividing zero!");
        }
        equationIndex = 0;
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;

        String currentButtonPressedText = (String) button.getText();

        switch (currentButtonPressedText) {
            case "-":
            case "+":
            case "/":
            case "*":
                textViewCurrent.setText("0");
                equation[1] = currentButtonPressedText;
                equationIndex = 2;
                break;
            case "=":
                showResult();
                break;
            case "C":
                textViewCurrent.setText("0");
                textViewAnswer.setText("0");
                break;
            default:
                textViewAnswer.setText("0");
                textViewCurrent.setText(currentButtonPressedText);
                equation[equationIndex] = currentButtonPressedText;
                break;
        }
    }
}