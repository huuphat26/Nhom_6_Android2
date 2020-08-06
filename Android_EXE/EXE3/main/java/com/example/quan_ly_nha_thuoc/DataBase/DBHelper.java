package com.example.quan_ly_nha_thuoc.DataBase;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Yte", null, 1);
    }
    //truy van khong tra ket qua:
    public void QueryData(String sql)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    //Truy van có tra ket qua :
    private Cursor Get_Data(String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlHD = "Create table HoaDon (sohoadon text,maNT text , ngayin text)";
        String sqlNT = "Create table NhaThuoc (maNT text,tenNT text,diadiemNT text)";
        String sqlLT = "Create table LoaiThuoc (mathuoc text,tenthuoc text,donvi text, dongia text)";
        String sqlCT = "Create table ChiTiet (mathuoc text,soluong text,sohoadon text)";
        db.execSQL(sqlHD);
        db.execSQL(sqlNT);
        db.execSQL(sqlLT);
        db.execSQL(sqlCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}