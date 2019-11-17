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
    public String Nname;
    public String Ntime;
    public String NDetail;
    public String FCreateTime;

    public NoteBean(String Nname, String Ntime) {
        this.Nname = Nname;
        this.Ntime = Ntime;
    }

    @Generated(hash = 1274114053)
    public NoteBean(Long id, String Nname, String Ntime, String NDetail,
            String FCreateTime) {
        this.id = id;
        this.Nname = Nname;
        this.Ntime = Ntime;
        this.NDetail = NDetail;
        this.FCreateTime = FCreateTime;
    }

    @Generated(hash = 451626881)
    public NoteBean() {
    }
    public String getNname() {
        return this.Nname;
    }
    public void setNname(String Nname) {
        this.Nname = Nname;
    }
    public String getNtime() {
        return this.Ntime;
    }
    public void setNtime(String Ntime) {
        this.Ntime = Ntime;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNDetail() {
        return this.NDetail;
    }

    public void setNDetail(String NDetail) {
        this.NDetail = NDetail;
    }

    public String getFCreateTime() {
        return this.FCreateTime;
    }

    public void setFCreateTime(String FCreateTime) {
        this.FCreateTime = FCreateTime;
    }

}
