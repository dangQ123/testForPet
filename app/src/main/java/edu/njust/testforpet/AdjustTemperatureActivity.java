package edu.njust.testforpet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import edu.njust.testforpet.util.Vector2D;

public class AdjustTemperatureActivity extends AppCompatActivity {

    private ImageView adjustImageView ;
    private ImageView adjustSmallButton ;
    private Handler handler;
    private FrameLayout frameLayout;
    public static float parent_x_px, parent_y_px;
    private float offsetX, offsetY;
    private float circle_x_px, circle_y_px;
    private float smallButton_x_px, smallButton_y_px;
    float density ;
    private int count = 0;
    private void startUpdatingText() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                smallButton_x_px = adjustSmallButton.getX()+34.5f/2*density;
                smallButton_y_px = adjustSmallButton.getY()+34.5f/2*density;
                Vector2D res = putInCircle(circle_x_px,circle_y_px,smallButton_x_px,smallButton_y_px,220*density);
                double dis = getDistanceToCirclePointWithDP(circle_x_px,circle_y_px,smallButton_x_px,smallButton_y_px);

                // 继续定时任务
                startUpdatingText();
            }
        }, 1); // 每隔 秒更新一次
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_temperature);
        density = getResources().getDisplayMetrics().density;
        adjustSmallButton = findViewById(R.id.adjustSmallButton);
        adjustImageView = findViewById(R.id.adjustImageView);

        handler = new Handler(Looper.getMainLooper());

        // 开始定时任务，每隔一段时间更新一次 TextView
        startUpdatingText();

        adjustSmallButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
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
                        smallButton_x_px = event.getRawX() - offsetX+34.5f/2*density;
                        smallButton_y_px = event.getRawY() - offsetY+34.5f/2*density;
                        double dis = getDistanceToCirclePointWithDP(circle_x_px,circle_y_px,smallButton_x_px,smallButton_y_px);
                        Vector2D res = putInCircle(circle_x_px,circle_y_px,smallButton_x_px,smallButton_y_px,220*density);

                        float needx = event.getRawX() - offsetX;
                        float needy = event.getRawY() - offsetY;
                        if (dis!=220){
                            needx = (float) res.getX();
                            needy = (float) res.getY();
                        }

                        v.setX(needx);
                        v.setY(needy);
                        break;
                }
                return true;
            }
        });

        ViewTreeObserver viewTreeObserver = adjustImageView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 获取背景的绝对位置
                parent_x_px = adjustImageView.getX();
                parent_y_px = adjustImageView.getY();

                // 获取背景圆心的绝对位置
                circle_x_px = parent_x_px + getResources().getDisplayMetrics().density*261;
                circle_y_px = parent_y_px + getResources().getDisplayMetrics().density*261;

                // 设置相对温度计最左上角的位置
//                setAdjustSmallButtonPosToParentWithDP(parent_x_px, parent_y_px,10,10);

                smallButton_x_px = circle_x_px -220f*density-34.5f/2*density;
                smallButton_y_px = circle_y_px-34.5f/2*density ;
                adjustSmallButton.setX(smallButton_x_px);
                adjustSmallButton.setY(smallButton_y_px);

                // 移除监听器，以防止重复调用
                adjustImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public double getDistanceToCirclePointWithDP(float circle_x , float circle_y, float x,float y){
        Vector2D vector2D = new Vector2D(x - circle_x, y- circle_y);
        return vector2D.getLength()/density;
    }

    public double getAngle(float circle_x , float circle_y , float x, float y){
        Vector2D down = new Vector2D(0,1);
        Vector2D toCircle = new Vector2D(x-circle_x , y-circle_y);
        double rad = Vector2D.radianBetween(down,toCircle);
        return Vector2D.radian2Angle(rad);
    }

    public double getAngleToCircle(float circle_x , float circle_y , float x, float y){
        return getAngle(circle_x,circle_y,x,y);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 移除未执行的任务，以防止内存泄漏
        handler.removeCallbacksAndMessages(null);
    }

    public Vector2D putInCircle(float cx,float cy,float sx,float sy , float r){
        Vector2D res = new Vector2D();
        float k =(sy-cy)/(sx-cx);
        double v = r / Math.sqrt(1 + k * k);
        float resx = (float) (cx - v);
        float resy = (float) (cy - k*v);
        res.setX(resx-34.5f/2*density);
        res.setY(resy-34.5f/2*density);
        return res;
    }


}