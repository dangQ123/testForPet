package edu.njust.testforpet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import cn.pedant.SweetAlert.SweetAlertDialog;
import edu.njust.testforpet.bottombar.adapter.TabPagerAdapter;

public class HomePageActivity extends AppCompatActivity {

    private ViewPager vp_content;
    private RadioGroup rg_tabbar;
    private static boolean isLogin = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        SharedPreferences shared = getSharedPreferences("share",MODE_PRIVATE);
        String phonenumber = shared.getString("phonenumber","nostring");
        String password = shared.getString("password","nostring");
        if (!phonenumber.equals("nostring")){
            isLogin = true;
        }
        if (!isLogin){
            Intent intent = new Intent(this, LoginMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        vp_content = findViewById(R.id.vp_content);
        rg_tabbar =findViewById(R.id.rg_tabbar);

        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        vp_content.setAdapter(tabPagerAdapter);

        vp_content.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

            @Override
            public void onPageSelected(int position) {
                rg_tabbar.check(rg_tabbar.getChildAt(position).getId());
            }
        });
        rg_tabbar.setOnCheckedChangeListener(((radioGroup, i) -> {
            for (int pos =0;pos<rg_tabbar.getChildCount();pos++){
                RadioButton tab = (RadioButton) rg_tabbar.getChildAt(pos);
                if (tab.getId()==i){
                    vp_content.setCurrentItem(pos);
                }
            }
        }));
    }
}