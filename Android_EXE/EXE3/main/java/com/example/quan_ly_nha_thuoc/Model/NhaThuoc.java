package com.example.quan_ly_nha_thuoc.Model;

public class NhaThuoc {
    String maNT;
    String tenNT;
    String diadiemNT;

    @Override
    public String toString() {
        return
                "Mã Nhà Thuốc : " + maNT + '\'' +
                "Tên Nhà Thuốc :" + tenNT + '\'' +
                "Địa Chỉ :" + diadiemNT + '\'' +
                '}';
    }

    public void setMaNT(String maNT) {
        this.maNT = maNT;
    }

    public void setTenNT(String tenNT) {
        this.tenNT = tenNT;
    }

    public void setDiadiemNT(String diadiemNT) {
        this.diadiemNT = diadiemNT;
    }

    public String getMaNT() {
        return maNT;
    }

    public String getTenNT() {
        return tenNT;
    }

    public String getDiadiemNT() {
        return diadiemNT;
    }

    public NhaThuoc() {
        this.maNT = maNT;
        this.tenNT = tenNT;
        this.diadiemNT = diadiemNT;
    }
}
