package com.fangzuo.assist.UI.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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
import com.fangzuo.assist.Adapter.HomeRyAdapter;
import com.fangzuo.assist.Adapter.MoodRyAdapter;
import com.fangzuo.assist.Beans.MoodBean;
import com.fangzuo.assist.Dao.NoteBean;
import com.fangzuo.assist.R;
import com.fangzuo.assist.Utils.CommonUtil;
import com.fangzuo.assist.Utils.ImageUtil;
import com.fangzuo.assist.Utils.Lg;
import com.fangzuo.assist.Utils.Toast;
import com.fangzuo.assist.Utils.VibratorUtil;
import com.fangzuo.assist.widget.SpinnerAddrUIDlg;
import com.fangzuo.assist.widget.SpinnerBuyUIDlg;
import com.fangzuo.assist.widget.piccut.CropImageActivity;
import com.fangzuo.assist.widget.piccut.SelectPhotoDialog;
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
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ed_detail)
    EditText edDetail;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.sp_buy)
    SpinnerBuyUIDlg spBuyUIDlg;
    @BindView(R.id.sp_addr)
    SpinnerAddrUIDlg spAddrUIDlg;

    private NoteBeanDao noteBeanDao;
    private NoteBean noteBean;
    public static String baseLoc = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);
        initBar();
        getPermisssion();
        noteBean = new NoteBean();
        noteBeanDao = daoSession.getNoteBeanDao();

    }

    @Override
    protected void initData() {
        tvTime.setText(CommonUtil.getTime(true));
        spBuyUIDlg.setAutoSelection("","",false);
        spAddrUIDlg.setAutoSelection("","",false);
    }

    @Override
    protected void initListener() {


    }

    @Override
    protected void OnReceive(String code) {

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, AddNoteActivity.class);
//        intent.putExtra("activity", activity);
//        intent.putStringArrayListExtra("fid", fid);
        context.startActivity(intent);
    }


    @OnClick({R.id.iv_add, R.id.tv_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                saveNote();
                break;
            case R.id.tv_time:
                datePicker(tvTime);
                break;
        }
    }

    private void saveNote(){
        noteBean = new NoteBean();
//        noteBean.NTitle= edName.getText().toString();
        noteBean.NDetail= edDetail.getText().toString();
        noteBean.NBuyName= spBuyUIDlg.getDataName();
        noteBean.NAddrName= spAddrUIDlg.getDataName();
        noteBean.Ntime = tvTime.getText().toString();
        noteBean.NCreateTime = CommonUtil.getTimeLong(true);
        noteBeanDao.insert(noteBean);
        Toast.showText(mContext, "添加成功");
        VibratorUtil.Vibrate(mContext, 200);
        finish();
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
