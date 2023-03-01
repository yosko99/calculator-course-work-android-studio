package com.example.first_project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textViewCurrent, textViewAnswer;
    Integer equationIndex = 0;
    Button drawButton;
    String[] equation = new String[3];

    void hideActionBar() {
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
        drawButton = findViewById(R.id.button_draw);

        drawButton.setOnClickListener(((view) -> {
            switchActivities();
        }));
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, SecondActivity.class);
        switchActivityIntent.putExtra("values", equation);
        startActivity(switchActivityIntent);
    }

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
        Button button = (Button) view;
        String currentButtonPressedText = (String) button.getText();

        drawButton.setEnabled(false);
        drawButton.setBackgroundColor(Color.rgb(211, 211, 211));


        switch (currentButtonPressedText) {
            case "-":
            case "+":
            case "/":
            case "*":
                if (equation[0] != null) {
                    textViewCurrent.setText("0");
                    equation[1] = currentButtonPressedText;
                    equationIndex = 2;
                }
                break;
            case "=":
                if (equation[2] != null) {
                    showResult();
                    drawButton.setBackgroundColor(Color.rgb(0, 154, 23));
                    drawButton.setEnabled(true);
                }
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