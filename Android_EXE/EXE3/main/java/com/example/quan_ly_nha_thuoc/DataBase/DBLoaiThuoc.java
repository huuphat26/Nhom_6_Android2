package com.example.quan_ly_nha_thuoc.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quan_ly_nha_thuoc.Model.LoaiThuoc;
import com.example.quan_ly_nha_thuoc.Model.NhaThuoc;

import java.util.ArrayList;

public class DBLoaiThuoc {
    DBHelper dbHelper;
    public DBLoaiThuoc(Context context) {
        dbHelper = new DBHelper(context);
    }
    public void ThemLT(LoaiThuoc loaiThuoc) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mathuoc", loaiThuoc.getMaThuoc());
        values.put("tenthuoc", loaiThuoc.getTenThuoc());
        values.put("donvi", loaiThuoc.getDonVi());
        values.put("dongia", loaiThuoc.getDonGia());
        db.insert("LoaiThuoc", null, values);
    }
    public void saveLT(ArrayList<LoaiThuoc> loaiThuocs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (int i = 0; i < loaiThuocs.size(); i++) {
            ThemLT(loaiThuocs.get(i));
        }
    }
    public ArrayList<LoaiThuoc> layDLLT() {
        ArrayList<LoaiThuoc> data = new ArrayList<>();
        String sql = "select * from LoaiThuoc ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            LoaiThuoc loaiThuoc = new LoaiThuoc();
            loaiThuoc.setMaThuoc(cursor.getString(0));
            loaiThuoc.setTenThuoc(cursor.getString(1));
            loaiThuoc.setDonVi(cursor.getString(2));
            loaiThuoc.setDonGia(cursor.getString(3));
            data.add(loaiThuoc);
        }
        while (cursor.moveToNext());
        return data;
    }
    public ArrayList<LoaiThuoc> getData()
    {
        ArrayList<LoaiThuoc> data = new ArrayList<>();
        String sql="select * from LoaiThuoc";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try {
            cursor.moveToFirst();
            do {
                LoaiThuoc loaiThuoc = new LoaiThuoc();
                loaiThuoc.setMaThuoc(cursor.getString(0));
                loaiThuoc.setTenThuoc(cursor.getString(1));
                loaiThuoc.setDonVi(cursor.getString(2));
                loaiThuoc.setDonGia(cursor.getString(3));
                data.add(loaiThuoc);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }
        return  data;
    }
    public ArrayList<LoaiThuoc> layDL(String ma) {
        ArrayList<LoaiThuoc> data = new ArrayList<>();
        String sql = "select * from LoaiThuoc  where mathuoc = '"+ ma + "' ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            LoaiThuoc loaiThuoc = new LoaiThuoc();
            loaiThuoc.setMaThuoc(cursor.getString(0));
            loaiThuoc.setTenThuoc(cursor.getString(1));
            loaiThuoc.setDonVi(cursor.getString(2));
            loaiThuoc.setDonGia(cursor.getString(3));
            data.add(loaiThuoc);
        }
        while (cursor.moveToNext());
        return data;
    }
    public ArrayList<String> LayMathuoc() {
        ArrayList<String> data = new ArrayList<>();
        String sql = "select * from LoaiThuoc ";
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
    public void xoaLT(LoaiThuoc loaiThuoc) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from LoaiThuoc where maThuoc='"+ loaiThuoc.getMaThuoc()+"'";
        db.execSQL(sql);
    }

    public void suaLT(LoaiThuoc loaiThuoc) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mathuoc", loaiThuoc.getMaThuoc());
        values.put("tenthuoc", loaiThuoc.getTenThuoc());
        values.put("donvi", loaiThuoc.getDonVi());
        values.put("dongia", loaiThuoc.getDonGia());
        db.update("LoaiThuoc", values, "mathuoc = '" + loaiThuoc.getMaThuoc() + "'", null);
    }
}
