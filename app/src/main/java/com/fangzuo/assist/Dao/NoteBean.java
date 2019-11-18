package com.fangzuo.assist.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by NB on 2017/7/26.
 */
@Entity
public class NoteBean {

    @Id(autoincrement = true)
    public Long id;
    public String NTitle;//标题
    public String Ntime;//时间
    public String NDetail;//日志问价按
    public int NMoodLocInt;//
//    public byte[] NPic;
    public String NCreateTime;

    public NoteBean(String NTitle, String Ntime) {
        this.NTitle = NTitle;
        this.Ntime = Ntime;
    }

    @Generated(hash = 1462593919)
    public NoteBean(Long id, String NTitle, String Ntime, String NDetail,
            int NMoodLocInt, String NCreateTime) {
        this.id = id;
        this.NTitle = NTitle;
        this.Ntime = Ntime;
        this.NDetail = NDetail;
        this.NMoodLocInt = NMoodLocInt;
        this.NCreateTime = NCreateTime;
    }

    @Generated(hash = 451626881)
    public NoteBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNTitle() {
        return this.NTitle;
    }

    public void setNTitle(String NTitle) {
        this.NTitle = NTitle;
    }

    public String getNtime() {
        return this.Ntime;
    }

    public void setNtime(String Ntime) {
        this.Ntime = Ntime;
    }

    public String getNDetail() {
        return this.NDetail;
    }

    public void setNDetail(String NDetail) {
        this.NDetail = NDetail;
    }

    public int getNMoodLocInt() {
        return this.NMoodLocInt;
    }

    public void setNMoodLocInt(int NMoodLocInt) {
        this.NMoodLocInt = NMoodLocInt;
    }

    public String getNCreateTime() {
        return this.NCreateTime;
    }

    public void setNCreateTime(String NCreateTime) {
        this.NCreateTime = NCreateTime;
    }


}
