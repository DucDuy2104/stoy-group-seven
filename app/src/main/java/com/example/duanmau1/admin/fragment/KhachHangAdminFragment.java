package com.example.duanmau1.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau1.R;
import com.example.duanmau1.admin.adapter.KhachHangAdminAdapter;
import com.example.duanmau1.dao.NguoiDungDao;
import com.example.duanmau1.model.NguoiDung;

import java.util.List;

public class KhachHangAdminFragment extends Fragment {
    View view;
    RecyclerView recKhachHang;
    KhachHangAdminAdapter khachHangAdminAdapter;
    NguoiDungDao nguoiDungDao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_khach_hang_admin, container, false);
        initView();
        setView();
        return view;
    }

    private void setView() {
        nguoiDungDao = new NguoiDungDao(getContext());
        nguoiDungDao.getListNguoiDung(new NguoiDungDao.OnGetListSuccess() {
            @Override
            public void onGetListSuccess(List<NguoiDung> nguoiDungs) {
                khachHangAdminAdapter = new KhachHangAdminAdapter(nguoiDungs, getContext());
                recKhachHang.setAdapter(khachHangAdminAdapter);
                recKhachHang.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
    }

    private void initView() {
        recKhachHang = view.findViewById(R.id.rec_khachHangAdminFr);
    }
}
