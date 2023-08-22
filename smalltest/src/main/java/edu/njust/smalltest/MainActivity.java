package edu.njust.smalltest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private ImageView draggableImageView;
    private float offsetX, offsetY;
    private FrameLayout drawingContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        draggableImageView = findViewById(R.id.circleDragImageView);

        draggableImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 计算按下时的偏移量
                        offsetX = event.getRawX() - v.getX();
                        offsetY = event.getRawY() - v.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // 更新 ImageView 的位置
                        v.setX(event.getRawX() - offsetX);
                        v.setY(event.getRawY() - offsetY);
                        break;
                }
                return true;
            }
        });

        drawingContainer = findViewById(R.id.drawingContainer);
        drawingContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                drawingContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                drawCircle();
            }
        });


    }
    private void drawCircle() {
        int centerX = drawingContainer.getWidth() / 2;
        int centerY = drawingContainer.getHeight() / 2;
        int radius = 150; // 让圆半径为容器宽高的1/4

        CircleView circleView = new CircleView(this, centerX, centerY, radius);
        drawingContainer.addView(circleView);
    }

    private class CircleView extends View {
        private int centerX;
        private int centerY;
        private int radius;
        private Paint paint;

        public CircleView(MainActivity context, int centerX, int centerY, int radius) {
            super(context);
            this.centerX = centerX;
            this.centerY = centerY;
            this.radius = radius;

            paint = new Paint();
            paint.setColor(Color.BLACK); // 设置圆的颜色
            paint.setStyle(Paint.Style.STROKE); // 修改样式为 Stroke
            paint.setStrokeWidth(5); // 设置边框宽度
        }

        @Override
        protected void onDraw(android.graphics.Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawCircle(centerX, centerY, radius, paint);
        }
    }
}