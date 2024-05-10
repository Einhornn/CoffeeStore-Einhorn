package DATHANG;

public class DSDatHang {
    private String tenMatHang;
    private String loaiMatHang;
    private String kichThuoc;
    private int soLuong;
    private double donGia;
    private double thanhTien;

    public DSDatHang(String tenMatHang, String loaiMatHang, String kichThuoc, int soLuong, double donGia, double thanhTien) {
        this.tenMatHang = tenMatHang;
        this.loaiMatHang = loaiMatHang;
        this.kichThuoc = kichThuoc;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getLoaiMatHang() {
        return loaiMatHang;
    }

    public void setLoaiMatHang(String loaiMatHang) {
        this.loaiMatHang = loaiMatHang;
    }

    public String getTenMatHang() {
        return tenMatHang;
    }

    public void setTenMatHang(String tenMatHang) {
        this.tenMatHang = tenMatHang;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getDonGia() {
        return donGia;
    }

    public boolean equals(DSDatHang mathang) {
        return this.tenMatHang.equals(mathang.getTenMatHang()) &&
                this.loaiMatHang.equals(mathang.getLoaiMatHang()) &&
                this.kichThuoc.equals(mathang.getKichThuoc());
    }
}

