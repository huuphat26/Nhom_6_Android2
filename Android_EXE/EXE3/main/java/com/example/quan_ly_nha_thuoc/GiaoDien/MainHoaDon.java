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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.quan_ly_nha_thuoc.Adpater.CustomAdapter_HD;
import com.example.quan_ly_nha_thuoc.Adpater.CustomAdapter_LT;
import com.example.quan_ly_nha_thuoc.Adpater.CustomAdapter_NT;
import com.example.quan_ly_nha_thuoc.DataBase.DBHoaDon;
import com.example.quan_ly_nha_thuoc.DataBase.DBLoaiThuoc;
import com.example.quan_ly_nha_thuoc.DataBase.DBNhaThuoc;
import com.example.quan_ly_nha_thuoc.Model.HoaDon;
import com.example.quan_ly_nha_thuoc.Model.LoaiThuoc;
import com.example.quan_ly_nha_thuoc.Model.NhaThuoc;
import com.example.quan_ly_nha_thuoc.R;

import java.util.ArrayList;

public class MainHoaDon extends AppCompatActivity {
    EditText txtSoHD, txtNgayInHD;
    Spinner spMaNhaThuoc;
    Button bntThemHD, bntXoaHD, bntSuaHD;
    GridView gvDanhSachHD;
    ArrayList<HoaDon> data_HoaDon = new ArrayList<>();
    CustomAdapter_HD Adapter_HoaDon;

    ArrayList<String> data_MaNhaThuoc = new ArrayList<>();
    ArrayAdapter adapter_MaNhaThuoc;


    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon);
        setControl();
        setEvent();
    }

    private void setEvent() {
        ShowData();
        LoadSpinner();
        Adapter_HoaDon = new CustomAdapter_HD(this, R.layout.item_hoadon, data_HoaDon);
        gvDanhSachHD.setAdapter(Adapter_HoaDon);
        bntThemHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtSoHD.length() > 0 && txtNgayInHD.length() > 0) {
                    HoaDon hoaDon = getHoadon();
                    getInfoHD(hoaDon);
                    data_HoaDon.add(hoaDon);
                    Adapter_HoaDon.notifyDataSetChanged();
                    DBHoaDon dbHoaDon=new DBHoaDon(getApplicationContext());
                    dbHoaDon.ThemHD(hoaDon);
                    setStatus();
                    Toast.makeText(MainHoaDon.this, "Thêm thành công!", Toast.LENGTH_LONG).show();
                } else {
                    if (txtSoHD.length() == 0 && txtNgayInHD.length() == 0) {
                        txtSoHD.setError("Vui lòng điền trường này");
                    }
                    if (txtNgayInHD.length() == 0) {
                        txtNgayInHD.setError("Vui lòng điền trường này");
                    }
                }
            }
        });
        bntSuaHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (index != -1) {
                    HoaDon hoaDon = data_HoaDon.get(index);
                    DBHoaDon dbHoaDon=new DBHoaDon(getApplicationContext());
                    getInfoHD(hoaDon);
                    hoaDon =getHoadon();
                    dbHoaDon.suaHoaDon(hoaDon);
                    Adapter_HoaDon.notifyDataSetChanged();
                    Toast.makeText(MainHoaDon.this, "Cập nhập nhập thành công!", Toast.LENGTH_LONG).show();
                    setStatus();
                } else {
                    Toast.makeText(MainHoaDon.this, "Bạn chưa chọn nha thuoc nao!", Toast.LENGTH_LONG).show();
                }
            }
        });
        bntXoaHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    HoaDon hoaDon = data_HoaDon.remove(index);
                    DBHoaDon dbHoaDon=new DBHoaDon(getApplicationContext());
                    getInfoHD(hoaDon);
                    hoaDon =getHoadon();
                    dbHoaDon.xoaHoaDon(hoaDon);
                    Adapter_HoaDon.notifyDataSetChanged();
                    setStatus();
                    Toast.makeText(MainHoaDon.this, "Xóa Thành công", Toast.LENGTH_LONG).show();
                    index = -1;
                } else {
                    Toast.makeText(MainHoaDon.this, "Bạn chưa chọn Hoa don nao!", Toast.LENGTH_LONG).show();
                }
            }
        });
        gvDanhSachHD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HoaDon hoaDon = data_HoaDon.get(position);
                txtSoHD.setText(hoaDon.getSoHoaDon());
                txtNgayInHD.setText(hoaDon.getNgayIn());
                spMaNhaThuoc.setSelection(index);
                index = position;
            }
        });
        gvDanhSachHD.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainHoaDon.this);
                builder.setTitle("Thông báo!");
                builder.setMessage("Bạn có muốn xóa khong ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_HoaDon.remove(index);
                        Adapter_HoaDon.notifyDataSetChanged();
                        setStatus();
                        Toast.makeText(MainHoaDon.this, "Xóa Thành công", Toast.LENGTH_LONG).show();
                        index = -1;
                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                return false;
            }
        });

    }
    private HoaDon getHoadon()
    {
        HoaDon hoaDon=new HoaDon();
        hoaDon.setSoHoaDon(txtSoHD.getText().toString());
        hoaDon.setNgayIn(txtNgayInHD.getText().toString());
        hoaDon.setMaNhaThuoc(spMaNhaThuoc.getSelectedItem().toString());
        return hoaDon;
    }
    private void ShowData()
    {
        DBHoaDon dbHoaDon=new DBHoaDon(this);
        data_HoaDon=dbHoaDon.getData();
        Adapter_HoaDon=new CustomAdapter_HD(this ,R.layout.item_hoadon,data_HoaDon);
        gvDanhSachHD.setAdapter(Adapter_HoaDon);
    }
    private void LoadSpinner() {
       DBNhaThuoc dbNhaThuoc = new DBNhaThuoc(this);
       data_MaNhaThuoc = dbNhaThuoc.LayMaNT();
       adapter_MaNhaThuoc = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,data_MaNhaThuoc);
       spMaNhaThuoc.setAdapter(adapter_MaNhaThuoc);

    }
    private void setStatus() {
        txtSoHD.setText("");
        txtNgayInHD.setText("");
        spMaNhaThuoc.setSelection(0);
    }

    private void getInfoHD(HoaDon hoaDon) {
        hoaDon.setSoHoaDon(txtSoHD.getText().toString());
        hoaDon.setNgayIn(txtNgayInHD.getText().toString());
        hoaDon.setMaNhaThuoc(spMaNhaThuoc.getSelectedItem().toString());
    }

    private void setControl() {
        txtSoHD = findViewById(R.id.txtsohoadon);
        spMaNhaThuoc = findViewById(R.id.spMaNhaThuoc);
        txtNgayInHD = findViewById(R.id.txtNgayInHD);
        bntThemHD = findViewById(R.id.bntThemHD);
        bntXoaHD = findViewById(R.id.bntXoaHD);
        bntSuaHD = findViewById(R.id.btnSuaHD);
        gvDanhSachHD = findViewById(R.id.gvDanhSach);
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
                DBHoaDon dbHoaDon = new DBHoaDon(getApplicationContext());
                dbHoaDon.saveHD(data_HoaDon);
                Toast.makeText(MainHoaDon.this, "Lưu Thành công", Toast.LENGTH_LONG).show();
                break;
            case R.id.mnChuyen:
                Toast.makeText(getApplicationContext(), "Danh Sách Hóa Đơn", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), DanhSach_HoaDon.class);
                startActivity(intent);
                break;
            case R.id.actionThoat:
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainHoaDon.this);
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
