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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quan_ly_nha_thuoc.Adpater.CustomAdapter_NT;
import com.example.quan_ly_nha_thuoc.DataBase.DBNhaThuoc;
import com.example.quan_ly_nha_thuoc.Model.NhaThuoc;
import com.example.quan_ly_nha_thuoc.R;

import java.util.ArrayList;
import java.util.Locale;

public class MainNhaThuoc extends AppCompatActivity{
    Button btnThemNT,btnSuaNT,btnXoaNT;
    EditText txtMaNT,txtTenNT,txtDiaChiNT;
    ListView lsNhaThuoc;
    ArrayList<NhaThuoc> data_nhathuoc = new ArrayList<>();
    CustomAdapter_NT adapter_nhathuoc;
    ImageView imgHinh;
    int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhathuoc);
        setControl();
        setEvent();
    }

    private void setEvent() {
        ShowData();
        adapter_nhathuoc = new CustomAdapter_NT(this, R.layout.item_nhathuoc, data_nhathuoc);
        lsNhaThuoc.setAdapter(adapter_nhathuoc);
        btnThemNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtMaNT.length() > 0 && txtTenNT.length() > 0) {
                    NhaThuoc nhaThuoc = getNhathuoc();
                    getInfoNhaThuoc(nhaThuoc);
                    data_nhathuoc.add(nhaThuoc);
                    adapter_nhathuoc.notifyDataSetChanged();
                    DBNhaThuoc dbNhaThuoc=new DBNhaThuoc(getApplicationContext());
                    dbNhaThuoc.addNhathuoc(nhaThuoc);
                    setStatus();
                    Toast.makeText(MainNhaThuoc.this, "Thêm thành công!", Toast.LENGTH_LONG).show();
                }
                else {
                    if (txtMaNT.length() == 0) {
                        txtTenNT.setError("Vui lòng điền trường này");
                    }
                    if (txtTenNT.length() == 0) {
                        txtDiaChiNT.setError("Vui lòng điền trường này");
                    }

                }

            }
        });
        btnSuaNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    NhaThuoc nhaThuoc = data_nhathuoc.get(index);
                    DBNhaThuoc dbNhaThuoc=new DBNhaThuoc(getApplicationContext());
                    getInfoNhaThuoc(nhaThuoc);
                    nhaThuoc =getNhathuoc();
                    dbNhaThuoc.suaNhathuoc(nhaThuoc);
                    adapter_nhathuoc.notifyDataSetChanged();
                    Toast.makeText(MainNhaThuoc.this, "Cập nhập nhập thành công!", Toast.LENGTH_LONG).show();
                    setStatus();
                } else {
                    Toast.makeText(MainNhaThuoc.this, "Bạn chưa chọn nha thuoc nao!", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnXoaNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    NhaThuoc nhaThuoc = data_nhathuoc.remove(index);
                    DBNhaThuoc dbNhaThuoc=new DBNhaThuoc(getApplicationContext());
                    getInfoNhaThuoc(nhaThuoc);
                    nhaThuoc =getNhathuoc();
                    dbNhaThuoc.xoaNhaThuoc(nhaThuoc);
                    adapter_nhathuoc.notifyDataSetChanged();
                    setStatus();
                    Toast.makeText(MainNhaThuoc.this, "Xóa Thành công", Toast.LENGTH_LONG).show();
                    index = -1;
                } else {
                    Toast.makeText(MainNhaThuoc.this, "Bạn chưa chọn casi nao!", Toast.LENGTH_LONG).show();
                }
            }
        });
        lsNhaThuoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhaThuoc nhaThuoc = data_nhathuoc.get(position);
                txtMaNT.setText(nhaThuoc.getMaNT());
                txtTenNT.setText(nhaThuoc.getTenNT());
                txtDiaChiNT.setText(nhaThuoc.getDiadiemNT());
                index = position;
            }
        });

        lsNhaThuoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainNhaThuoc.this);
                builder.setTitle("Thông báo!");
                builder.setMessage("Bạn có muốn xóa khong ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_nhathuoc.remove(index);
                        adapter_nhathuoc.notifyDataSetChanged();
                        setStatus();
                        Toast.makeText(MainNhaThuoc.this, "Xóa Thành công", Toast.LENGTH_LONG).show();
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
    private NhaThuoc getNhathuoc()
    {
            NhaThuoc nhaThuoc=new NhaThuoc();
            nhaThuoc.setMaNT(txtMaNT.getText().toString());
            nhaThuoc.setTenNT(txtTenNT.getText().toString());
            nhaThuoc.setDiadiemNT(txtDiaChiNT.getText().toString());
            return nhaThuoc;
    }
    private void ShowData()
    {
        DBNhaThuoc dbNhaThuoc=new DBNhaThuoc(this);
        data_nhathuoc=dbNhaThuoc.getData();
        adapter_nhathuoc=new CustomAdapter_NT(this ,R.layout.item_nhathuoc,data_nhathuoc);
        lsNhaThuoc.setAdapter(adapter_nhathuoc);
    }
    private void getInfoNhaThuoc(NhaThuoc nhaThuoc) {
        nhaThuoc.setMaNT(txtMaNT.getText().toString());
        nhaThuoc.setTenNT(txtTenNT.getText().toString());
        nhaThuoc.setDiadiemNT(txtDiaChiNT.getText().toString());
    }

    private void setStatus() {
        txtMaNT.setText("");
        txtTenNT.setText("");
        txtDiaChiNT.setText("");
    }



    private void setControl() {
        txtMaNT = findViewById(R.id.txtMaNT);
        txtTenNT = findViewById(R.id.txtTenNT);
        txtDiaChiNT = findViewById(R.id.txtSoLuong);
        lsNhaThuoc = findViewById(R.id.lsNhaThuoc);
        btnThemNT = findViewById(R.id.btnThemNT);
        btnSuaNT = findViewById(R.id.btnSuaNT);
        btnXoaNT = findViewById(R.id.btnXoaNT);
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
                DBNhaThuoc dBnhathuoc = new DBNhaThuoc(getApplicationContext());
                dBnhathuoc.save(data_nhathuoc);
                Toast.makeText(MainNhaThuoc.this, "Lưu Thành công", Toast.LENGTH_LONG).show();
                break;
            case R.id.mnChuyen:
                Toast.makeText(getApplicationContext(),"Danh Sách Nha thuoc",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(), DanhSach_NhaThuoc.class);
                startActivity(intent1);
                break;
            case R.id.actionThoat:
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainNhaThuoc.this);
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
