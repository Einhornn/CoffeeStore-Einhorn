package HOADON;

import CHUCNANG.Rangbuoc;
import java.util.Date;
import java.util.Scanner;

public class Hoadon {
    public String maHoadon;
    public String maNhanvien;
    public Date ngayHoadon;
    public double tienHoadon;
    public static Scanner inp = new Scanner(System.in);
    public Hoadon() {

    }
    public Hoadon(String maHoadon, String maNhanvien, Date ngayHoadon, double tienHoadon) {
        this.maHoadon = maHoadon;
        this.maNhanvien = maNhanvien;
        this.ngayHoadon = ngayHoadon;
        this.tienHoadon = tienHoadon;
    }

    public void setMaNhanvien(String maNhanvien) {
        this.maNhanvien = maNhanvien;
    }

    public String getMaNhanvien() {
        return maNhanvien;
    }

    public void setMaHoadon(String maHoadon) {
        this.maHoadon = maHoadon;
    }

    public String getMaHoadon() {
        return maHoadon;
    }


    public double getTienHoadon() {
        return tienHoadon;
    }

    public void setTienHoadon(double tienHoadon) {
        this.tienHoadon = tienHoadon;
    }

    public Date getNgayHoadon() {
        return ngayHoadon;
    }

    public void setNgayHoadon(Date ngayHoadon) {
        this.ngayHoadon = ngayHoadon;
    }
    public void nhapHoadon() {
        System.out.print("Nhap ma hoa don: ");
        String maHD = Rangbuoc.rangbuocMaHoadon();
        setMaHoadon(maHD);
        System.out.print("Nhap ma nhan vien: ");
//        này là do chưa có lớp nhân viên, file nhân viên nên mình sẽ nhập như vậy,
//        khi có file nhân viên sẽ check xem coi mã nhân viên này có tồn tại hay không trước rồi mới thực hiện tiếp
        String maNV = Rangbuoc.rangbuocMaNhanvien();
        setMaNhanvien(maNV);
//        System.out.print("Nhap ngay hoa don (dd/MM/yyyy): ");
//        Date ngayHD = Rangbuoc.rangbuocNgayhoadon();
        Date ngayHD = (new Date());
        setNgayHoadon(ngayHD);
    }
    public void xuatHoadon () {
        System.out.println("+=========================================+");
        System.out.println("|                  HÓA ĐƠN                |");
        System.out.println("+-----------------------------------------+");
        System.out.println("| Mã hóa đơn: " + getMaHoadon());
        System.out.println("| Mã nhân viên: " + getMaNhanvien());
        System.out.println("| Ngày: " + getNgayHoadon());
        System.out.println("| Tổng tiền: " + getTienHoadon());
        System.out.println("+-----------------------------------------+");
    }
}
