package com.example.quan_ly_nha_thuoc.GiaoDien;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quan_ly_nha_thuoc.DataBase.DBLoaiThuoc;
import com.example.quan_ly_nha_thuoc.Model.LoaiThuoc;
import com.example.quan_ly_nha_thuoc.R;

import java.util.ArrayList;

public class chitietLT extends AppCompatActivity {

    EditText txtMaThuoc, txtTenThuoc, txtDonVi,txtDonGia;
    ArrayList<LoaiThuoc> data_loaithuoc = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_l_t);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        ((ActionBar) actionBar).setDisplayHomeAsUpEnabled(true);

    }

    private void setEvent() {
        String ma= getIntent().getExtras().getString("mathuoc");
        DBLoaiThuoc dbLoaiThuoc = new DBLoaiThuoc(this);
        data_loaithuoc = dbLoaiThuoc.layDL(ma);
        txtMaThuoc.setText("Mã Thuốc  :"+data_loaithuoc.get(0).getMaThuoc());
        txtTenThuoc.setText("Tên Thuốc:"+data_loaithuoc.get(0).getTenThuoc());
        txtDonVi.setText("Đơn Vị : "+data_loaithuoc.get(0).getDonVi());
        txtDonGia.setText("Đơn Giá: "+data_loaithuoc.get(0).getDonGia());
    }

    private void setConTrol() {
        txtMaThuoc = findViewById(R.id.txtMaThuoc);
        txtTenThuoc = findViewById(R.id.txtTenThuoc);
        txtDonVi = findViewById(R.id.txtDonVi);
        txtDonGia = findViewById(R.id.txtDonGia);


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