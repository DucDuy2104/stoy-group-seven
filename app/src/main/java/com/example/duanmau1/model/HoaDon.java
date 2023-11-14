package com.example.duanmau1.model;

import java.util.List;

public class HoaDon {
    private int maHd; // mã hóa đơn
    private int maNd; //mã người dùng
    private List<Integer> listMaMh; //list các sản phẩm trong hóa đơn

    public HoaDon() {
    }

    public HoaDon(int maNd, List<Integer> listMaMh) {
        this.maNd = maNd;
        this.listMaMh = listMaMh;
    }

    public int getMaHd() {
        return maHd;
    }

    public void setMaHd(int maHd) {
        this.maHd = maHd;
    }

    public int getMaNd() {
        return maNd;
    }

    public void setMaNd(int maNd) {
        this.maNd = maNd;
    }

    public List<Integer> getListMaMh() {
        return listMaMh;
    }

    public void setListMaMh(List<Integer> listMaMh) {
        this.listMaMh = listMaMh;
    }
}
