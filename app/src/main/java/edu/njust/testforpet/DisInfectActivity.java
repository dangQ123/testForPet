package edu.njust.testforpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;

public class DisInfectActivity extends AppCompatActivity {

    private Button operateDisinfectButton ,backicon;
    private ImageView disinfectimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_infect);

        disinfectimage = findViewById(R.id.infectimage);
        operateDisinfectButton =(Button) findViewById(R.id.operateDisinfectButton);
        operateDisinfectButton.setOnClickListener(view -> {
            operateDisinfectButton.setText("正在消毒");
            operateDisinfectButton.setEnabled(false); // 禁用按钮以防止重复点击
            operateDisinfectButton.setElevation(6);
            disinfectimage.setImageResource(R.drawable.infectbigbutton2);


            Handler handler = new Handler();
            handler.postDelayed(() -> {
                operateDisinfectButton.setText("消毒完成");
                operateDisinfectButton.setEnabled(true); // 恢复按钮可点击状态
                operateDisinfectButton.setBackgroundResource(R.drawable.disinfectbuttonretangle);
                disinfectimage.setImageResource(R.drawable.infectbigbutton1);
            }, 3000); // 3秒后更新文字
        });

        backicon = findViewById(R.id.backicon);
        backicon.setOnClickListener(view -> {
            Intent intent = new Intent(this, HomePageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(new Intent(this , HomePageActivity.class));
            overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_from_right);
        });

    }
}