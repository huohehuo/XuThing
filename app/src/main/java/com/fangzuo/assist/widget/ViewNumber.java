package com.fangzuo.assist.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangzuo.assist.Activity.Crash.App;
import com.fangzuo.assist.R;
import com.fangzuo.assist.Utils.Info;
import com.fangzuo.assist.Utils.Toast;
import com.fangzuo.assist.Utils.VibratorUtil;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewNumber extends RelativeLayout {
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.tvdel)
    TextView tvdel;
    Context mContext;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.tv8)
    TextView tv8;
    @BindView(R.id.tv9)
    TextView tv9;
    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tvdian)
    TextView tvdian;
    @BindColor(R.color.cpb_blue)int blue;
    @BindColor(R.color.white)int white;
    public ViewNumber(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.view_number, this);
        ButterKnife.bind(this);
        mContext = context;
    }

    public String getString() {
        return tvResult.getText().toString();
    }

    //数量变化
    private void setTvResult(String num) {
        String s = tvResult.getText().toString();
        tvResult.setText(s + num);
    }

    //删除
    private void delResult() {
        String s = tvResult.getText().toString();
        if (s.length() > 0) {
            tvResult.setText(s.substring(0, s.length() - 1));
        } else {
            Toast.showText(App.getContext(), "没了别点了");
        }

    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9, R.id.tv0, R.id.tvdian, R.id.tvdel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                setTvResult("1");VibratorUtil.Vibrate(mContext, Info.View_Number_Click);
                setColor(R.id.tv1);
                break;
            case R.id.tv2:
                setTvResult("2");VibratorUtil.Vibrate(mContext, Info.View_Number_Click);
                setColor(R.id.tv2);
                break;
            case R.id.tv3:
                setTvResult("3");VibratorUtil.Vibrate(mContext, Info.View_Number_Click);
                setColor(R.id.tv3);
                break;
            case R.id.tv4:
                setTvResult("4");VibratorUtil.Vibrate(mContext, Info.View_Number_Click);
                setColor(R.id.tv4);
                break;
            case R.id.tv5:
                setTvResult("5");VibratorUtil.Vibrate(mContext, Info.View_Number_Click);
                setColor(R.id.tv5);
                break;
            case R.id.tv6:
                setTvResult("6");VibratorUtil.Vibrate(mContext, Info.View_Number_Click);
                setColor(R.id.tv6);
                break;
            case R.id.tv7:
                setTvResult("7");VibratorUtil.Vibrate(mContext, Info.View_Number_Click);
                setColor(R.id.tv7);
                break;
            case R.id.tv8:
                setTvResult("8");VibratorUtil.Vibrate(mContext, Info.View_Number_Click);
                setColor(R.id.tv8);
                break;
            case R.id.tv9:
                setTvResult("9");VibratorUtil.Vibrate(mContext, Info.View_Number_Click);
                setColor(R.id.tv9);
                break;
            case R.id.tv0:
                setTvResult("0");VibratorUtil.Vibrate(mContext, Info.View_Number_Click);
                setColor(R.id.tv0);
                break;
            case R.id.tvdian:
                setTvResult(".");VibratorUtil.Vibrate(mContext, Info.View_Number_Click);
                setColor(R.id.tvdian);
                break;
            case R.id.tvdel:VibratorUtil.Vibrate(mContext, Info.View_Number_Click);
                delResult();
                setColor(R.id.tvdel);
                break;
        }
    }
    private void setColor(int id){
        tv1.setBackgroundColor(white);
        tv2.setBackgroundColor(white);
        tv3.setBackgroundColor(white);
        tv4.setBackgroundColor(white);
        tv5.setBackgroundColor(white);
        tv6.setBackgroundColor(white);
        tv7.setBackgroundColor(white);
        tv8.setBackgroundColor(white);
        tv9.setBackgroundColor(white);
        tv0.setBackgroundColor(white);
        tvdian.setBackgroundColor(white);
        tvdel.setBackgroundColor(white);

        if (R.id.tv1 ==id)tv1.setBackgroundColor(blue);
        if (R.id.tv2 ==id)tv2.setBackgroundColor(blue);
        if (R.id.tv3 ==id)tv3.setBackgroundColor(blue);
        if (R.id.tv4 ==id)tv4.setBackgroundColor(blue);
        if (R.id.tv5 ==id)tv5.setBackgroundColor(blue);
        if (R.id.tv6 ==id)tv6.setBackgroundColor(blue);
        if (R.id.tv7 ==id)tv7.setBackgroundColor(blue);
        if (R.id.tv8 ==id)tv8.setBackgroundColor(blue);
        if (R.id.tv9 ==id)tv9.setBackgroundColor(blue);
        if (R.id.tv0 ==id)tv0.setBackgroundColor(blue);
        if (R.id.tvdian ==id)tvdian.setBackgroundColor(blue);
        if (R.id.tvdel ==id)tvdel.setBackgroundColor(blue);


    }
}
