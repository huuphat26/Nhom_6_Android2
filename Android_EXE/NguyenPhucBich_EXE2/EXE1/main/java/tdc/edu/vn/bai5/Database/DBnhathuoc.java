package tdc.edu.vn.bai5.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tdc.edu.vn.bai5.Model.NhaThuoc;

public class DBnhathuoc {
    DBhelper dbHelper;

    public DBnhathuoc(Context context) {
        dbHelper = new DBhelper(context);
    }

    public void addNhathuoc(NhaThuoc nhaThuoc) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maNT", nhaThuoc.getMaNT());
        values.put("tenNT", nhaThuoc.getTenNT());
        values.put("diadiemNT", nhaThuoc.getDiadiemNT());
        db.insert("NhaThuoc", null, values);
    }

    public void save(ArrayList<NhaThuoc> nhaThuoc) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (int i = 0; i < nhaThuoc.size(); i++) {
            addNhathuoc(nhaThuoc.get(i));
        }
    }

    public ArrayList<NhaThuoc> layDL() {
        ArrayList<NhaThuoc> data = new ArrayList<>();
        String sql = "select * from NhaThuoc ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            NhaThuoc nt = new NhaThuoc();
            nt.setMaNT(cursor.getString(0));
            nt.setTenNT(cursor.getString(1));
            nt.setDiadiemNT(cursor.getString(2));
            data.add(nt);
        }
        while (cursor.moveToNext());
        return data;
    }

    public void xoaNhaThuoc(NhaThuoc nhaThuoc) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maNT", nhaThuoc.getMaNT());
        values.put("tenNT", nhaThuoc.getTenNT());
        values.put("diadiem NT", nhaThuoc.getDiadiemNT());
        db.delete("NhaThuoc", "ten = '" + nhaThuoc.getTenNT() + "'", null);
    }

    public void suaNhanVien(NhaThuoc nhaThuoc) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maNT", nhaThuoc.getMaNT());
        values.put("tenNT", nhaThuoc.getTenNT());
        values.put("diadiem NT", nhaThuoc.getDiadiemNT());
        db.update("NhaThuoc", values, "ten = '" + nhaThuoc.getTenNT() + "'", null);
    }
}
