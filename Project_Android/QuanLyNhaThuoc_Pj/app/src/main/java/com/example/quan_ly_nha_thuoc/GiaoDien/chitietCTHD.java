package com.example.quan_ly_nha_thuoc.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.quan_ly_nha_thuoc.DataBase.DBChiTiet;
import com.example.quan_ly_nha_thuoc.DataBase.DBHoaDon;
import com.example.quan_ly_nha_thuoc.Model.ChiTiet;
import com.example.quan_ly_nha_thuoc.Model.HoaDon;
import com.example.quan_ly_nha_thuoc.R;

import java.util.ArrayList;

public class chitietCTHD extends AppCompatActivity {
    EditText txtMathuoc, txtsohoadon, txtSoLuong;
    ArrayList<ChiTiet> data_Chitiet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_cthd);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        ((ActionBar) actionBar).setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        String ma= getIntent().getExtras().getString("mathuoc");
        DBChiTiet dbChiTiet = new DBChiTiet(this);
        data_Chitiet = dbChiTiet.layDL(ma);
        txtMathuoc.setText("Mã Thuốc :"+data_Chitiet.get(0).getMathuoc());
        txtsohoadon.setText("Số Hóa Đơn :"+data_Chitiet.get(0).getSohoadon());
        txtSoLuong.setText("Số Lượng : "+data_Chitiet.get(0).getSoluong());
    }

    private void setConTrol() {
        txtMathuoc = findViewById(R.id.txtMathuoc);
        txtsohoadon = findViewById(R.id.txtsohoadon);
        txtSoLuong = findViewById(R.id.txtSoLuong);


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