package com.fangzuo.assist.UI.Fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fangzuo.assist.ABase.BaseFragment;
import com.fangzuo.assist.Adapter.BaseDataRyAdapter;
import com.fangzuo.assist.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.Dao.BuyBean;
import com.fangzuo.assist.R;
import com.fangzuo.assist.Utils.CommonUtil;
import com.fangzuo.assist.Utils.EventBusInfoCode;
import com.fangzuo.assist.Utils.EventBusUtil;
import com.fangzuo.assist.Utils.GreenDaoManager;
import com.fangzuo.assist.Utils.Lg;
import com.fangzuo.assist.Utils.LocDataUtil;
import com.fangzuo.assist.Utils.Toast;
import com.fangzuo.greendao.gen.BuyBeanDao;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BuyBeanFragment extends BaseFragment {
    @BindView(R.id.ry_list)
    EasyRecyclerView ryList;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.btn_add)
    Button btnAdd;
    BaseDataRyAdapter adapter;
    private FragmentActivity mContext;
    private BuyBeanDao buyBeanDao;

    public BuyBeanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_base_data, container, false);
        ButterKnife.bind(this, v);
        mContext = getActivity();
        buyBeanDao = GreenDaoManager.getmInstance(mContext).getDaoSession().getBuyBeanDao();

        return v;
    }

    @Override
    public void initView() {
//        ryList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected void initData() {
        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.BaseData_Tip, buyBeanDao.loadAll().size() + ""));
        adapter = new BaseDataRyAdapter(mContext);
        ryList.setAdapter(adapter);
        ryList.setLayoutManager(new LinearLayoutManager(mContext));
        ryList.setRefreshing(true);
        adapter.clear();
        adapter.addAll(buyBeanDao.queryBuilder().orderDesc(BuyBeanDao.Properties.Id).build().list());
        adapter.notifyDataSetChanged();
        ryList.setRefreshing(false);

        loadListData();
    }

    private void loadListData() {
//        if (null == ryList)return;
//        if (Hawk.get(Info.ChangeView,0)==0){
//            adapter = new HomeRyAdapter(mContext,0);
//        }else{
//            adapter = new HomeRyAdapter(mContext,1);
//        }
//        ryList.setAdapter(adapter);
//        ryList.setLayoutManager(new LinearLayoutManager(mContext));
//        ryList.setRefreshing(true);
//        adapter.clear();
//        adapter.addAll(buyBeanDao.loadAll());
//        ryList.setRefreshing(false);


        adapter.notifyDataSetChanged();
        //列表点击事件
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                BuyBean thisNo = adapter.getAllData().get(position);
                Lg.e("点击", thisNo);
//                ShowNoteActivity.start(mContext,thisNo.id+"");

            }
        });
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final int position) {
                new AlertDialog.Builder(mContext)
                        .setTitle("是否删除该条数据")
                        .setMessage("该记录下的所有数据将一并清空")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delBuyBean(position);
                            }
                        })
                        .create().show();
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initListener() {

    }
    //删除数据
    private void delBuyBean(int position){
        BuyBean buyBean = adapter.getAllData().get(position);
        if (LocDataUtil.checkNoteBean(buyBean.FName)>0){
            Toast.showText(mContext,"该种类存在数据，不允许删除");
            return;
        }
        buyBeanDao.deleteInTx(buyBean);
        Toast.showText(mContext,"删除成功");
        initData();

    }
    //添加新数据
    private void addBuyBean(){
        if ("".equals(edName.getText().toString())){
            Toast.showText(mContext,"请输入添加的种类");
            return;
        }
        if (LocDataUtil.checkBuyBean(edName.getText().toString())){
            Toast.showText(mContext,"该种类已存在，不能重复添加");
            return;
        }
        buyBeanDao.insert(new BuyBean(edName.getText().toString(), CommonUtil.getTime(true), CommonUtil.getTimeLong(false)));
        edName.setText("");
        initData();

    }


    @OnClick({R.id.ed_name, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_name:
                break;
            case R.id.btn_add:
                addBuyBean();
                break;
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (null != buyBeanDao) {
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.BaseData_Tip, buyBeanDao.loadAll().size() + ""));
            }
//            adapter.clear();
//            adapter.addAll(buyBeanDao.loadAll());
//            initData();
            //相当于Fragment的onResume
        } else {
            //相当于Fragment的onPause
        }
    }

    @Override
    protected void OnReceive(String barCode) {

    }
}
