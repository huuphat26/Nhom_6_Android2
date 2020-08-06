package tdc.edu.vn.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter_BaiHat extends BaseAdapter {
    Context mContext;
    int mLayout;
    List<Song> list_baihat;
    Adapter_BaiHat(Context context, int Layout, List<Song> list_baihats){
        mContext = context;
        mLayout = Layout;
        list_baihat = list_baihats;
    }
    @Override
    public int getCount() {
        return list_baihat.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        convertView = inflater.inflate(mLayout, null);
        //ánh xạ
        TextView txt_tenbaihat = (TextView) convertView.findViewById(R.id.tvTenBaiHat);
        txt_tenbaihat.setText(list_baihat.get(position).getTenbaihat());
        ImageView imgHinh = convertView.findViewById(R.id.imgHinh);
        imgHinh.setImageResource(list_baihat.get(position).getHinh());

        return convertView;
    }
}
