package com.example.duanmau1.model;

public class HoaDonChiTiet {
    private int mahd;
    private String ngayLap, dichVuVc;
    private boolean tinhTrang;
    private int tienVanChuyen;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String ngayLap, String dichVuVc, boolean tinhTrang, int tienVanChuyen) {
        this.ngayLap = ngayLap;
        this.dichVuVc = dichVuVc;
        this.tinhTrang = tinhTrang;
        this.tienVanChuyen = tienVanChuyen;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getDichVuVc() {
        return dichVuVc;
    }

    public void setDichVuVc(String dichVuVc) {
        this.dichVuVc = dichVuVc;
    }

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getTienVanChuyen() {
        return tienVanChuyen;
    }

    public void setTienVanChuyen(int tienVanChuyen) {
        this.tienVanChuyen = tienVanChuyen;
    }
}
