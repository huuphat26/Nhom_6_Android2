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

import com.example.quan_ly_nha_thuoc.GiaoDien.chitietNT;
import com.example.quan_ly_nha_thuoc.Model.NhaThuoc;
import com.example.quan_ly_nha_thuoc.R;

import java.util.ArrayList;
import java.util.Locale;
public class CustomAdapter_NT extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<NhaThuoc> data;
    ArrayList<NhaThuoc> data_DanhSachNT;
    static class Holder {
        ImageView imgHinh;
        ImageView imgDetail;
        TextView tvHoTen;
        TextView tvMa;
        TextView tvDiaChi;
    }
    public CustomAdapter_NT(Context context, int resource, ArrayList<NhaThuoc> data) {
        super(context, resource);
        this.context = context;
        this.data = data;
        this.resource = resource;
        this.data= data;
        this.data_DanhSachNT = new ArrayList<NhaThuoc>();
        this.data_DanhSachNT.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.imgHinh = view.findViewById(R.id.imgshow);
            holder.imgDetail = view.findViewById(R.id.imgDetailNT);
            holder.tvMa = view.findViewById(R.id.tvMaNhathuoc);
            holder.tvHoTen = view.findViewById(R.id.tvTenNhathuoc);
            holder.tvDiaChi = view.findViewById(R.id.tvDiaChi);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();
        final NhaThuoc nhaThuoc = data.get(position);
        holder.tvMa.setText(nhaThuoc.getMaNT());
        holder.tvHoTen.setText(nhaThuoc.getTenNT());
        holder.tvDiaChi.setText(nhaThuoc.getDiadiemNT());
        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Activity) context, chitietNT.class);
                Bundle bundle = new Bundle();
                bundle.putString("maNT", nhaThuoc.getMaNT());
                intent.putExtras(bundle);
                ((Activity) context).startActivity(intent);
            }
        });
        return view;
    }
    public void filter (String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if(charText.length() == 0)
        {
            data.addAll(data_DanhSachNT);
        }
        else {
            for (NhaThuoc model : data_DanhSachNT)
            {
                if (model.getMaNT().toLowerCase(Locale.getDefault())
                        .contains(charText))
                {
                    data.add(model);}

            }
        }
        notifyDataSetChanged();
    }
}
