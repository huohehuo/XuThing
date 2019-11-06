package com.lins.xuthing.Beans;

public class InStoreNumBean {
	public String FItemID;
	public String FStockID;
	public String FStockPlaceID;
	public String FBatchNo;
	public String FKFDate;
	public String FKFPeriod;
	public InStoreNumBean(){}

	public InStoreNumBean(String FItemID, String FStockID, String FStockPlaceID, String FBatchNo) {
		this.FItemID = FItemID;
		this.FStockID = FStockID;
		this.FStockPlaceID = FStockPlaceID;
		this.FBatchNo = FBatchNo;
	}

	@Override
	public String toString() {
		return "InStoreNumBean{" +
				"FItemID='" + FItemID + '\'' +
				", FStockID='" + FStockID + '\'' +
				", FStockPlaceID='" + FStockPlaceID + '\'' +
				", FBatchNo='" + FBatchNo + '\'' +
				'}';
	}
}
