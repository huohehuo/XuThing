package com.fangzuo.assist.UI.Fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.fangzuo.assist.ABase.BaseFragment;
import com.fangzuo.assist.Activity.ProductInStorageActivity;
import com.fangzuo.assist.Activity.PurchaseInStorageActivity;
import com.fangzuo.assist.Activity.PurchaseOrderActivity;
import com.fangzuo.assist.Adapter.GridViewAdapter;
import com.fangzuo.assist.Adapter.HomeRyAdapter;
import com.fangzuo.assist.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.Beans.SettingList;
import com.fangzuo.assist.Dao.NoteBean;
import com.fangzuo.assist.R;
import com.fangzuo.assist.UI.Activity.ShowNoteActivity;
import com.fangzuo.assist.Utils.CommonUtil;
import com.fangzuo.assist.Utils.EventBusInfoCode;
import com.fangzuo.assist.Utils.EventBusUtil;
import com.fangzuo.assist.Utils.GetSettingList;
import com.fangzuo.assist.Utils.GreenDaoManager;
import com.fangzuo.assist.Utils.Lg;
import com.fangzuo.greendao.gen.NoteBeanDao;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends BaseFragment {
    @BindView(R.id.ry_list)
    EasyRecyclerView ryList;
    HomeRyAdapter adapter;
    private FragmentActivity mContext;
    private NoteBeanDao noteBeanDao;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);
        mContext = getActivity();
        noteBeanDao = GreenDaoManager.getmInstance(mContext).getDaoSession().getNoteBeanDao();
        return v;
    }

    @Override
    public void initView() {
        ryList.setAdapter(adapter = new HomeRyAdapter(mContext));
        ryList.setLayoutManager(new LinearLayoutManager(mContext));
//        ryList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected void initData() {

        ryList.setRefreshing(true);
        adapter.clear();
        adapter.addAll(noteBeanDao.loadAll());
        adapter.notifyDataSetChanged();
        ryList.setRefreshing(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initListener() {
        //列表点击事件
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                NoteBean thisNo = adapter.getAllData().get(position);
                Lg.e("点击",thisNo);
                ShowNoteActivity.start(mContext,thisNo.id+"");

            }
        });
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final int position) {
                new AlertDialog.Builder(mContext)
                        .setTitle("是否删除该条数据")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                noteBeanDao.deleteInTx(adapter.getAllData().get(position));
                                initData();
                            }
                        })
                        .create().show();
                return true;
            }
        });
    }


    @Override
    protected void OnReceive(String barCode) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
