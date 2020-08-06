package com.example.quan_ly_nha_thuoc.DataBase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quan_ly_nha_thuoc.Model.HoaDon;
import com.example.quan_ly_nha_thuoc.Model.LoaiThuoc;

import java.util.ArrayList;
public class DBHoaDon  {
    DBHelper dbHelper;
    public DBHoaDon(Context context) {
        dbHelper = new DBHelper(context);
    }
    public void ThemHD(HoaDon hoaDon) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sohoadon", hoaDon.getSoHoaDon());
        values.put("maNT", hoaDon.getMaNhaThuoc());
        values.put("ngayin", hoaDon.getNgayIn());
        db.insert("HoaDon", null, values);
    }
    public void saveHD(ArrayList<HoaDon> hoaDons) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (int i = 0; i < hoaDons.size(); i++) {
            ThemHD(hoaDons.get(i));
        }
    }
    public ArrayList<HoaDon> getData()
    {
        ArrayList<HoaDon> data = new ArrayList<>();
        String sql="select * from HoaDon";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try {
            cursor.moveToFirst();
            do {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaNhaThuoc(cursor.getString(0));
                hoaDon.setSoHoaDon(cursor.getString(1));
                hoaDon.setNgayIn(cursor.getString(2));
                data.add(hoaDon);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }
        return  data;
    }
    public ArrayList<HoaDon> layDLHD() {
        ArrayList<HoaDon> data = new ArrayList<>();
        String sql = "select * from HoaDon ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {

            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaNhaThuoc(cursor.getString(0));
            hoaDon.setSoHoaDon(cursor.getString(1));
            hoaDon.setNgayIn(cursor.getString(2));
            data.add(hoaDon);
        }
        while (cursor.moveToNext());
        return data;
    }
    public ArrayList<HoaDon> layDL(String ma) {
        ArrayList<HoaDon> data = new ArrayList<>();
        String sql = "select * from HoaDon  where sohoadon = '"+ ma + "' ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {

            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaNhaThuoc(cursor.getString(0));
            hoaDon.setSoHoaDon(cursor.getString(1));
            hoaDon.setNgayIn(cursor.getString(2));
            data.add(hoaDon);
        }
        while (cursor.moveToNext());
        return data;
    }
    public ArrayList<String> LaySoHD() {
        ArrayList<String> data = new ArrayList<>();
        String sql = "select * from HoaDon ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                data.add(cursor.getString(0));
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
        }
        return data;
    }
    public void xoaHoaDon(HoaDon hoaDon) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from HoaDon where maNT='"+ hoaDon.getMaNhaThuoc()+"'";
        db.execSQL(sql);
    }

    public void suaHoaDon(HoaDon hoaDon) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maNT", hoaDon.getMaNhaThuoc());
        values.put("sohoadon", hoaDon.getSoHoaDon());
        values.put("ngayin", hoaDon.getNgayIn());
        db.update("HoaDon", values, "maNT = '" + hoaDon.getMaNhaThuoc() + "'", null);
    }

}
