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

import com.example.quan_ly_nha_thuoc.GiaoDien.chitietHD;
import com.example.quan_ly_nha_thuoc.GiaoDien.chitietLT;
import com.example.quan_ly_nha_thuoc.Model.HoaDon;
import com.example.quan_ly_nha_thuoc.Model.LoaiThuoc;
import com.example.quan_ly_nha_thuoc.R;

import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapter_HD extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<HoaDon> data;
    ArrayList<HoaDon> data_DanhSachHD;
    static class Holder {
        ImageView imgHinh;
        ImageView imgDetail;
        TextView tvSoHD;
        TextView tvMathuoc;
        TextView tvNgayin;
    }
    public CustomAdapter_HD(@NonNull Context context, int resource, ArrayList<HoaDon> data) {
        super(context, resource);
        this.context = context;
        this.data = data;
        this.resource = resource;
        this.data= data;
        this.data_DanhSachHD = new ArrayList<HoaDon>();
        this.data_DanhSachHD.addAll(data);
    }
    @Override
    public int getCount() {
        return data.size();
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
       Holder holder=null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.imgHinh = view.findViewById(R.id.imgshow);
            holder.imgDetail = view.findViewById(R.id.imgDetailHD);
            holder.tvSoHD = view.findViewById(R.id.tvSoHoaDon);
            holder.tvMathuoc = view.findViewById(R.id.tvMaThuoc);
            holder.tvNgayin = view.findViewById(R.id.tvNgayIn);
            view.setTag(holder);
        } else
            holder=(Holder)view.getTag();

        final HoaDon hoaDon = data.get(position);

        holder.tvSoHD.setText(hoaDon.getSoHoaDon());
        holder.tvMathuoc.setText(hoaDon.getMaNhaThuoc());
        holder.tvNgayin.setText(hoaDon.getNgayIn());
        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Activity) context, chitietHD.class);
                Bundle bundle = new Bundle();
                bundle.putString("sohoadon", hoaDon.getSoHoaDon());
                intent.putExtras(bundle);
                ((Activity) context).startActivity(intent);
            }
        });
//        View view = LayoutInflater.from(context).inflate(resource, null);
//        TextView txtSoHD = view.findViewById(R.id.tvSoHoaDon);
//        TextView txtNgayin = view.findViewById(R.id.tvNgayIn);
//        TextView txtMant = view.findViewById(R.id.tvMaThuoc);
//        HoaDon hoaDon = data.get(position);
//        txtSoHD.setText(hoaDon.getSoHoaDon());
//        txtNgayin.setText(hoaDon.getNgayIn());
//        txtMant.setText(hoaDon.getMaNhaThuoc());
        return view;
    }
    public void filter (String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if(charText.length() == 0)
        {
            data.addAll(data_DanhSachHD);
        }
        else {
            for (HoaDon model : data_DanhSachHD)
            {
                if (model.getSoHoaDon().toLowerCase(Locale.getDefault())
                        .contains(charText))
                {
                    data.add(model);}

            }
        }
        notifyDataSetChanged();
    }
}
