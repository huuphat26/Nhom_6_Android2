package com.example.quan_ly_nha_thuoc.Adpater;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.quan_ly_nha_thuoc.GiaoDien.chitietCTHD;
import com.example.quan_ly_nha_thuoc.Model.ChiTiet;
import com.example.quan_ly_nha_thuoc.R;

import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapter_CT extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ChiTiet> data_ct;
    ArrayList<ChiTiet> data_DanhSachct;
    static class Holder {
        ImageView imgHinh;
        ImageView imgDetail;
        TextView tvMaThuoc;
        TextView tvSoHoaDon;
        TextView tvSoLuong;
    }
    public CustomAdapter_CT(@NonNull Context context, int resource, ArrayList<ChiTiet> data_DanhSachct) {
        super(context, resource);
        this.context = context;
        this.data_ct = data_DanhSachct;
        this.resource = resource;
        this.data_ct = data_DanhSachct;
        this.data_DanhSachct = new ArrayList<ChiTiet>();
        this.data_DanhSachct.addAll(data_ct);
    }

    @Override
    public int getCount() {
        return data_ct.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder=null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.imgHinh = view.findViewById(R.id.imgshow);
            holder.imgDetail = view.findViewById(R.id.imgDetailCT);
            holder.tvMaThuoc = view.findViewById(R.id.tvmathuoc);
            holder.tvSoHoaDon = view.findViewById(R.id.tvSoHoaDon);
            holder.tvSoLuong = view.findViewById(R.id.tvSoLuong);
            view.setTag(holder);
        } else
            holder=(Holder)view.getTag();

        final ChiTiet chiTiet = data_ct.get(position);

        holder.tvMaThuoc.setText(chiTiet.getMathuoc());
        holder.tvSoHoaDon.setText(chiTiet.getSohoadon());
        holder.tvSoLuong.setText(chiTiet.getSoluong());
        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Activity) context, chitietCTHD.class);
                Bundle bundle = new Bundle();
                bundle.putString("mathuoc", chiTiet.getMathuoc());
                intent.putExtras(bundle);
                ((Activity) context).startActivity(intent);
            }
        });
        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        data_ct.clear();
        if (charText.length() == 0) {
            data_ct.addAll(data_DanhSachct);
        } else {
            for (ChiTiet model : data_DanhSachct) {
                if (model.getMathuoc().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    data_ct.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
