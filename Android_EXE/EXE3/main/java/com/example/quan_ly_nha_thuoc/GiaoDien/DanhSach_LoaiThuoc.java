package com.example.quan_ly_nha_thuoc.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import com.example.quan_ly_nha_thuoc.Adpater.CustomAdapter_LT;
import com.example.quan_ly_nha_thuoc.DataBase.DBLoaiThuoc;
import com.example.quan_ly_nha_thuoc.Model.LoaiThuoc;
import com.example.quan_ly_nha_thuoc.R;

import java.util.ArrayList;

public class DanhSach_LoaiThuoc extends AppCompatActivity {
    ListView lvDanhSach_LT;
    ArrayList<LoaiThuoc> data_loaithuoc = new ArrayList<>();
    CustomAdapter_LT adapter_LT;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsach_loaithuoc);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        DBLoaiThuoc dbLoaiThuoc = new DBLoaiThuoc(this);
        lvDanhSach_LT = findViewById(R.id.lvDanhSach_LT);
        CustomAdapter_LT adapter = new CustomAdapter_LT(this, R.layout.item_loaithuoc, dbLoaiThuoc.layDLLT());
        lvDanhSach_LT.setAdapter(adapter);
        setEvent();
    }

    private void setEvent() {
        DBLoaiThuoc dbLoaiThuoc = new DBLoaiThuoc(this);
        data_loaithuoc = dbLoaiThuoc.layDLLT();
        adapter_LT = new CustomAdapter_LT(this, R.layout.item_loaithuoc, data_loaithuoc);
        lvDanhSach_LT.setAdapter(adapter_LT);
        registerForContextMenu(lvDanhSach_LT);
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
                    adapter_LT.filter("");
                    lvDanhSach_LT.clearTextFilter();
                } else {
                    adapter_LT.filter(s);
                }
                return true;
            }
        });
        return true;
    }
}
