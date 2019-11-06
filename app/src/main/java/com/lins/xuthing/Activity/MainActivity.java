package com.lins.xuthing.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lins.xuthing.Adapter.RePrintBoxRyAdapter;
import com.lins.xuthing.Base.App;
import com.lins.xuthing.Base.BaseActivity;
import com.lins.xuthing.Beans.CommonResponse;
import com.lins.xuthing.R;
import com.lins.xuthing.RxSerivce.MySubscribe;

public class MainActivity extends BaseActivity {

    private RePrintBoxRyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getRService().doIOAction("UserIO", "0", new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    public static void start(Context context){
        Intent intent = new Intent(context,MainActivity.class);
//        intent.putExtra("activity",activity);
        context.startActivity(intent);
    }
}
