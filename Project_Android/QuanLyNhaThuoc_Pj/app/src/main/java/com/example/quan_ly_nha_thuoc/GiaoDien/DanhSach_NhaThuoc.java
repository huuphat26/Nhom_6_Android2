package com.example.quan_ly_nha_thuoc.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.quan_ly_nha_thuoc.Adpater.CustomAdapter_NT;
import com.example.quan_ly_nha_thuoc.DataBase.DBNhaThuoc;
import com.example.quan_ly_nha_thuoc.Model.NhaThuoc;
import com.example.quan_ly_nha_thuoc.R;

import java.util.ArrayList;
public class DanhSach_NhaThuoc extends AppCompatActivity {
    ListView lvDanhSachNT;
    ArrayList<NhaThuoc> data_nhathuoc = new ArrayList<>();
    CustomAdapter_NT adapter_nt;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsach_nhathuoc);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        DBNhaThuoc dBnhathuoc = new DBNhaThuoc(this);
        lvDanhSachNT = findViewById(R.id.lvDanhSachnt);
        CustomAdapter_NT adapter = new CustomAdapter_NT(this, R.layout.item_nhathuoc, dBnhathuoc.layDLNT());
        lvDanhSachNT.setAdapter(adapter);
        setEvent();
    }

    private void setEvent() {
        DBNhaThuoc dBnhathuoc = new DBNhaThuoc(this);
        data_nhathuoc = dBnhathuoc.layDLNT();
        adapter_nt = new CustomAdapter_NT(this, R.layout.item_nhathuoc, data_nhathuoc);
        lvDanhSachNT.setAdapter(adapter_nt);
        registerForContextMenu(lvDanhSachNT);
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionsearch, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    adapter_nt.filter("");
                    lvDanhSachNT.clearTextFilter();
                } else {
                    adapter_nt.filter(s);
                }
                return true;
            }
        });
        return true;
    }
}
