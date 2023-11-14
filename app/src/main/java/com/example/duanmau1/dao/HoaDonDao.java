package com.example.duanmau1.dao;

import com.example.duanmau1.model.HoaDon;
import com.example.duanmau1.model.HoaDonChiTiet;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDao {
    private DatabaseReference dbReference;
    private HoaDonChiTietDao hoaDonChiTietDao;
    public HoaDonDao() {
        this.dbReference  = FirebaseDatabase.getInstance().getReference("list_hoa_don");
        this.hoaDonChiTietDao = new HoaDonChiTietDao();
    }

    public interface OnGetHoaDonSuccess{
        void onGetSuccess(HoaDon hoaDon, HoaDonChiTiet hoaDonChiTiet);
    }

    public interface OnGetAllHoaDonSuccess {
        void OnGetSuccess(List<HoaDon> hoaDonList, List<HoaDonChiTiet> hoaDonChiTiets);
    }

    public void getAllHoaDon(OnGetAllHoaDonSuccess onGetAllHoaDonSuccess) {
        List<HoaDon> hoaDonList = new ArrayList<>();
        dbReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot hoaDonSnapshot: dataSnapshot.getChildren()) {
                    HoaDon hoaDon = hoaDonSnapshot.getValue(HoaDon.class);
                    hoaDonList.add(hoaDon);
                }

                hoaDonChiTietDao.getAllChiTiet(new HoaDonChiTietDao.OnGetAllChiTietSuccess() {
                    @Override
                    public void onGetAllSuccess(List<HoaDonChiTiet> hoaDonChiTiets) {
                        onGetAllHoaDonSuccess.OnGetSuccess(hoaDonList, hoaDonChiTiets);
                    }
                });

            }
        });
    }

    public void addHoaDon(HoaDon hoaDon, String ngayLap, String dichVuVc, boolean tinhTrang, int tienVanChuyen) {
        getAllHoaDon(new OnGetAllHoaDonSuccess() {
            @Override
            public void OnGetSuccess(List<HoaDon> hoaDonList, List<HoaDonChiTiet> hoaDonChiTiets) {
                int maHd = 0;
                if (hoaDonList.size() > 0) {
                    maHd = hoaDonList.get(hoaDonList.size()- 1).getMaHd() + 1;
                }
                hoaDon.setMaHd(maHd);
                dbReference.child("hoa_don_" +hoaDon.getMaHd()).setValue(hoaDon);
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(ngayLap, dichVuVc, tinhTrang, tienVanChuyen);
                hoaDonChiTiet.setMahd(maHd);
                hoaDonChiTietDao.addChiTiet(hoaDonChiTiet);
            }
        });

    }

    public void updateHoaDon(HoaDon hoaDon, String ngayLap, String dichVuVc, boolean tinhTrang, int tienVanChuyen) {
        dbReference.child("hoa_don_" +hoaDon.getMaHd()).setValue(hoaDon);
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(ngayLap, dichVuVc, tinhTrang, tienVanChuyen);
        hoaDonChiTiet.setMahd(hoaDon.getMaHd());
        hoaDonChiTietDao.addChiTiet(hoaDonChiTiet);
    }

    public void deleteHoaDon(int mahd){
        dbReference.child("hoa_don_" + mahd).removeValue();
        dbReference.child("chi_tiet_" + mahd).removeValue();
    }

    public void getHoaDonById(int maHd, OnGetHoaDonSuccess onGetHoaDonSuccess) {
        dbReference.child("hoa_don_" + maHd).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                HoaDon hoaDon = dataSnapshot.getValue(HoaDon.class);
                hoaDonChiTietDao.getChiTietByMahd(hoaDon.getMaHd(), new HoaDonChiTietDao.OnGetChiTietSuccess() {
                    @Override
                    public void onGetChiTietSuccess(HoaDonChiTiet hoaDonChiTiet) {
                        onGetHoaDonSuccess.onGetSuccess(hoaDon,hoaDonChiTiet);
                    }
                });
            }
        });
    }
}
