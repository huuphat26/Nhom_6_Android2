package com.example.quan_ly_nha_thuoc.Model;

public class LoaiThuoc {
    String MaThuoc,TenThuoc,DonVi,DonGia;

    public LoaiThuoc(String maThuoc, String tenThuoc, String donVi, String donGia) {
        MaThuoc = maThuoc;
        TenThuoc = tenThuoc;
        DonVi = donVi;
        DonGia = donGia;
    }

    public LoaiThuoc() {

    }

    @Override
    public String toString() {
        return
                "Mã Thuốc : " + MaThuoc + '\'' +
                "Tên Thuốc : " + TenThuoc + '\'' +
                "Đơn Vị : " + DonVi + '\'' +
                "Đơn Giá : " + DonGia + '\'' ;
    }



    public String getMaThuoc() {
        return MaThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        MaThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return TenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        TenThuoc = tenThuoc;
    }

    public String getDonVi() {
        return DonVi;
    }

    public void setDonVi(String donVi) {
        DonVi = donVi;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setDonGia(String donGia) {
        DonGia = donGia;
    }
}
