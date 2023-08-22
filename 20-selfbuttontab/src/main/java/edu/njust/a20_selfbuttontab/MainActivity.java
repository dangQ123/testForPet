package edu.njust.a20_selfbuttontab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.concurrent.RejectedExecutionHandler;

import edu.njust.a20_selfbuttontab.adapter.TabPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp_content;
    private RadioGroup rg_tabbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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