package com.example.quan_ly_nha_thuoc.DataBase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.quan_ly_nha_thuoc.Model.ChiTiet;
import com.example.quan_ly_nha_thuoc.Model.HoaDon;

import java.util.ArrayList;
public class DBChiTiet {
    DBHelper dbHelper;
    public DBChiTiet(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void ThemCT(ChiTiet chiTiet) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mathuoc", chiTiet.getMathuoc());
        values.put("soluong", chiTiet.getSoluong());
        values.put("sohoadon", chiTiet.getSohoadon());
        db.insert("ChiTiet", null, values);
    }

    public void SaveCT(ArrayList<ChiTiet> chiTiets) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (int i = 0; i < chiTiets.size(); i++) {
            ThemCT(chiTiets.get(i));
        }
    }

    public ArrayList<ChiTiet> layDLCT() {
        ArrayList<ChiTiet> data = new ArrayList<>();
        String sql = "select * from ChiTiet ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            ChiTiet chiTiet = new ChiTiet();
            chiTiet.setMathuoc(cursor.getString(0));
            chiTiet.setSohoadon(cursor.getString(1));
            chiTiet.setSoluong(cursor.getString(2));
            data.add(chiTiet);
        }
        while (cursor.moveToNext());
        return data;
    }
    public ArrayList<ChiTiet> getData()
    {
        ArrayList<ChiTiet> data = new ArrayList<>();
        String sql="select * from ChiTiet";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        try {
            cursor.moveToFirst();
            do {
                ChiTiet chiTiet = new ChiTiet();
                chiTiet.setMathuoc(cursor.getString(0));
                chiTiet.setSohoadon(cursor.getString(1));
                chiTiet.setSoluong(cursor.getString(2));
                data.add(chiTiet);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }
        return  data;
    }
    public ArrayList<ChiTiet> layDL(String ma) {
        ArrayList<ChiTiet> data = new ArrayList<>();
        String sql = "select * from ChiTiet  where mathuoc = '"+ ma + "' ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            ChiTiet chiTiet = new ChiTiet();
            chiTiet.setMathuoc(cursor.getString(0));
            chiTiet.setSohoadon(cursor.getString(1));
            chiTiet.setSoluong(cursor.getString(2));
            data.add(chiTiet);
        }
        while (cursor.moveToNext());
        return data;
    }
    public void xoaChitiet(ChiTiet chiTiet) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from ChiTiet where mathuoc='"+ chiTiet.getMathuoc()+"'";
        db.execSQL(sql);
    }

    public void suaChitiet(ChiTiet chiTiet) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mathuoc", chiTiet.getMathuoc());
        values.put("soluong", chiTiet.getSoluong());
        values.put("sohoadon", chiTiet.getSohoadon());
        db.update("ChiTiet", values, "mathuoc = '" + chiTiet.getMathuoc() + "'", null);
    }
}
