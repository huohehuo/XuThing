package com.fangzuo.assist.Beans;

import com.fangzuo.assist.Dao.AddrBean;
import com.fangzuo.assist.Dao.BuyAtBean;
import com.fangzuo.assist.Dao.BuyBean;

import java.util.ArrayList;

public class WebResponse {
	public boolean state;
	public String backString;
	public int size;
	public WebResponse() {
		this.backString = "";
	}
	public WebResponse(boolean state, String backString) {
		this.state = state;
		this.backString = backString;
	}
	public ArrayList<BuyBean> buyBeans;
	public ArrayList<BuyAtBean> buyAtBeans;
	public ArrayList<AddrBean> addrBeans;


}
