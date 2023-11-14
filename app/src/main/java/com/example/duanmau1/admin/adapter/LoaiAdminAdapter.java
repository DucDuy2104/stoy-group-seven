package com.example.duanmau1.admin.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duanmau1.R;
import com.example.duanmau1.dao.LoaiMoHinhDao;
import com.example.duanmau1.dao.MoHinhDao;
import com.example.duanmau1.model.LoaiMoHinh;

import java.util.List;

public class LoaiAdminAdapter extends RecyclerView.Adapter<LoaiAdminAdapter.LoaiViewHolder> {
    private List<LoaiMoHinh> loaiMoHinhList;
    private MoHinhDao moHinhDao;
    private Context context;
    private LoaiMoHinhDao loaiMoHinhDao;

    public LoaiAdminAdapter(List<LoaiMoHinh> loaiMoHinhList, Context context) {
        this.loaiMoHinhList = loaiMoHinhList;
        this.moHinhDao = new MoHinhDao(context);
        this.context = context;
        this.loaiMoHinhDao = new LoaiMoHinhDao(context);
    }

    public void setLoaiMoHinhList(List<LoaiMoHinh> loaiMoHinhList) {
        this.loaiMoHinhList = loaiMoHinhList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_admin, parent, false);
        return new LoaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiViewHolder holder, int position) {
        LoaiMoHinh loaiMoHinh = loaiMoHinhList.get(position);
        Glide.with(context).load(Uri.parse(loaiMoHinh.getImgUri())).into(holder.imgLoai);
        holder.tvName.setText(loaiMoHinh.getTenLoai());
        moHinhDao.countMoHinhByLoai(loaiMoHinh.getMaLmh(), new MoHinhDao.OnCountMoHinh() {
            @Override
            public void onCountComplete(int count) {
                holder.tvSoLuong.setText("SL: "+ count);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("XÓA!");
                builder.setMessage("Bạn có chắc chắn muốn xóa " + loaiMoHinh.getTenLoai() + "?");
                builder.setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                           @Override
                           public void onDismiss(DialogInterface dialogInterface) {
                               dialogInterface.dismiss();
                           }
                       });
                    }
                });

                builder.setPositiveButton("XÓA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loaiMoHinhDao.deleteLoaiMoHinh(loaiMoHinh.getMaLmh());
                        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                dialogInterface.dismiss();
                            }
                        });
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return loaiMoHinhList.size();
    }

    public class LoaiViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgLoai;
        private TextView tvName, tvSoLuong;
        private ImageButton btnDelete;
        public LoaiViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLoai = itemView.findViewById(R.id.img_itemLoai);
            tvName = itemView.findViewById(R.id.tv_nameItemLoai);
            tvSoLuong = itemView.findViewById(R.id.tv_slItemLoai);
            btnDelete = itemView.findViewById(R.id.btn_deleteItemLoai);
        }
    }
}
