package com.example.duanmau1.dao;

import com.example.duanmau1.model.HoaDonChiTiet;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDao {
    private DatabaseReference dbReference;
    public HoaDonChiTietDao() {
        this.dbReference  = FirebaseDatabase.getInstance().getReference("list_hoa_don_chi_tiet");
    }
    public interface OnGetAllChiTietSuccess {
        void onGetAllSuccess(List<HoaDonChiTiet> hoaDonChiTiets);
    }

    public interface  OnGetChiTietSuccess {
        void onGetChiTietSuccess(HoaDonChiTiet hoaDonChiTiet);
    }
    public void getAllChiTiet(OnGetAllChiTietSuccess onGetAllChiTietSucces) {
        List<HoaDonChiTiet> hoaDonChiTiets = new ArrayList<>();
        dbReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot chiTietSnapshot: dataSnapshot.getChildren()) {
                    HoaDonChiTiet hoaDonChiTiet = chiTietSnapshot.getValue(HoaDonChiTiet.class);
                    hoaDonChiTiets.add(hoaDonChiTiet);
                }
                onGetAllChiTietSucces.onGetAllSuccess(hoaDonChiTiets);
            }
        });

    }
    public void addChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        dbReference.child("chi_tiet_" + hoaDonChiTiet.getMahd()).setValue(hoaDonChiTiet);
    }

    public void updateChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        dbReference.child("chi_tiet_" + hoaDonChiTiet.getMahd()).setValue(hoaDonChiTiet);
    }

    public void deleteChiTiet(int maHd) {
        dbReference.child("chi_tiet_" + maHd).removeValue();
    }

    public void getChiTietByMahd(int maHd, OnGetChiTietSuccess onGetChiTietSuccess) {
        dbReference.child("chi_tiet_" + maHd).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                HoaDonChiTiet hoaDonChiTiet  = dataSnapshot.getValue(HoaDonChiTiet.class);
                onGetChiTietSuccess.onGetChiTietSuccess(hoaDonChiTiet);
            }
        });
    }
}


