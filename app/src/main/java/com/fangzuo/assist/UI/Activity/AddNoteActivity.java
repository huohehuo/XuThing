package com.fangzuo.assist.UI.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fangzuo.assist.ABase.BaseActivity;
import com.fangzuo.assist.Adapter.BaseDataRyAdapter;
import com.fangzuo.assist.Adapter.BuyAtRyAdapter;
import com.fangzuo.assist.Adapter.HomeRyAdapter;
import com.fangzuo.assist.Adapter.MoodRyAdapter;
import com.fangzuo.assist.Beans.MoodBean;
import com.fangzuo.assist.Dao.BuyAtBean;
import com.fangzuo.assist.Dao.BuyBean;
import com.fangzuo.assist.Dao.NoteBean;
import com.fangzuo.assist.R;
import com.fangzuo.assist.Utils.CommonUtil;
import com.fangzuo.assist.Utils.ImageUtil;
import com.fangzuo.assist.Utils.Lg;
import com.fangzuo.assist.Utils.MathUtil;
import com.fangzuo.assist.Utils.Toast;
import com.fangzuo.assist.Utils.VibratorUtil;
import com.fangzuo.assist.widget.SpinnerAddrUIDlg;
import com.fangzuo.assist.widget.SpinnerBuyUIDlg;
import com.fangzuo.assist.widget.ViewNumber;
import com.fangzuo.assist.widget.piccut.CropImageActivity;
import com.fangzuo.assist.widget.piccut.SelectPhotoDialog;
import com.fangzuo.greendao.gen.BuyAtBeanDao;
import com.fangzuo.greendao.gen.BuyBeanDao;
import com.fangzuo.greendao.gen.NoteBeanDao;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.hawk.Hawk;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class AddNoteActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.tv_num)
    ViewNumber tvNum;
    @BindView(R.id.ed_name)
    EditText edName;
//    @BindView(R.id.sp_buy)
//    SpinnerBuyUIDlg spBuyUIDlg;
    @BindView(R.id.sp_addr)
    SpinnerAddrUIDlg spAddrUIDlg;
    @BindView(R.id.ry_list)
    EasyRecyclerView ryList;

    BuyAtRyAdapter adapter;
    private NoteBeanDao noteBeanDao;
    private NoteBean noteBean;
    private BuyBeanDao buyBeanDao;
    private BuyBean buyBean;
    private BuyAtBeanDao buyAtBeanDao;
    private String buyBeanName;
    public static String baseLoc = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);
        initBar();
        getPermisssion();
        noteBean = new NoteBean();
        noteBeanDao = daoSession.getNoteBeanDao();
        buyBeanDao = daoSession.getBuyBeanDao();
        buyAtBeanDao = daoSession.getBuyAtBeanDao();
        Intent in = getIntent();
        buyBeanName = in.getStringExtra("buybean");
        tvTitle.setText(buyBeanName);
    }

    @Override
    protected void initData() {
//        spBuyUIDlg.setAutoSelection("","",false);
        spAddrUIDlg.setAutoSelection("","",false);
        buyBean = buyBeanDao.queryBuilder().where(BuyBeanDao.Properties.FName.eq(buyBeanName)).build().list().get(0);

        adapter = new BuyAtRyAdapter(mContext);
        ryList.setAdapter(adapter);
        ryList.setLayoutManager(new LinearLayoutManager(mContext));
        initList();
    }
    private void initList(){
        adapter.clear();
        ryList.setRefreshing(true);
        adapter.addAll(buyAtBeanDao.queryBuilder().where(BuyAtBeanDao.Properties.FBuyName.eq(buyBeanName)).orderDesc(BuyAtBeanDao.Properties.Id).build().list());
        adapter.notifyDataSetChanged();
        ryList.setRefreshing(false);
        double res=0;
        for (int i = 0; i < adapter.getAllData().size(); i++) {
            res += MathUtil.toD(adapter.getAllData().get(i).FName);
        }
        tvResult.setText("汇总:"+res);
    }

    @Override
    protected void initListener() {
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final int position) {
                new AlertDialog.Builder(mContext)
                        .setTitle("是否删除该条数据")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                buyAtBeanDao.deleteInTx(adapter.getAllData().get(position));
                                initList();
                            }
                        })
                        .create().show();
                return true;
            }
        });

    }

    @Override
    protected void OnReceive(String code) {

    }

    public static void start(Context context,String buybean) {
        Intent intent = new Intent(context, AddNoteActivity.class);
//        intent.putExtra("activity", activity);
        intent.putExtra("buybean", buybean);
//        intent.putStringArrayListExtra("fid", fid);
        context.startActivity(intent);
    }


    @OnClick({R.id.iv_add,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                saveNote();
                break;
        }
    }

    private void saveNote(){
        //当本地不存在时
        List<NoteBean> list = noteBeanDao.queryBuilder().where(NoteBeanDao.Properties.NBuyName.eq(buyBeanName)).build().list();
        if (list.size()<=0){
            noteBean = new NoteBean();
//        noteBean.NTitle= edName.getText().toString();
//            noteBean.NDetail= edDetail.getText().toString();
            noteBean.NBuyName= buyBeanName;
//            noteBean.NAddrName= spAddrUIDlg.getDataName();
            noteBean.Ntime = CommonUtil.getTimeLong(true);
            noteBean.NCreateTime = CommonUtil.getTimeLong(true);
            noteBeanDao.insert(noteBean);
            Toast.showText(mContext, "添加成功");
        }else{
            NoteBean bean = list.get(0);
            bean.Ntime = CommonUtil.getTimeLong(true);
            noteBeanDao.update(bean);
        }
        BuyAtBean buyAtBean = new BuyAtBean();
        buyAtBean.FName = tvNum.getString();
        buyAtBean.setBuyBean(buyBean);
        buyAtBean.setAddrBean(spAddrUIDlg.getData());
        buyAtBean.FCreateData = CommonUtil.getTimeLong(true);
        buyAtBeanDao.insert(buyAtBean);
        VibratorUtil.Vibrate(mContext, 200);
        initList();
    }


    //权限获取-------------------------------------------------------------
    private void getPermisssion() {
        String[] perm = {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(mContext, perm)) {
            EasyPermissions.requestPermissions(this, "必要的权限", 0, perm);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //把申请权限的回调交由EasyPermissions处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.i("permisssion", "获取成功的权限" + perms);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.i("permisssion", "获取失败的权限" + perms);
    }

}
