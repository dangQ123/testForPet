package edu.njust.testforpet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import edu.njust.testforpet.util.Vector2D;

public class AdjustTemperatureActivity extends AppCompatActivity {

    private ImageView adjustImageView , hotbutton,coldbutton;
    private ImageView adjustSmallButton ;
    private Handler handler;
    private TextView temperatureNum;
    public static float parent_x_px, parent_y_px;
    private float offsetX, offsetY;
    private float circle_x_px, circle_y_px;
    private float smallButton_x_px, smallButton_y_px;

    private int status = STATUS_HOT;
    public static final int STATUS_HOT = 0,STATUS_COLD = 1;

    float density ;
    private int count = 0;

    public int getNowTemp(float circle_x_px, float circle_y_px,float smallButton_x_px,float smallButton_y_px , int status){
        if (status==STATUS_HOT){
            return (int) (getAngle(circle_x_px,circle_y_px,smallButton_x_px,smallButton_y_px)/9+20);
        }else if (status==STATUS_COLD){
            return (int) (getAngle(circle_x_px,circle_y_px,smallButton_x_px,smallButton_y_px)/9+10);
        }else
            return -1;
    }

    private void startUpdatingText() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                smallButton_x_px = adjustSmallButton.getX()+34.5f/2*density;
                smallButton_y_px = adjustSmallButton.getY()+34.5f/2*density;
//                int temp = (int) (getAngle(circle_x_px,circle_y_px,smallButton_x_px,smallButton_y_px)/9+20);
                int temp = getNowTemp(circle_x_px,circle_y_px,smallButton_x_px,smallButton_y_px,status);
                temperatureNum.setText(""+temp
                +"℃");

                // 继续定时任务
                startUpdatingText();
            }
        }, 500); // 每隔 秒更新一次
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_temperature);
        density = getResources().getDisplayMetrics().density;
        adjustSmallButton = findViewById(R.id.adjustSmallButton);

        hotbutton = findViewById(R.id.hotbutton);
        coldbutton = findViewById(R.id.coldbutton);
        hotbutton.setOnClickListener(view -> {
            status = STATUS_HOT;
            hotbutton.setImageResource(R.drawable.hot_press);
            coldbutton.setImageResource(R.drawable.cold_notpress);
            adjustSmallButton.setImageResource(R.drawable.adjustsmallbutton_hot);
            adjustImageView.setImageResource(R.drawable.adjusthot);
            temperatureNum.setTextColor(Color.parseColor("#FF9900"));
        });
        coldbutton.setOnClickListener(view -> {
            status = STATUS_COLD;
            hotbutton.setImageResource(R.drawable.hot_notpress);
            coldbutton.setImageResource(R.drawable.cold_press);
            adjustSmallButton.setImageResource(R.drawable.adjustsmallbutton_cold);
            adjustImageView.setImageResource(R.drawable.adjustcold);
            temperatureNum.setTextColor(Color.parseColor("#365EFF"));
        });


        adjustImageView = findViewById(R.id.adjustImageView);
        temperatureNum = findViewById(R.id.tempratureNum);
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