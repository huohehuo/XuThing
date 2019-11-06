package com.lins.xuthing.Beans;

/**
 * Created by NB on 2017/7/26.
 */
public class CreateData {
    public String FCreateData;
    public String FShelfLife;
    public String FDeathData;
    public String FBatch;
    public String FID;
    public String FStorageID;
    public CreateData() {
    }

    public CreateData(String FCreateData, String shelf) {
        this.FCreateData = FCreateData;
        this.FShelfLife = shelf;
    }
}
