package edu.njust.testforpet.bottombar.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.pedant.SweetAlert.SweetAlertDialog;
import edu.njust.testforpet.AdjustTemperatureActivity;
import edu.njust.testforpet.DisInfectActivity;
import edu.njust.testforpet.HomePageActivity;
import edu.njust.testforpet.R;
import edu.njust.testforpet.SleepActivity;

public class TabFirstFragment extends Fragment {
    private static final String TAG = "TabFirstFragment";
    protected View mView; // 声明一个视图对象
    protected Context mContext; // 声明一个上下文对象

    private LinearLayout sleeplinear , infectlinear;
    private TextView exacttemp;
    private int nowTemp = 30;
    private int temperature;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity(); // 获取活动页面的上下文
        // 根据布局文件fragment_tab_first.xml生成视图对象
        mView = inflater.inflate(R.layout.fragment_tab_first, container, false);

        return mView;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button floatingbutton =getActivity().findViewById(R.id.floatingButton);
        sleeplinear = getActivity().findViewById(R.id.sleepbuttonlinear);
        sleeplinear.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SleepActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        infectlinear = getActivity().findViewById(R.id.infectlinear);
        infectlinear.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), DisInfectActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        floatingbutton.setOnClickListener(view -> {
           Intent intent = new Intent(getActivity() , AdjustTemperatureActivity.class);
           intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            Bundle bundle = new Bundle();
            bundle.putInt("temperature" , temperature);
            intent.putExtras(bundle);
             startActivity(intent);

        });

        exacttemp = getActivity().findViewById(R.id.exacttemp);

    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle!=null){
            int temperature = bundle.getInt("temperature");
            exacttemp.setText(temperature+" ℃");
        }
    }
}
