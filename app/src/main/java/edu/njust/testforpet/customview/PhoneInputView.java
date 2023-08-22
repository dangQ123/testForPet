package edu.njust.testforpet.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import edu.njust.testforpet.R;

public class PhoneInputView extends RelativeLayout {

    private Spinner spinner;
    private EditText editText;
    private RelativeLayout country ;

    private void initSpinnerForDropdown(Context context) {
        // 声明一个下拉列表的数组适配器
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(context,
                R.layout.item_select, starArray);
        editText = findViewById(R.id.phonenum);
        country = findViewById(R.id.countrylayout);

        country.setOnClickListener(view -> {
            Toast.makeText(getContext(),"点击了下拉框",Toast.LENGTH_LONG).show();
        });

    }

    // 定义下拉列表需要显示的文本数组
    private String[] starArray = {"+86", "+86", "+86"};
    // 定义一个选择监听器，它实现了接口OnItemSelectedListener
    class MySelectedListener implements AdapterView.OnItemSelectedListener {
        // 选择事件的处理方法，其中arg2代表选择项的序号
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        }

        // 未选择时的处理方法，通常无需关注
        public void onNothingSelected(AdapterView<?> arg0) {}
    }

    public PhoneInputView(Context context) {
        super(context);
        initSpinnerForDropdown(context);
    }


    public PhoneInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.phoneinput, this);
        initSpinnerForDropdown(context);

    }
}
