package com.example.quan_ly_nha_thuoc.Model;

public class ChiTiet {
    String sohoadon, mathuoc, soluong;

    public ChiTiet() {
    }

    public ChiTiet(String sohoadon, String mathuoc, String soluong) {
        this.sohoadon = sohoadon;
        this.mathuoc = mathuoc;
        this.soluong = soluong;
    }

    public String getSohoadon() {
        return sohoadon;
    }

    public void setSohoadon(String sohoadon) {
        this.sohoadon = sohoadon;
    }

    public String getMathuoc() {
        return mathuoc;
    }

    public void setMathuoc(String mathuoc) {
        this.mathuoc = mathuoc;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        return "ChiTiet{" +
                "Số Hóa Đơn : " + sohoadon + '\'' +
                "Mã Thuốc : " + mathuoc + '\'' +
                "Số Lượng : " + soluong + '\'' ;
    }
}
