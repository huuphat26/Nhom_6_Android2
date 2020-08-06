package tdc.edu.vn.bai5.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.quanlynhathuoc.R;

import java.util.ArrayList;

import tdc.edu.vn.bai5.Adapter.CustomerAdapter;
import tdc.edu.vn.bai5.Database.DBnhathuoc;
import tdc.edu.vn.bai5.Model.NhaThuoc;

public class DanhsachnhathuocActivity extends AppCompatActivity {
    ListView lvDanhSach;
    ArrayList<NhaThuoc> data_nhathuoc = new ArrayList<>();
    CustomerAdapter adapter_nt;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachnhathuoc);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        DBnhathuoc dBnhathuoc = new DBnhathuoc(this);
        lvDanhSach = findViewById(R.id.lvDanhSachNT);
        CustomerAdapter adapter = new CustomerAdapter(this, R.layout.item_nhathuoc, dBnhathuoc.layDL());
        lvDanhSach.setAdapter(adapter);
        setEvent();
    }

    private void setEvent() {
        DBnhathuoc dBnhathuoc = new DBnhathuoc(this);
        data_nhathuoc = dBnhathuoc.layDL();
        adapter_nt = new CustomerAdapter(this, R.layout.item_nhathuoc, data_nhathuoc);
        lvDanhSach.setAdapter(adapter_nt);
        registerForContextMenu(lvDanhSach);

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
                    lvDanhSach.clearTextFilter();
                } else {
                    adapter_nt.filter(s);
                }
                return true;
            }
        });
        return true;
    }
}
