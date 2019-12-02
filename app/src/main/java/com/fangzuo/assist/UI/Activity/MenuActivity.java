package com.fangzuo.assist.UI.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangzuo.assist.ABase.BaseActivity;
import com.fangzuo.assist.Adapter.MenuFragmentAdapter;
import com.fangzuo.assist.UI.Fragment.HomeFragment;
import com.fangzuo.assist.UI.Fragment.OwnFragment;
import com.fangzuo.assist.R;
import com.fangzuo.greendao.gen.DaoSession;

import org.greenrobot.greendao.async.AsyncSession;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends BaseActivity {

    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.iv_purchase)
    ImageView ivPurchase;
    @BindView(R.id.tv_purchase)
    TextView tvPurchase;
    @BindView(R.id.bottom_btn_purchase)
    LinearLayout bottomBtnPurchase;
    @BindView(R.id.iv_sale)
    ImageView ivSale;
    @BindView(R.id.tv_sale)
    TextView tvSale;
    @BindView(R.id.bottom_btn_sale)
    LinearLayout bottomBtnSale;
    @BindView(R.id.iv_storage)
    ImageView ivStorage;
    @BindView(R.id.tv_storage)
    TextView tvStorage;
    @BindView(R.id.bottom_btn_storage)
    LinearLayout bottomBtnStorage;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.bottom_btn_setting)
    LinearLayout bottomBtnSetting;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindColor(R.color.bottombartv)
    int tvcolor;
    @BindColor(R.color.fragment_text)
    int tvColorUnClick;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private MenuActivity mContext;
    private FragmentTransaction ft;
    private Fragment curFragment;
    private DaoSession daoSession;
    private AsyncSession asyncSession;
    private AsyncSession asyncSession2;
    private long nowTime;
    private int size;

    @Override
    public void initView() {
        setContentView(R.layout.activity_menu);
        mContext = this;
        ButterKnife.bind(this);
        initBar();
        initFragments();

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }


//


    @Override
    public void initData() {
//        tvUser.setText("当前用户:" + ShareUtil.getInstance(mContext).getUserName());
//        ivPurchase.setImageResource(R.mipmap.purchase);
//        tvPurchase.setTextColor(tvcolor);
    }


    @Override
    public void initListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        resetBottomView();
                        ivPurchase.setImageResource(R.mipmap.home);
                        tvPurchase.setTextColor(tvcolor);
                        ivAdd.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        resetBottomView();
                        ivSetting.setImageResource(R.mipmap.set);
                        ivAdd.setVisibility(View.GONE);
//                        tvSetting.setTextColor(tvcolor);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void OnReceive(String code) {

    }


    private void initFragments() {
        FragmentManager fm = getSupportFragmentManager();
        HomeFragment purchaseFragment = new HomeFragment();
        OwnFragment settingFragment = new OwnFragment();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(purchaseFragment);
        fragments.add(settingFragment);
        MenuFragmentAdapter menuFragmentAdapter = new MenuFragmentAdapter(fm, fragments);
        viewPager.setAdapter(menuFragmentAdapter);
        viewPager.setCurrentItem(0);
    }


//    @OnClick(R.id.bottom_btn_sale)
//    public void onBottomBtnSaleClicked() {
//        Log.e("bottomBar", "purchase");
//        viewPager.setCurrentItem(1, true);
//        resetBottomView();
//        ivSale.setImageResource(R.mipmap.sale);
//        tvSale.setTextColor(tvcolor);
//    }
//
//    @OnClick(R.id.bottom_btn_storage)
//    public void onBottomBtnStorageClicked() {
//        Log.e("bottomBar", "purchase");
//        viewPager.setCurrentItem(2, true);
//        resetBottomView();
//        ivStorage.setImageResource(R.mipmap.storage);
//        tvStorage.setTextColor(tvcolor);
//    }


    private void resetBottomView() {
        ivPurchase.setImageResource(R.mipmap.home_no);
//        ivSale.setImageResource(R.mipmap.unsale);
//        ivStorage.setImageResource(R.mipmap.unstorage);
        ivSetting.setImageResource(R.mipmap.set_no);
//        tvPurchase.setTextColor(tvColorUnClick);
//        tvSale.setTextColor(tvColorUnClick);
//        tvSetting.setTextColor(tvColorUnClick);
//        tvStorage.setTextColor(tvColorUnClick);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    @OnClick({R.id.iv_add, R.id.ll1,R.id.bottom_btn_purchase,R.id.bottom_btn_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                AddNoteActivity.start(mContext);
                break;
            case R.id.ll1:
                break;
            case R.id.bottom_btn_purchase:
                viewPager.setCurrentItem(0, true);
                resetBottomView();
                ivPurchase.setImageResource(R.mipmap.home);
                tvPurchase.setTextColor(tvcolor);
                ivAdd.setVisibility(View.VISIBLE);
                break;
            case R.id.bottom_btn_setting:
                viewPager.setCurrentItem(1, true);
                resetBottomView();
                ivSetting.setImageResource(R.mipmap.set);
                tvSetting.setTextColor(tvcolor);
                ivAdd.setVisibility(View.GONE);
                break;

        }
    }
}
