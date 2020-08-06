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

import com.example.quan_ly_nha_thuoc.GiaoDien.chitietLT;
import com.example.quan_ly_nha_thuoc.Model.LoaiThuoc;
import com.example.quan_ly_nha_thuoc.R;
import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapter_LT extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<LoaiThuoc> data;
    ArrayList<LoaiThuoc> data_DanhSachLT;
    static class Holder {
        ImageView imgHinh;
        ImageView imgDetail;
        TextView tvDonvi;
        TextView tvDongia;
        TextView tvTenthuoc;
        TextView tvMathuoc;
    }
    public CustomAdapter_LT(@NonNull Context context, int resource, ArrayList<LoaiThuoc> data) {
        super(context, resource);
        this.context = context;
        this.data = data;
        this.resource = resource;
        this.data= data;
        this.data_DanhSachLT = new ArrayList<LoaiThuoc>();
        this.data_DanhSachLT.addAll(data);
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
            holder.imgHinh = view.findViewById(R.id.imgshowLT);
            holder.imgDetail = view.findViewById(R.id.imgDetailLT);
            holder.tvMathuoc = view.findViewById(R.id.textViewmaThuoc);
            holder.tvTenthuoc = view.findViewById(R.id.textViewtenThuoc);
            holder.tvDonvi = view.findViewById(R.id.textViewDonVi);
            holder.tvDongia = view.findViewById(R.id.textViewDonGia);
            view.setTag(holder);
        } else
            holder=(Holder)view.getTag();

        final LoaiThuoc loaiThuoc = data.get(position);

        holder.tvMathuoc.setText(loaiThuoc.getMaThuoc());
        holder.tvTenthuoc.setText(loaiThuoc.getTenThuoc());
        holder.tvDonvi.setText(loaiThuoc.getDonVi());
        holder.tvDongia.setText(loaiThuoc.getDonGia());
        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent((Activity) context, chitietLT.class);
                Bundle bundle = new Bundle();
                bundle.putString("mathuoc", loaiThuoc.getMaThuoc());
                intent.putExtras(bundle);
                ((Activity) context).startActivity(intent);
            }
        });
//
//        View view = LayoutInflater.from(context).inflate(resource, null);
//        TextView txtMathuoc = view.findViewById(R.id.textViewmaThuoc);
//        TextView txtTenthuoc = view.findViewById(R.id.textViewtenThuoc);
//        TextView txtDonvi = view.findViewById(R.id.textViewDonVi);
//        TextView txtDongia = view.findViewById(R.id.textViewDonGia);
//        LoaiThuoc loaiThuoc = data.get(position);
//        txtMathuoc.setText(loaiThuoc.getMaThuoc());
//        txtTenthuoc.setText(loaiThuoc.getTenThuoc());
//        txtDonvi.setText(loaiThuoc.getDonVi());
//        txtDongia.setText(loaiThuoc.getDonGia());
        return view;
    }
    public void filter (String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if(charText.length() == 0)
        {
            data.addAll(data_DanhSachLT);
        }
        else {
            for (LoaiThuoc model : data_DanhSachLT)
            {
                if (model.getMaThuoc().toLowerCase(Locale.getDefault())
                        .contains(charText))
                {
                    data.add(model);}

            }
        }
        notifyDataSetChanged();
    }
}
