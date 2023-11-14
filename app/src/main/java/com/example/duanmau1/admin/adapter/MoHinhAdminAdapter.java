package com.example.duanmau1.admin.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duanmau1.R;
import com.example.duanmau1.dao.MoHinhDao;
import com.example.duanmau1.model.LoaiMoHinh;
import com.example.duanmau1.model.MoHinh;

import java.util.List;

public class MoHinhAdminAdapter extends RecyclerView.Adapter<MoHinhAdminAdapter.MoHinhViewHolder> {
    private List<MoHinh> moHinhList;
    private Context context;
    private MoHinhDao moHinhDao;

    public MoHinhAdminAdapter(List<MoHinh> moHinhList, Context context) {
        this.moHinhList = moHinhList;
        this.context = context;
        this.moHinhDao = new MoHinhDao(context);
    }

    public void setMoHinhList(List<MoHinh> moHinhList) {
        this.moHinhList = moHinhList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoHinhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mo_hinh_admin, parent, false);
        return new MoHinhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoHinhViewHolder holder, int position) {
        MoHinh moHinh = moHinhList.get(position);
        Glide.with(context).load(Uri.parse(moHinh.getImgUri())).into(holder.imgMoHinh);
        holder.tvName.setText(moHinh.getTenMh());
        holder.tvRate.setText("Rate: " + moHinh.getDanhGia());
        holder.tvPrice.setText(moHinh.getGiaBan() + "");
        holder.tvAmount.setText("Amount: " + moHinh.getSoLuong());
        holder.tvSold.setText("Sold: " + moHinh.getSoLuongDaBan());
        moHinhDao.getMoHinhById(moHinh.getMaMh(), new MoHinhDao.OnGetMoHinhSuccess() {
            @Override
            public void onGetSuccess(MoHinh moHinh, LoaiMoHinh loaiMoHinh) {
                holder.tvType.setText("Type: " + loaiMoHinh.getTenLoai());
            }
        });
    }

    @Override
    public int getItemCount() {
        return moHinhList.size();
    }

    public class MoHinhViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMoHinh;
        private TextView tvName, tvRate, tvPrice, tvAmount, tvSold, tvType;
        private ImageButton btnDelete;
        public MoHinhViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMoHinh = itemView.findViewById(R.id.img_itemMoHinh);
            tvName = itemView.findViewById(R.id.tv_nameItemMoHinh);
            tvRate = itemView.findViewById(R.id.tv_rateItemMoHinh);
            tvPrice =itemView.findViewById(R.id.tv_priceItemMoHinh);
            tvAmount= itemView.findViewById(R.id.tv_amountItemMoHinh);
            tvSold = itemView.findViewById(R.id.tv_soldItemMoHinh);
            tvType =itemView.findViewById(R.id.tv_typeItemMoHinh);
            btnDelete = itemView.findViewById(R.id.btn_deleteItemMoHinh);
        }
    }
}
