package com.example.quan_ly_nha_thuoc.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quan_ly_nha_thuoc.Model.NhaThuoc;

import java.util.ArrayList;

public class DBNhaThuoc {
    DBHelper dbHelper;

    public DBNhaThuoc(Context context) {
        dbHelper = new DBHelper(context);
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
    public ArrayList<NhaThuoc> getData()
    {
        ArrayList<NhaThuoc> data = new ArrayList<>();
        String sql="select * from NhaThuoc";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try {
            cursor.moveToFirst();
            do {
                NhaThuoc nhaThuoc = new NhaThuoc();
                nhaThuoc.setMaNT(cursor.getString(0));
                nhaThuoc.setTenNT(cursor.getString(1));
                nhaThuoc.setDiadiemNT(cursor.getString(2));
                data.add(nhaThuoc);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }
        return  data;
    }


    public ArrayList<NhaThuoc> layDLNT() {
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
    public ArrayList<NhaThuoc> layDL(String ma) {
        ArrayList<NhaThuoc> data = new ArrayList<>();
        String sql = "select * from NhaThuoc where maNT = '"+ ma + "' ";
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
    public ArrayList<String> LayMaNT() {
        ArrayList<String> data = new ArrayList<>();
        String sql = "select * from NhaThuoc ";
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

    public void xoaNhaThuoc(NhaThuoc nhaThuoc) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("maNT", nhaThuoc.getMaNT());
//        values.put("tenNT", nhaThuoc.getTenNT());
//        values.put("diadiem NT", nhaThuoc.getDiadiemNT());
//        db.delete("NhaThuoc", "maNT = '" + nhaThuoc.getTenNT() + "'", null);
        String sql ="Delete from NhaThuoc where maNT='"+ nhaThuoc.getMaNT()+"'";
        db.execSQL(sql);
    }

    public void suaNhathuoc(NhaThuoc nhaThuoc) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maNT", nhaThuoc.getMaNT());
        values.put("tenNT", nhaThuoc.getTenNT());
        values.put("diadiemNT", nhaThuoc.getDiadiemNT());
        db.update("NhaThuoc", values, "maNT = '" + nhaThuoc.getMaNT() + "'", null);
    }

}
