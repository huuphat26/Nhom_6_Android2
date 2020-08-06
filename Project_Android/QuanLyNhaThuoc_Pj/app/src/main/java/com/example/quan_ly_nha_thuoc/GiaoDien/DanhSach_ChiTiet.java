package com.example.quan_ly_nha_thuoc.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.quan_ly_nha_thuoc.Adpater.CustomAdapter_CT;
import com.example.quan_ly_nha_thuoc.DataBase.DBChiTiet;
import com.example.quan_ly_nha_thuoc.Model.ChiTiet;
import com.example.quan_ly_nha_thuoc.R;

import java.util.ArrayList;

public class DanhSach_ChiTiet extends AppCompatActivity {
    ListView lvDanhSach_CT;
    ArrayList<ChiTiet> data_ChiTiet = new ArrayList<>();
    CustomAdapter_CT adapter_ct;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsach_chitiet);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        DBChiTiet dbChiTiet = new DBChiTiet(this);
        lvDanhSach_CT = findViewById(R.id.lvDanhSach_CT);
        CustomAdapter_CT adapter = new CustomAdapter_CT(this, R.layout.item_chitiet, dbChiTiet.layDLCT());
        lvDanhSach_CT.setAdapter(adapter);
        setEvent();
    }

    private void setEvent() {
        DBChiTiet dbChiTiet = new DBChiTiet(this);
        data_ChiTiet = dbChiTiet.layDLCT();
        adapter_ct = new CustomAdapter_CT(this, R.layout.item_chitiet, data_ChiTiet);
        lvDanhSach_CT.setAdapter(adapter_ct);
        registerForContextMenu(lvDanhSach_CT);
    }

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
                    adapter_ct.filter("");
                    lvDanhSach_CT.clearTextFilter();
                } else {
                    adapter_ct.filter(s);
                }
                return true;
            }
        });
        return true;
    }
}