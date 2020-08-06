package tdc.edu.vn.bai5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlynhathuoc.R;

import java.util.ArrayList;
import java.util.Locale;

import tdc.edu.vn.bai5.Model.NhaThuoc;

public class CustomerAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<NhaThuoc> data;
    ArrayList<NhaThuoc> data_DanhSachNT;

    public CustomerAdapter(Context context, int resource, ArrayList<NhaThuoc> data) {
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
        View view = LayoutInflater.from(context).inflate(resource, null);
        ImageView img = view.findViewById(R.id.imgshow);
        TextView txtmaNT = view.findViewById(R.id.textViewmaNT);
        TextView txttenNT = view.findViewById(R.id.textViewtenNT);
        TextView txtdiadiemNT = view.findViewById(R.id.textViewdiadiemNT);
        NhaThuoc nhaThuoc = data.get(position);
        txtmaNT.setText(nhaThuoc.getMaNT());
        txttenNT.setText(nhaThuoc.getTenNT());
        txtdiadiemNT.setText(nhaThuoc.getDiadiemNT());


        if (nhaThuoc.getMaNT().equals("1")){
            img.setImageResource(R.drawable.ic_baseline_exposure_plus_1_24);
        }
        if (nhaThuoc.getMaNT().equals("2")){
            img.setImageResource(R.drawable.ic_baseline_exposure_plus_2_24);
        }
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
