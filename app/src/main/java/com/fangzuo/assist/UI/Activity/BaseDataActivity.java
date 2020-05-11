package com.fangzuo.assist.UI.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.fangzuo.assist.ABase.BaseActivity;
import com.fangzuo.assist.Adapter.BaseDataRyAdapter;
import com.fangzuo.assist.Adapter.HomeRyAdapter;
import com.fangzuo.assist.Adapter.StripAdapter;
import com.fangzuo.assist.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.Dao.BuyBean;
import com.fangzuo.assist.Fragment.pushdown.ChooseFragment;
import com.fangzuo.assist.Fragment.pushdown.DownLoadPushFragment;
import com.fangzuo.assist.R;
import com.fangzuo.assist.UI.Fragment.AddrBeanFragment;
import com.fangzuo.assist.UI.Fragment.BuyBeanFragment;
import com.fangzuo.assist.Utils.EventBusInfoCode;
import com.fangzuo.assist.Utils.Lg;
import com.fangzuo.assist.Utils.PagerSlidingTabStrip;
import com.fangzuo.assist.widget.BottomSheetDialogListView;
import com.fangzuo.assist.widget.SpringBackBottomSheetDialog;
import com.fangzuo.greendao.gen.BuyBeanDao;
import com.fangzuo.greendao.gen.NoteBeanDao;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseDataActivity extends BaseActivity {

    @BindView(R.id.tabstrip)
    PagerSlidingTabStrip tabstrip;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindColor(R.color.cpb_blue)
    int cpb_blue;
    public int tag;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.tv_user)
    TextView tvUser;
    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }
    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.BaseData_Tip://
                String res = (String) event.postEvent;
                tvTip.setText(res);
                break;
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_basedata);
        ButterKnife.bind(this);
        initBar();
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        fragments.add(new BuyBeanFragment());
        fragments.add(new AddrBeanFragment());
        titles.add("购买种类");
        titles.add("常用地址");
        StripAdapter stripAdapter = new StripAdapter(getSupportFragmentManager(), fragments, titles);
        Log.e("stripAdapter", stripAdapter + "");
        viewpager.setAdapter(stripAdapter);
        tabstrip.setShouldExpand(true);
        tabstrip.setViewPager(viewpager);
        tabstrip.setDividerColor(cpb_blue);
        tabstrip.setUnderlineHeight(3);
        tabstrip.setIndicatorHeight(6);
        tabstrip.setIndicatorColor(cpb_blue);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void OnReceive(String code) {

    }
    public static void start(Context context) {
        Intent intent = new Intent(context, BaseDataActivity.class);
//        intent.putExtra("activity", activity);
//        intent.putStringArrayListExtra("fid", fid);
        context.startActivity(intent);
    }

}
