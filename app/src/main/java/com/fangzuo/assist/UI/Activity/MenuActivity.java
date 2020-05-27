package com.fangzuo.assist.UI.Activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fangzuo.assist.ABase.BaseActivity;
import com.fangzuo.assist.Adapter.BaseDataRyAdapter;
import com.fangzuo.assist.Adapter.MenuFragmentAdapter;
import com.fangzuo.assist.Adapter.ProductselectAdapter1;
import com.fangzuo.assist.UI.Fragment.HomeFragment;
import com.fangzuo.assist.UI.Fragment.OwnFragment;
import com.fangzuo.assist.R;
import com.fangzuo.assist.UI.Fragment.RiliFragment;
import com.fangzuo.assist.Utils.Lg;
import com.fangzuo.assist.Utils.LocDataUtil;
import com.fangzuo.assist.Utils.UpgradeUtil.AppStatisticalUtil;
import com.fangzuo.assist.Utils.UpgradeUtil.AppVersionUtil;
import com.fangzuo.assist.widget.LoadingUtil;
import com.fangzuo.greendao.gen.BuyBeanDao;
import com.fangzuo.greendao.gen.DaoSession;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.greendao.async.AsyncSession;
import org.json.JSONArray;
import org.json.JSONException;

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
    private AsyncSession asyncSession;
    private AsyncSession asyncSession2;
    private long nowTime;
    private int size;

    private BuyBeanDao buyBeanDao;

    @Override
    public void initView() {
        setContentView(R.layout.activity_menu);
        mContext = this;
        ButterKnife.bind(this);
        buyBeanDao = daoSession.getBuyBeanDao();
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
        AppStatisticalUtil.upDataStatis(mContext,"MenuActivity");
        AppVersionUtil.CheckVersion(mContext);
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
//                    case 1:
//                        resetBottomView();
//                        ivSale.setImageResource(R.mipmap.rili);
//                        tvSale.setTextColor(tvcolor);
//                        ivAdd.setVisibility(View.VISIBLE);
//                        break;
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
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
//        fragments.add(new RiliFragment());
        fragments.add(new OwnFragment());
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
        ivSale.setImageResource(R.mipmap.rili_no);
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

    AlertDialog alertDialog;
    private void selectAdd(){
        AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
        ab.setTitle("请选择项目");
        View v = LayoutInflater.from(mContext).inflate(R.layout.menu_add, null);
        EasyRecyclerView lv = v.findViewById(R.id.ry_list);
        final BaseDataRyAdapter adapter = new BaseDataRyAdapter(mContext);
        lv.setLayoutManager(new LinearLayoutManager(mContext));
        adapter.addAll(buyBeanDao.queryBuilder().orderDesc(BuyBeanDao.Properties.Id).build().list());
        lv.setAdapter(adapter);
        ab.setView(v);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Lg.e("点击",adapter.getAllData().get(position));
                AddNoteActivity.start(mContext,adapter.getAllData().get(position).FName);
                alertDialog.dismiss();
            }
        });
//        ab.setMessage(msg);
        ab.setPositiveButton("返回",null);
        ab.setNeutralButton("添加项目", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                insertBuyBean();
            }
        });
        alertDialog = ab.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
    AlertDialog getAlertDialog;
    private void insertBuyBean(){
        AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
//        ab.setTitle("请选择项目");
        View v = LayoutInflater.from(mContext).inflate(R.layout.dlg_buy_add, null);
        final EditText editText = v.findViewById(R.id.ed_buyname);
        Button button = v.findViewById(R.id.btn);
        ab.setView(v);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LocDataUtil.addBuyBean(editText.getText().toString())){
                    AddNoteActivity.start(mContext,editText.getText().toString());
                    alertDialog.dismiss();
                    getAlertDialog.dismiss();
                }else{
                    LoadingUtil.showAlter(mContext,"该项目已存在");
                }
            }
        });
        getAlertDialog = ab.create();
//        getAlertDialog.getWindow().setBackgroundDrawableResource(R.drawable.loading);//设置背景
        getAlertDialog.setCanceledOnTouchOutside(false);
        getAlertDialog.show();
    }





    @OnClick({R.id.iv_add, R.id.ll1,R.id.bottom_btn_purchase,R.id.bottom_btn_sale,R.id.bottom_btn_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                selectAdd();
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
            case R.id.bottom_btn_sale:
                viewPager.setCurrentItem(1, true);
                resetBottomView();
                ivSale.setImageResource(R.mipmap.rili);
                tvSale.setTextColor(tvcolor);
                ivAdd.setVisibility(View.VISIBLE);
                break;
            case R.id.bottom_btn_setting:
                viewPager.setCurrentItem(2, true);
                resetBottomView();
                ivSetting.setImageResource(R.mipmap.set);
                tvSetting.setTextColor(tvcolor);
                ivAdd.setVisibility(View.GONE);
                break;

        }
    }
}
