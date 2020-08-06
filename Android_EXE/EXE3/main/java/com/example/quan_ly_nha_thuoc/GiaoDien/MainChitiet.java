package com.example.quan_ly_nha_thuoc.GiaoDien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quan_ly_nha_thuoc.Adpater.CustomAdapter_CT;
import com.example.quan_ly_nha_thuoc.DataBase.DBChiTiet;
import com.example.quan_ly_nha_thuoc.DataBase.DBHoaDon;
import com.example.quan_ly_nha_thuoc.DataBase.DBLoaiThuoc;
import com.example.quan_ly_nha_thuoc.Model.ChiTiet;
import com.example.quan_ly_nha_thuoc.Model.HoaDon;
import com.example.quan_ly_nha_thuoc.R;

import java.util.ArrayList;

public class MainChitiet extends AppCompatActivity {
    EditText txtSoLuong;
    Spinner spMaThuoc, spSoHoaDon;
    Button btnThem, btnSua, btnXoa;
    ListView lvDanhSachCT;
    ImageView imgHinh;
    ArrayList<ChiTiet> data_ChiTiet = new ArrayList<>();
    ArrayList<String> data_SoHoaDon = new ArrayList<>();
    ArrayList<String> data_Mathuoc = new ArrayList<>();
    CustomAdapter_CT Adapter_ChiTiet;
    ArrayAdapter adapter_SoHoaDon;
    ArrayAdapter adapter_Mathuoc;

    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet);
        setControl();
        setEvent();
    }

    private void setEvent() {
        ShowData();
        LoadSpinnerSoHD();
        Adapter_ChiTiet = new CustomAdapter_CT(this, R.layout.item_chitiet, data_ChiTiet);
        lvDanhSachCT.setAdapter(Adapter_ChiTiet);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtSoLuong.length() > 0) {
                    ChiTiet chiTiet = getChitiet();
                    getInfoCT(chiTiet);
                    data_ChiTiet.add(chiTiet);
                    Adapter_ChiTiet.notifyDataSetChanged();
                    DBChiTiet dbChiTiet=new DBChiTiet(getApplicationContext());
                    dbChiTiet.ThemCT(chiTiet);
                    setStatus();
                    Toast.makeText(MainChitiet.this, "Thêm thành công!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainChitiet.this, "Chưa Thêm!", Toast.LENGTH_LONG).show();
                }
            }
        });
        lvDanhSachCT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChiTiet chiTiet = data_ChiTiet.get(position);
                txtSoLuong.setText(chiTiet.getSoluong());
                spMaThuoc.setSelection(index);
                spSoHoaDon.setSelection(index);
                index = position;
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    ChiTiet chiTiet = data_ChiTiet.get(index);
                    DBChiTiet dbChiTiet=new DBChiTiet(getApplicationContext());
                    getInfoCT(chiTiet);
                    chiTiet =getChitiet();
                    dbChiTiet.suaChitiet(chiTiet);
                    Adapter_ChiTiet.notifyDataSetChanged();
                    Toast.makeText(MainChitiet.this, "Cập nhập nhập thành công!", Toast.LENGTH_LONG).show();
                    setStatus();
                } else {
                    Toast.makeText(MainChitiet.this, "Bạn chưa chọn nha thuoc nao!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    ChiTiet chiTiet = data_ChiTiet.remove(index);
                    DBChiTiet dbChiTiet=new DBChiTiet(getApplicationContext());
                    getInfoCT(chiTiet);
                    chiTiet =getChitiet();
                    dbChiTiet.xoaChitiet(chiTiet);
                    Adapter_ChiTiet.notifyDataSetChanged();
                    setStatus();
                    Toast.makeText(MainChitiet.this, "Xóa Thành công", Toast.LENGTH_LONG).show();
                    index = -1;
                } else {
                    Toast.makeText(MainChitiet.this, "Bạn chưa chọn !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setControl() {
        txtSoLuong = findViewById(R.id.txtSoLuong);
        spMaThuoc = findViewById(R.id.spMathuocct);
        spSoHoaDon = findViewById(R.id.spSohoadonct);
        btnThem = findViewById(R.id.btnThemct);
        btnSua = findViewById(R.id.btnSuact);
        btnXoa = findViewById(R.id.btnXoact);
        lvDanhSachCT = findViewById(R.id.lvChitiet);
    }
    private void LoadSpinnerSoHD() {
        DBHoaDon dbHoaDon = new DBHoaDon(this);
        data_SoHoaDon = dbHoaDon.LaySoHD();
        adapter_SoHoaDon = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,data_SoHoaDon);
        spSoHoaDon.setAdapter(adapter_SoHoaDon);
        DBLoaiThuoc dbLoaiThuoc = new DBLoaiThuoc(this);
        data_Mathuoc = dbLoaiThuoc.LayMathuoc();
        adapter_Mathuoc = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,data_Mathuoc);
        spMaThuoc.setAdapter(adapter_Mathuoc);

    }
    private ChiTiet getChitiet()
    {
        ChiTiet chiTiet=new ChiTiet();
        chiTiet.setMathuoc(spMaThuoc.getSelectedItem().toString());
        chiTiet.setSohoadon(spSoHoaDon.getSelectedItem().toString());
        chiTiet.setSoluong(txtSoLuong.getText().toString());
        return chiTiet;
    }
    private void ShowData()
    {
        DBChiTiet dbChiTiet=new DBChiTiet(this);
        data_ChiTiet= dbChiTiet.getData();
        Adapter_ChiTiet=new CustomAdapter_CT(this ,R.layout.item_chitiet,data_ChiTiet);
        lvDanhSachCT.setAdapter(Adapter_ChiTiet);
    }
    private void setStatus() {
        txtSoLuong.setText("");
        spMaThuoc.setSelection(0);
        spSoHoaDon.setSelection(0);
    }
    private void getInfoCT(ChiTiet chiTiet) {
        chiTiet.setSoluong(txtSoLuong.getText().toString());
        chiTiet.setMathuoc(spMaThuoc.getSelectedItem().toString());
        chiTiet.setSohoadon(spSoHoaDon.getSelectedItem().toString());
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
                DBChiTiet dbChiTiet = new DBChiTiet(getApplicationContext());
                dbChiTiet.SaveCT(data_ChiTiet);
                Toast.makeText(MainChitiet.this, "Lưu Thành công", Toast.LENGTH_LONG).show();
                break;
            case R.id.mnChuyen:
                Toast.makeText(getApplicationContext(), "Danh Sách Chi Tiet", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),DanhSach_ChiTiet.class);
                startActivity(intent);
                break;
            case R.id.actionThoat:
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainChitiet.this);
                builder.setTitle("Thông báo!");
                builder.setMessage("Bạn có muốn thoát không ?");
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



