package HOADON;
import CHUCNANG.Rangbuoc;

import java.util.Scanner;

public class CTHD {
    public String maChitetHoadon;
    public String id;
    public String size;
    public int soluongSanpham;
    public double donGia;
    public CTHD() {
    }
    public CTHD (String  maHoadon, String id, int soluongSanpham, String size, double tienSanpham)  {
        this.maChitetHoadon = maHoadon;
        this.soluongSanpham = soluongSanpham;
        this.id = id;
        this.size = size;
        this.donGia = tienSanpham;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaChitetHoadon() {
        return maChitetHoadon;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setMaChitetHoadon(String maChitetHoadon) {
        this.maChitetHoadon = maChitetHoadon;
    }

    public int getSoluongSanpham() {
        return soluongSanpham;
    }

    public void setSoluongSanpham(int soluongSanpham) {
        this.soluongSanpham = soluongSanpham;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public void nhapCTHoadon() {
        Scanner inp = new Scanner(System.in);
        System.out.print("Nhap ma san pham: ");
        String ma = Rangbuoc.rangbuocMaSanpham();
        setId(ma);
        System.out.println("Chon kich thuoc: ");
        System.out.println("1. S");
        System.out.println("2. M");
        System.out.println("3. L");
        int sizeChoice = Rangbuoc.rangbuocSo();
        while (sizeChoice < 1 || sizeChoice > 3) {
            System.out.println("Vui long nhap lai!");
            sizeChoice = Rangbuoc.rangbuocSo();
        }
        switch (sizeChoice) {
            case 1:
                setSize("S");
            case 2:
                setSize("M");
            case 3:
                setSize("L");
        }
        System.out.println("So luong san pham: ");
        int slSP = Rangbuoc.rangbuocSo();
        setSoluongSanpham(slSP);
    }
    public void xuatHoadon() {

    }

}
