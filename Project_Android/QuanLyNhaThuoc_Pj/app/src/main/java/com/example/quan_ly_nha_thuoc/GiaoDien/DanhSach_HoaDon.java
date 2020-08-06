package com.example.quan_ly_nha_thuoc.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.quan_ly_nha_thuoc.Adpater.CustomAdapter_HD;
import com.example.quan_ly_nha_thuoc.DataBase.DBHoaDon;
import com.example.quan_ly_nha_thuoc.Model.HoaDon;
import com.example.quan_ly_nha_thuoc.R;

import java.util.ArrayList;

public class DanhSach_HoaDon extends AppCompatActivity {
    ListView lvDanhSach_HD;
    ArrayList<HoaDon> data_HoaDons = new ArrayList<>();
    CustomAdapter_HD adapter_hd;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsach_hoadon);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        DBHoaDon dbHoaDon = new DBHoaDon(this);
        lvDanhSach_HD = findViewById(R.id.lvDanhSach_HD);
        CustomAdapter_HD adapter = new CustomAdapter_HD(this, R.layout.item_hoadon, dbHoaDon.layDLHD());
        lvDanhSach_HD.setAdapter(adapter);
        setEvent();
    }
    private void setEvent() {
        DBHoaDon dbHoaDon = new DBHoaDon(this);
        data_HoaDons = dbHoaDon.layDLHD();
        adapter_hd = new CustomAdapter_HD(this, R.layout.item_hoadon, data_HoaDons);
        lvDanhSach_HD.setAdapter(adapter_hd);
        registerForContextMenu(lvDanhSach_HD);
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
                    adapter_hd.filter("");
                    lvDanhSach_HD.clearTextFilter();
                } else {
                    adapter_hd.filter(s);
                }
                return true;
            }
        });
        return true;
    }
}