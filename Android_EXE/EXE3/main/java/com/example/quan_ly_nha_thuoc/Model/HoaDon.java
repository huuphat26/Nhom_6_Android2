package com.example.quan_ly_nha_thuoc.Model;
public class HoaDon {
    String soHoaDon,MaNhaThuoc,NgayIn;

    public String getSoHoaDon() {
        return soHoaDon;
    }

    public void setSoHoaDon(String soHoaDon) {
        this.soHoaDon = soHoaDon;
    }

    public HoaDon() {
        this.soHoaDon = soHoaDon;
        this.NgayIn = NgayIn;
        this.MaNhaThuoc = MaNhaThuoc;

    }

    @Override
    public String toString() {
        return
                "Số Hóa Đơn : " + soHoaDon + '\'' +
                "Mã Nhà Thuốc : " + MaNhaThuoc + '\'' +
                "Ngày In : " + NgayIn + '\'' +
                '}';
    }

    public HoaDon(String maHoaDon, String maNhaThuoc, String ngayIn) {
        soHoaDon = maHoaDon;
        NgayIn = ngayIn;
        MaNhaThuoc = maNhaThuoc;

    }



    public String getMaNhaThuoc() {
        return MaNhaThuoc;
    }

    public void setMaNhaThuoc(String maNhaThuoc) {
        MaNhaThuoc = maNhaThuoc;
    }

    public String getNgayIn() {
        return NgayIn;
    }

    public void setNgayIn(String ngayIn) {
        NgayIn = ngayIn;
    }
}
