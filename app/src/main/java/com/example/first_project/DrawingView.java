package com.example.first_project;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

public class DrawingView extends View {
    Integer width, height;
    DisplayMetrics displayMetrics;
    private Paint fillPaint = new Paint();
    private Paint strokePaint = new Paint();
    private Paint textPaint = new Paint();
    private Paint roundedRectanglePaint = new Paint();
    private Integer left, top, right, bottom;

    public DrawingView(Context context, int width, int height) {
        super(context);
        this.width = width;
        this.height = height;
        this.displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        init();
    }

    private void init() {
        initPaintConfig();
        getCenterCoordinates(this.width, this.height);
    }

    void initPaintConfig() {
        fillPaint.setColor(Color.YELLOW);
        fillPaint.setStyle(Paint.Style.FILL);

        strokePaint.setColor(Color.RED);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(5);

        textPaint.setColor(Color.RED);
        textPaint.setTextSize(70);

        roundedRectanglePaint.setColor(Color.RED);
        roundedRectanglePaint.setStyle(Paint.Style.FILL);
    }

    void getCenterCoordinates(int width, int height) {
        int centerX = displayMetrics.widthPixels / 2;
        int centerY = displayMetrics.heightPixels / 2;
        int rectWidth = width * 10;
        int rectHeight = height * 10;
        this.left = centerX - rectWidth / 2;
        this.top = centerY - rectHeight / 2;
        this.right = centerX + rectWidth / 2;
        this.bottom = centerY + rectHeight / 2;
    }


    void drawRectAtCenter(Canvas canvas, int width, int height, boolean isRounded) {
        getCenterCoordinates(width, height);

        if (isRounded) {
            canvas.drawRoundRect(left, top, right, bottom, 50, 50, roundedRectanglePaint);
        } else {
            canvas.drawRect(left, top, right, bottom, fillPaint);
            canvas.drawRect(left, top, right, bottom, strokePaint);
        }
    }

    void drawScene(Canvas canvas) {
        if (this.width == 0 || this.height == 0) {
            canvas.drawText("Invalid values!", left - 200, top, textPaint);
        } else {
            drawRectAtCenter(canvas, this.width * 10, this.height * 10, false);
            drawRectAtCenter(canvas, this.width * 8, this.height * 8, true);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawScene(canvas);
    }
}
