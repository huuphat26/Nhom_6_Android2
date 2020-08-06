package com.example.quan_ly_nha_thuoc.GiaoDien;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quan_ly_nha_thuoc.DataBase.DBHoaDon;
import com.example.quan_ly_nha_thuoc.Model.HoaDon;
import com.example.quan_ly_nha_thuoc.R;

import java.util.ArrayList;

public class chitietHD extends AppCompatActivity {

    EditText txtSohoadon, txtMaNT, txtNgayInHD;
    ArrayList<HoaDon> data_hoadon = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_h_d);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        ((ActionBar) actionBar).setDisplayHomeAsUpEnabled(true);

    }

    private void setEvent() {
        String ma= getIntent().getExtras().getString("sohoadon");
        DBHoaDon dbHoaDon = new DBHoaDon(this);
        data_hoadon = dbHoaDon.layDL(ma);
        txtSohoadon.setText("Số Hóa Đơn :"+data_hoadon.get(0).getSoHoaDon());
        txtMaNT.setText("Mã Nhà Thuốc :"+data_hoadon.get(0).getMaNhaThuoc());
        txtNgayInHD.setText("Ngày in: "+data_hoadon.get(0).getNgayIn());
    }

    private void setConTrol() {
        txtSohoadon = findViewById(R.id.txtsohoadon);
        txtMaNT = findViewById(R.id.txtMaNT);
        txtNgayInHD = findViewById(R.id.txtNgayInHD);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}