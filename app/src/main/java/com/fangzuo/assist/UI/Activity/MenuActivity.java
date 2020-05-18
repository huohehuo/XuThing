package com.fangzuo.assist.UI.Activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
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
//        tvUser.setText("当前用户:" + ShareUtil.getInstance(mContext).getUserName());
//        ivPurchase.setImageResource(R.mipmap.purchase);
//        tvPurchase.setTextColor(tvcolor);

        //[["FHTZD000001",100726.0,"电子阅读器BOOX Nova Pro 前壳玻璃三合一黑色+后壳蓝色 (BOOX丝印，RK3288_V1.1_C，2+32G，2800mha，中文版，新贴纸)","OPC0587R",101032.0,100001.0,100001.0,1.0,"SAL_SaleOrder","SaleOrder-DeliveryNotice"]]
        String string = "[[\"FHTZD000001\",100726.0,\"电子阅读器BOOX Nova Pro 前壳玻璃三合一黑色+后壳蓝色 (BOOX丝印，RK3288_V1.1_C，2+32G，2800mha，中文版，新贴纸)\",\"OPC0587R\",101032.0,100001.0,100001.0,1.0,\"SAL_SaleOrder\",\"SaleOrder-DeliveryNotice\"]]";
        setData(string);


    }
    private void setData(String object){
        JSONArray jsonArray = null;
        try {
            Lg.e("转换",gson.toJsonTree(object));
            Lg.e("转换",gson.toJson(object));
            jsonArray = new JSONArray(object);
            Lg.e("得到"+jsonArray);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray json = new JSONArray(jsonArray.get(i).toString());
//                PushDownSub dpt = new PushDownSub();
//                dpt.FNumber = json.get(0).toString();
//                dpt.FName = json.get(1).toString();
//                dpt.FNumber = json.get(2).toString();
//                dpt.FOrg = json.get(3).toString();
//                dpt.FISSTOCK = json.get(4).toString();
//                department.add(dpt);

                Lg.e("组合成"+json);
//                adapter.add(dpt);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Lg.e("明细",e.getMessage());
        }
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
        ab.setPositiveButton("取消",null);
        alertDialog = ab.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
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
