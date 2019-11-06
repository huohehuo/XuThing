package com.lins.xuthing.Base;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lins.xuthing.Beans.EventBusEvent.ClassEvent;
import com.lins.xuthing.Utils.EventBusUtil;
import com.lins.xuthing.Utils.GreenDaoManager;
import com.lins.xuthing.Utils.Lg;
import com.lins.xuthing.Utils.MathUtil;
import com.lins.xuthing.Utils.Toast;
import com.lins.xuthing.gen.DaoSession;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;

/**
 * Created by Administrator on 2019/11/6.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;
    private IntentFilter scanDataIntentFilter;
    public String barcodeStr;
    public String cutXing;
    public String TAG = getClass().getSimpleName();
    public Gson gson;
    public DaoSession daoSession;
    private String date;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        gson = new Gson();
        daoSession = GreenDaoManager.getmInstance(mContext).getDaoSession();
        //UBX
//        initScan();




        initView();
        initData();
        initListener();
    }

    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(ClassEvent event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    protected void receiveEvent(ClassEvent event) {

    }

    public String datePicker(final TextView v) {
        final DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            }
        }, year, month, day);
        datePickerDialog.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int year = datePickerDialog.getDatePicker().getYear();
                int month = datePickerDialog.getDatePicker().getMonth();
                int day = datePickerDialog.getDatePicker().getDayOfMonth();
                date = year + "-" + ((month < 9) ? "0" + (month + 1) : (month + 1)) + "-" + ((day < 10) ? "0" + day : day);
                Toast.showText(mContext, date);
                v.setText(date);
                datePickerDialog.dismiss();

            }
        });
        datePickerDialog.show();
        return date;
    }
    public String datePickerWithData(final TextView v,String time) {
        if (time.length()==10){//2019-01-01
            year = MathUtil.toInt(time.substring(0,4));
            month = MathUtil.toInt(time.substring(5,7))-1;
            day = MathUtil.toInt(time.substring(8,time.length()));
        }
        Lg.e("获取时间1"+year);
        Lg.e("获取时间2"+month);
        Lg.e("获取时间3"+day);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            }
        }, year, month, day);
        datePickerDialog.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int year = datePickerDialog.getDatePicker().getYear();
                int month = datePickerDialog.getDatePicker().getMonth();
                int day = datePickerDialog.getDatePicker().getDayOfMonth();
                date = year + "-" + ((month < 10) ? "0" + (month + 1) : (month + 1)) + "-" + ((day < 10) ? "0" + day : day);
                Toast.showText(mContext, date);
                v.setText(date);
                datePickerDialog.dismiss();

            }
        });
        datePickerDialog.show();
        return date;
    }
    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    //androidmanifest中获取版本名称
    public String getVersionName() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    getPackageName(), 0);

            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;

            System.out.println("versionName=" + versionName + ";versionCode="
                    + versionCode);

            return versionName;
        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }

        return "";
    }

}
