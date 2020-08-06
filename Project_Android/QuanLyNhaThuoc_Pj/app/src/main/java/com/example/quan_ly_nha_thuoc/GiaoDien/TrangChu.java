package com.example.quan_ly_nha_thuoc.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quan_ly_nha_thuoc.R;

public class TrangChu extends AppCompatActivity {
    Button btnNhaThuoc, btnLoaiThuoc, btnHoaDon, btnChiTiet, btnDangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giaodien);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Bạn Chọn Hóa Đơn", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(), MainHoaDon.class);
                startActivity(intent1);
            }
        });
        btnLoaiThuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Bạn Chọn Loại Thuốc", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(getApplicationContext(), MainLoaiThuoc.class);
                startActivity(intent2);
            }
        });
        btnNhaThuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Bạn Chọn Nhà Thuốc", Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(getApplicationContext(), MainNhaThuoc.class);
                startActivity(intent3);
            }
        });
        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Bạn Chọn Chi Tiết Bán Lẻ", Toast.LENGTH_LONG).show();
                Intent intent4 = new Intent(getApplicationContext(), MainChitiet.class);
                startActivity(intent4);
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
                Intent intent5 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent5);
            }
        });

    }

    private void setControl() {
        btnNhaThuoc = findViewById(R.id.btnQLNhaThuoc);
        btnLoaiThuoc = findViewById(R.id.btnQLLoaiThuoc);
        btnHoaDon = findViewById(R.id.btnHoaDon);
        btnChiTiet = findViewById(R.id.btnChiTiet);
        btnDangXuat = findViewById(R.id.btnDangXuat);
    }
}
