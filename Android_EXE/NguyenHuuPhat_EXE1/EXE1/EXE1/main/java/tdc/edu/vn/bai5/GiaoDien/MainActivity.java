package tdc.edu.vn.bai5.GiaoDien;

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

import com.example.quanlynhathuoc.R;

import java.util.ArrayList;

import tdc.edu.vn.bai5.Adapter.CustomerAdapter;
import tdc.edu.vn.bai5.Database.DBnhathuoc;
import tdc.edu.vn.bai5.Model.NhaThuoc;

public class MainActivity extends AppCompatActivity {
    Button btnThemNT, btnSuaNT, btnXoaNT, btnTimKiemNT, btnNgonNgu;
    EditText txtMaNT, txtTenNT, txtDiaChiNT, txtTimKiemNT;
    ListView lsNhaThuoc;
    ArrayList<NhaThuoc> data_nhathuoc = new ArrayList<>();
    CustomerAdapter adapter_nhathuoc;
    int index = -1;
    boolean ngonngu = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhathuoc);
        setControl();
        setEvent();
    }

    private void setEvent() {
        adapter_nhathuoc = new CustomerAdapter(this, R.layout.item_nhathuoc, data_nhathuoc);
        lsNhaThuoc.setAdapter(adapter_nhathuoc);
        btnNgonNgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ngonngu)
                    btnNgonNgu.setBackgroundResource(R.drawable.my);
                else
                    btnNgonNgu.setBackgroundResource(R.drawable.viet);
                ngonngu = !ngonngu;
            }
        });

        btnThemNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtMaNT.length() > 0 && txtTenNT.length() > 0) {
                    NhaThuoc nhaThuoc = new NhaThuoc();
                    getInfoNhaThuoc(nhaThuoc);
                    data_nhathuoc.add(nhaThuoc);
                    adapter_nhathuoc.notifyDataSetChanged();
                    setStatus();
                    Toast.makeText(MainActivity.this, "Thêm thành công!", Toast.LENGTH_LONG).show();
                } else {
                    if (txtMaNT.length() == 0) {
                        txtTenNT.setError("Vui lòng điền trường này");
                    }
                    if (txtTenNT.length() == 0) {
                        txtDiaChiNT.setError("Vui lòng điền trường này");
                    }
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
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo!");
                builder.setMessage("Bạn có muốn xóa khong ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_nhathuoc.remove(index);
                        adapter_nhathuoc.notifyDataSetChanged();
                        setStatus();
                        Toast.makeText(MainActivity.this, "Xóa Thành công", Toast.LENGTH_LONG).show();
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

        btnSuaNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    NhaThuoc nhaThuoc = data_nhathuoc.get(index);
                    getInfoNhaThuoc(nhaThuoc);
                    adapter_nhathuoc.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Cập nhập nhập thành công!", Toast.LENGTH_LONG).show();
                    setStatus();
                } else {
                    Toast.makeText(MainActivity.this, "Bạn chưa chọn casi nao!", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnXoaNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {

                    data_nhathuoc.remove(index);
                    adapter_nhathuoc.notifyDataSetChanged();
                    setStatus();
                    Toast.makeText(MainActivity.this, "Xóa Thành công", Toast.LENGTH_LONG).show();
                    index = -1;
                } else {
                    Toast.makeText(MainActivity.this, "Bạn chưa chọn casi nao!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void getInfoNhaThuoc(NhaThuoc nhaThuoc) {
        nhaThuoc.setMaNT(txtMaNT.getText().toString());
        nhaThuoc.setTenNT(txtTenNT.getText().toString());
        nhaThuoc.setDiadiemNT(txtDiaChiNT.getText().toString());
    }

    private void setStatus() {
        txtMaNT.setText("");
        txtTenNT.setText("");
        txtDiaChiNT.setSelection(0);
    }


    private void setControl() {
        txtMaNT = findViewById(R.id.txtMaNT);
        txtTenNT = findViewById(R.id.txtTenNT);
        txtDiaChiNT = findViewById(R.id.txtDiaChiNT);
        lsNhaThuoc = findViewById(R.id.lsNhaThuoc);
        btnThemNT = findViewById(R.id.btnThemNT);
        btnSuaNT = findViewById(R.id.btnSuaNT);
        btnXoaNT = findViewById(R.id.btnXoaNT);
        btnTimKiemNT = findViewById(R.id.btnTimKiemNT);
        btnNgonNgu = findViewById(R.id.btnNgonNgu);
        txtTimKiemNT = findViewById(R.id.txtTimKiemNT);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memu_action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionLuu:
                DBnhathuoc dBnhathuoc = new DBnhathuoc(getApplicationContext());
                dBnhathuoc.save(data_nhathuoc);
                Toast.makeText(MainActivity.this, "Lưu Thành công", Toast.LENGTH_LONG).show();
                break;
            case R.id.actionDoc:
                Toast.makeText(getApplicationContext(), "Danh Sách Nha thuoc", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), DanhsachnhathuocActivity.class);
                startActivity(intent);
                break;
            case R.id.actionThoat:
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
