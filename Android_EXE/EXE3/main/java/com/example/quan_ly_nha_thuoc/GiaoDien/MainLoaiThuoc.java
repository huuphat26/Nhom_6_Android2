package com.example.quan_ly_nha_thuoc.GiaoDien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.quan_ly_nha_thuoc.Adpater.CustomAdapter_LT;
import com.example.quan_ly_nha_thuoc.Adpater.CustomAdapter_NT;
import com.example.quan_ly_nha_thuoc.DataBase.DBLoaiThuoc;
import com.example.quan_ly_nha_thuoc.DataBase.DBNhaThuoc;
import com.example.quan_ly_nha_thuoc.Model.LoaiThuoc;
import com.example.quan_ly_nha_thuoc.Model.NhaThuoc;
import com.example.quan_ly_nha_thuoc.R;

import java.util.ArrayList;

public class MainLoaiThuoc extends AppCompatActivity {
    Button btnThemThuoc, btnSuaThuoc, btnXoaThuoc;
    EditText txtMaThuoc, txtTenThuoc, txtDonVi, txtDonGia;
    ListView lvDanhSachThuoc;
    ArrayList<LoaiThuoc> data_loaithuoc = new ArrayList<>();
    CustomAdapter_LT adapter_LT;
    int index = -1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaithuoc);
        setControl();
        setEvent();
    }

    private void setEvent() {
        ShowData();
        adapter_LT = new CustomAdapter_LT(this, R.layout.item_loaithuoc, data_loaithuoc);
        lvDanhSachThuoc.setAdapter(adapter_LT);
        btnThemThuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtTenThuoc.length() > 0 && txtDonGia.length() > 0 && txtDonVi.length() > 0) {
                    LoaiThuoc loaiThuoc = getLoaithuoc();
                    getInfoLoaiThuoc(loaiThuoc);
                    data_loaithuoc.add(loaiThuoc);
                    adapter_LT.notifyDataSetChanged();
                    DBLoaiThuoc dbLoaiThuoc=new DBLoaiThuoc(getApplicationContext());
                    dbLoaiThuoc.ThemLT(loaiThuoc);
                    setStatus();
                    Toast.makeText(MainLoaiThuoc.this, "Thêm thành công!", Toast.LENGTH_LONG).show();
                } else {
                    if (txtTenThuoc.length() == 0 && txtDonVi.length() == 0 && txtDonGia.length() == 0) {
                        txtTenThuoc.setError("Vui Long Điền Trường Này");
                        txtDonVi.setError("Vui Long Điền Trường Này");
                        txtDonGia.setError("Vui Long Điền Trường Này");
                    }
                }
            }
        });
        btnSuaThuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    LoaiThuoc loaiThuoc = data_loaithuoc.get(index);
                    DBLoaiThuoc dbLoaiThuoc=new DBLoaiThuoc(getApplicationContext());
                    getInfoLoaiThuoc(loaiThuoc);
                    loaiThuoc =getLoaithuoc();
                    dbLoaiThuoc.suaLT(loaiThuoc);
                    adapter_LT.notifyDataSetChanged();
                    Toast.makeText(MainLoaiThuoc.this, "Cập nhập nhập thành công!", Toast.LENGTH_LONG).show();
                    setStatus();
                } else {
                    Toast.makeText(MainLoaiThuoc.this, "Bạn chưa chọn loai thuoc nao !", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnXoaThuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    LoaiThuoc loaiThuoc = data_loaithuoc.remove(index);
                    DBLoaiThuoc dbLoaiThuoc=new DBLoaiThuoc(getApplicationContext());
                    getInfoLoaiThuoc(loaiThuoc);
                    loaiThuoc =getLoaithuoc();
                    dbLoaiThuoc.xoaLT(loaiThuoc);
                    adapter_LT.notifyDataSetChanged();
                    setStatus();
                    Toast.makeText(MainLoaiThuoc.this, "Xóa Thành công", Toast.LENGTH_LONG).show();
                    index = -1;
                } else {
                    Toast.makeText(MainLoaiThuoc.this, "Bạn Chưa Chọn !!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        lvDanhSachThuoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LoaiThuoc loaiThuoc = data_loaithuoc.get(position);
                txtMaThuoc.setText(loaiThuoc.getMaThuoc());
                txtTenThuoc.setText(loaiThuoc.getTenThuoc());
                txtDonVi.setText(loaiThuoc.getDonVi());
                txtDonGia.setText(loaiThuoc.getDonGia());
                index = position;
            }
        });

    }
    private LoaiThuoc getLoaithuoc()
    {
        LoaiThuoc loaiThuoc=new LoaiThuoc();
        loaiThuoc.setMaThuoc(txtMaThuoc.getText().toString());
        loaiThuoc.setTenThuoc(txtTenThuoc.getText().toString());
        loaiThuoc.setDonVi(txtDonVi.getText().toString());
        loaiThuoc.setDonGia(txtDonGia.getText().toString());
        return loaiThuoc;
    }
    private void ShowData()
    {
        DBLoaiThuoc dbLoaiThuoc=new DBLoaiThuoc(this);
        data_loaithuoc= dbLoaiThuoc.getData();
        adapter_LT=new CustomAdapter_LT(this ,R.layout.item_loaithuoc,data_loaithuoc);
        lvDanhSachThuoc.setAdapter(adapter_LT);
    }
    private void getInfoLoaiThuoc(LoaiThuoc loaiThuoc) {
        loaiThuoc.setMaThuoc(txtMaThuoc.getText().toString());
        loaiThuoc.setTenThuoc(txtTenThuoc.getText().toString());
        loaiThuoc.setDonVi(txtDonVi.getText().toString());
        loaiThuoc.setDonGia(txtDonGia.getText().toString());
    }
    private void setStatus() {
        txtMaThuoc.setText("");
        txtTenThuoc.setText("");
        txtDonVi.setText("");
        txtDonGia.setText("");
    }

    private void setControl() {
        txtMaThuoc = findViewById(R.id.txtMaThuoc);
        txtTenThuoc = findViewById(R.id.txtTenThuoc);
        txtDonVi = findViewById(R.id.txtDonVi);
        txtDonGia = findViewById(R.id.txtDonGia);
        btnThemThuoc = findViewById(R.id.btnThemThuoc);
        btnSuaThuoc = findViewById(R.id.btnSuaThuoc);
        btnXoaThuoc = findViewById(R.id.btnXoaThuoc);
        lvDanhSachThuoc = findViewById(R.id.lvDanhSachLoaiThuoc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionLuu:
                DBLoaiThuoc dbLoaiThuoc = new DBLoaiThuoc(getApplicationContext());
                dbLoaiThuoc.saveLT(data_loaithuoc);
                Toast.makeText(MainLoaiThuoc.this, "Lưu Thành công", Toast.LENGTH_LONG).show();
                break;
            case R.id.mnChuyen:
                Toast.makeText(getApplicationContext(), "Danh Sách Nha thuoc", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), DanhSach_LoaiThuoc.class);
                startActivity(intent);
                break;
            case R.id.actionThoat:
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainLoaiThuoc.this);
                builder.setTitle("Thông báo!");
                builder.setMessage("Bạn có thật sự muốn thoát?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
