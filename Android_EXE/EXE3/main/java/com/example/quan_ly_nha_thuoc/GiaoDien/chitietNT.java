package com.example.quan_ly_nha_thuoc.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.quan_ly_nha_thuoc.DataBase.DBNhaThuoc;
import com.example.quan_ly_nha_thuoc.Model.NhaThuoc;
import com.example.quan_ly_nha_thuoc.R;

import java.util.ArrayList;

public class chitietNT extends AppCompatActivity {
    EditText txtMaNT, txtTenNT, txtDiaChiNT;
    ArrayList<NhaThuoc> data_nhathuoc = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_n_t);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        ((ActionBar) actionBar).setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        String ma= getIntent().getExtras().getString("maNT");
        DBNhaThuoc dBnhathuoc = new DBNhaThuoc(this);
        data_nhathuoc = dBnhathuoc.layDL(ma);
        txtMaNT.setText("Mã Nhà Thuốc : "+data_nhathuoc.get(0).getMaNT());
        txtTenNT.setText("Tên Nhà Thuốc :"+data_nhathuoc.get(0).getTenNT());
        txtDiaChiNT.setText("Địa Chỉ : "+data_nhathuoc.get(0).getDiadiemNT());
    }

    private void setConTrol() {
        txtMaNT = findViewById(R.id.txtMaNT);
        txtTenNT = findViewById(R.id.txtTenNT);
        txtDiaChiNT = findViewById(R.id.txtDiaChiNT);


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