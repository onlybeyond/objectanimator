package com.onlybeyond.objectanimator.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.onlybeyond.objectanimator.R;
import com.onlybeyond.objectanimator.ui.activity.BaseActivity;
import com.onlybeyond.objectanimator.ui.activity.TestActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvTitle;

    @Override
    public void initTop() {
        super.initTop();
        tvTitle = (TextView)findViewById(R.id.toolbar_title);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        TextView tvTestFirst=(TextView)findViewById(R.id.tv_test_first);
        TextView tvTestSecond=(TextView)findViewById(R.id.tv_test_second);
        TextView tvTestThree=(TextView)findViewById(R.id.tv_test_three);
        TextView tvTestFour=(TextView)findViewById(R.id.tv_test_four);


        tvTestFirst.setOnClickListener(this);
        tvTestSecond.setOnClickListener(this);
        tvTestThree.setOnClickListener(this);
        tvTestFour.setOnClickListener(this);


    }

    @Override
    public void fillDate() {
      tvTitle.setText(getString(R.string.object_animator));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.tv_test_first){
            Bundle pBundle = new Bundle();
            pBundle.putString("test_sort",TestActivity.TEST_ONE);
            openActivity(TestActivity.class, pBundle);

        }else if(id==R.id.tv_test_second){
            Bundle pBundle = new Bundle();
            pBundle.putString("test_sort",TestActivity.TEST_TWO);
            openActivity(TestActivity.class, pBundle);
        }else if(id==R.id.tv_test_three){
            Bundle pBundle = new Bundle();
            pBundle.putString("test_sort",TestActivity.TEST_THREE);
            openActivity(TestActivity.class, pBundle);
        }else if(id==R.id.tv_test_four){
            Bundle pBundle = new Bundle();
            pBundle.putString("test_sort",TestActivity.TEST_FOUR);
            openActivity(TestActivity.class, pBundle);
        }
    }
}
