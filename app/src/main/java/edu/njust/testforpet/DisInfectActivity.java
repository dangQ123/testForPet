package edu.njust.testforpet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

public class DisInfectActivity extends AppCompatActivity {

    private Button operateDisinfectButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_infect);

        operateDisinfectButton =(Button) findViewById(R.id.operateDisinfectButton);

        operateDisinfectButton.setOnClickListener(view -> {
            operateDisinfectButton.setText("正在消毒");
            operateDisinfectButton.setEnabled(false); // 禁用按钮以防止重复点击
            operateDisinfectButton.setBackgroundResource(R.color.grey_login);

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                operateDisinfectButton.setText("消毒完成");
                operateDisinfectButton.setEnabled(true); // 恢复按钮可点击状态
                operateDisinfectButton.setBackgroundResource(R.drawable.disinfectbuttonretangle);
            }, 3000); // 3秒后更新文字
        });

    }
}