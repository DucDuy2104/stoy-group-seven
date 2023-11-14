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
import com.example.duanmau1.admin.adapter.MoHinhAdminAdapter;
import com.example.duanmau1.dao.MoHinhDao;
import com.example.duanmau1.model.MoHinh;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MoHinhAdminFragment extends Fragment {
    View view;
    RecyclerView recMoHinh;
    MoHinhAdminAdapter adapter;
    MoHinhDao moHinhDao;
    List<MoHinh> mListMoHinh;
    FloatingActionButton fBtnAdd;
    private final DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference("list_mo_hinh");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mo_hinh_admin, container, false);
        initView();
        setView();
        setOnClick();
        return view;
    }

    private void setOnClick() {
        fBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setRealTime() {
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MoHinh moHinh = snapshot.getValue(MoHinh.class);
                mListMoHinh.add(moHinh);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                MoHinh moHinh = snapshot.getValue(MoHinh.class);
                for (int i = 0; i < mListMoHinh.size(); i++) {
                    if (moHinh.getMaMh() == mListMoHinh.get(i).getMaMh()) {
                        mListMoHinh.remove(i);
                        adapter.notifyDataSetChanged();
                        return;
                    }
                }

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setView() {
        moHinhDao = new MoHinhDao(getContext());
        mListMoHinh = new ArrayList<>();
        adapter = new MoHinhAdminAdapter(mListMoHinh, getContext());
        recMoHinh.setLayoutManager(new LinearLayoutManager(getContext()));
        recMoHinh.setAdapter(adapter);
        setRealTime();
    }

    private void initView() {
        fBtnAdd = view.findViewById(R.id.fBtn_addMoHinh);
        recMoHinh = view.findViewById(R.id.rec_moHinhAdminFr);
    }
}
