package com.fangzuo.assist.Fragment;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fangzuo.assist.ABase.BaseFragment;
import com.fangzuo.assist.R;
import com.fangzuo.assist.Utils.GreenDaoManager;
import com.fangzuo.greendao.gen.NoteBeanDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OwnFragment extends BaseFragment {
    @BindView(R.id.iv_book)
    ImageView ivBook;
    @BindView(R.id.tv_num)
    TextView tvNum;
    private FragmentActivity mContext;
    private NoteBeanDao noteBeanDao;

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
