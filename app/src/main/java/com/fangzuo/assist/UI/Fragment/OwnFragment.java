package com.fangzuo.assist.UI.Fragment;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fangzuo.assist.ABase.BaseFragment;
import com.fangzuo.assist.Activity.Crash.App;
import com.fangzuo.assist.R;
import com.fangzuo.assist.Utils.GreenDaoManager;
import com.fangzuo.assist.Utils.Info;
import com.fangzuo.greendao.gen.NoteBeanDao;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OwnFragment extends BaseFragment {
    @BindView(R.id.iv_book)
    ImageView ivBook;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.cb_change_view)
    CheckBox cbChangeView;

    private FragmentActivity mContext;
    private NoteBeanDao noteBeanDao;
    private boolean isChangeView=false;
    public OwnFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_own, container, false);
        ButterKnife.bind(this, v);
        mContext = getActivity();
        noteBeanDao = GreenDaoManager.getmInstance(mContext).getDaoSession().getNoteBeanDao();
        return v;
    }

    @Override
    public void initView() {
        cbChangeView.setChecked(Hawk.get(Info.CbChangeView,false));
        isChangeView = cbChangeView.isChecked();
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initListener() {
        cbChangeView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Hawk.put(Info.ChangeView,1);
                }else{
                    Hawk.put(Info.ChangeView,0);
                }
                Hawk.put(Info.CbChangeView,isChecked);
                //当控件状态与初始化的不一致时，设置值给HomeFragment布局做更新state
                if (isChecked != isChangeView){
                    App.hasChangeView = true;
                    isChangeView=isChecked;
                }
            }
        });
    }


    @Override
    protected void OnReceive(String barCode) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            tvNum.setText("叙:"+noteBeanDao.loadAll().size());
            //相当于Fragment的onResume
        } else {
            //相当于Fragment的onPause
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.iv_book, R.id.tv_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_book:
                break;
            case R.id.tv_num:
                tvNum.setText("叙:"+noteBeanDao.loadAll().size());

                break;
        }
    }
}
