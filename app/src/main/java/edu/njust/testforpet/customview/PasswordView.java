package edu.njust.testforpet.customview;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import edu.njust.testforpet.R;

public class PasswordView extends RelativeLayout {

    private ImageView notseeicon;

    public void init(Context context){
        notseeicon = findViewById(R.id.notseeicon);
        notseeicon.setOnClickListener(view -> {
            Toast.makeText(context,"点击了密码icon",Toast.LENGTH_LONG).show();
        });


    }

    public PasswordView(Context context) {
        super(context);
        init(context);
    }

    public PasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
    // 加载布局
        LayoutInflater.from(context).inflate(R.layout.passwortinput, this);
        init(context);
    }
}
