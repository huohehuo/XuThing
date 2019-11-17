package com.fangzuo.assist.UI.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fangzuo.assist.ABase.BaseActivity;
import com.fangzuo.assist.Dao.NoteBean;
import com.fangzuo.assist.R;
import com.fangzuo.assist.Utils.CommonUtil;
import com.fangzuo.assist.Utils.Toast;
import com.fangzuo.assist.Utils.VibratorUtil;
import com.fangzuo.greendao.gen.NoteBeanDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNoteActivity extends BaseActivity {

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ed_name)
    EditText edName;
    private NoteBeanDao noteBeanDao;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);
        initBar();
        noteBeanDao = daoSession.getNoteBeanDao();
    }

    @Override
    protected void initData() {
        tvTime.setText(CommonUtil.getTime(true));
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
                noteBeanDao.insert(new NoteBean(edName.getText().toString(),tvTime.getText().toString()));
                Toast.showText(mContext,"添加成功");
                VibratorUtil.Vibrate(mContext,250);
                break;
            case R.id.tv_time:
                datePicker(tvTime);
                break;
        }
    }
}
