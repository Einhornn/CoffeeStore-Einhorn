package HOADON;

import CHUCNANG.Chucnang;
import CHUCNANG.Data;
import CHUCNANG.Rangbuoc;
import SANPHAM.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DSHoaDon {
    private List<Hoadon> DSHD;
    private List<CTHD> CTHDList;
    public Data data;
    public static List<Object[]> nhanvienList = new ArrayList<>();
    public void setData() {
//        khi đã liên kết với lớp nhân viên, khách hàng, sản phẩm thì k dùng này,
//        này chỉ là bộ test thui
//        maNhanvien, tenNhanvien, sdtNhanvien, emailNhanvien
        Object[] nv1 = {"NV001", "Ma Nguyen Anh Khoa", "123456789", "nv1@gmail.com"};
        Object[] nv2 = {"NV002", "Sombre Licorne", "987654321", "nv2@gmail.com"};
        Object[] nv3 = {"NV003", "Men Dung", "0111222333", "nv3@gmail.com"};
        nhanvienList.add(nv1);
        nhanvienList.add(nv2);
        nhanvienList.add(nv3);
    }
    private static Scanner sc = new Scanner(System.in);

    public DSHoaDon() {
        data = new Data();
        DSHD = data.getHoaDon("hoadon.txt");
        CTHDList = data.getCTHD("cthd.txt");
    }
//    ham này có công dụng kiểm tra có mã hóa đơn này trong hệ thống chưa
//    đề phòng nhập trùng
    public boolean checkHoadon(String maHD) {
        for (Hoadon hd : DSHD) {
            if (hd.getMaHoadon().equals(maHD)) {
                return true;
            }
        }
        return false;
    }
//    kiểm tra có nhân viên này k
    public boolean checkNhanvien(String maNV) {
        for (Object[] nv : nhanvienList) {
            if (maNV.equals(nv[0])) {
                return true;
            }
        }
        return false;
    }
    public void them() {
        System.out.println("+=============================+");
        System.out.println("|        THEM HOA DON         |");
        System.out.println("+-----------------------------+");
        Hoadon hoadon = new Hoadon();
        System.out.print("| Nhap ma hoa don: ");
        String maHoaDon = Rangbuoc.rangbuocMaHoadon();
        if (checkHoadon(maHoaDon)) {
            System.out.println("Đã tồn tại mã hóa đơn này. Bạn có muốn sửa hóa đơn không? (Y/N)");
            System.out.println("Vui lòng nhập lại!");
        }
        else {
            System.out.print("| Nhap ma nhan vien: ");
            String maNV = Rangbuoc.rangbuocMaNhanvien();
            if (!checkNhanvien(maNV)) {
                System.out.println("Không tìm thấy nhân viên, vui lòng nhập lại!");
            }
            else {
                hoadon.setMaNhanvien(maNV);
                hoadon.setMaHoadon(maHoaDon);
                String ngayHoaDon = Rangbuoc.traDate(new Date());
                hoadon.setNgayHoadon(Rangbuoc.parseDate(ngayHoaDon));
                System.out.println("+-----------------------------+");
                System.out.println("|       CHI TIET HOA DON      |");
                System.out.println("+-----------------------------+");
                List<CTHD> temp = new ArrayList<>();
                String choice;
                double tongTien = 0;
                do {
                    CTHD cthd = new CTHD();
                    cthd.setMaChitetHoadon(maHoaDon);
                    System.out.print("| Nhap ma san pham: ");
                    String maSP = Rangbuoc.rangbuocMaSanpham();
                    if (!Rangbuoc.tontaiSanpham(maSP)) {
                        System.out.println("Không có sản phẩm này trong hệ thống!");
                    } else {
                        cthd.setId(maSP);
                        System.out.print("| Chon size(S, M, L): ");
                        String size = Rangbuoc.rangBuocSize();
                        cthd.setSize(size);
                        System.out.print("| Nhap so luong: ");
                        int soLuong = Rangbuoc.rangbuocSo();
                        cthd.setSoluongSanpham(soLuong);
                        double gia = 0;
                        for (spCaPhe sp : data.getCaPhe("caphe.txt")) {
                            if (maSP.equals(sp.getId())) {
                                gia = sp.getGiaTien();
                            }
                        }
                        for (spTraSua ts : data.getTraSua("trasua.txt")) {
                            if (maSP.equals(ts.getId())) {
                                gia = ts.getGiaTien();
                            }
                        }
                        double donGia = 0;
                        if (size.equals("S")) {
                            donGia = gia - (gia * 0.1);
                        } else if (size.equals("M")) {
                            donGia = gia;
                        } else {
                            donGia = gia + (gia * 0.15);
                        }
                        cthd.setDonGia(donGia);
                        temp.add(cthd);
                        double tien = cthd.getDonGia() * cthd.getSoluongSanpham();
                        tongTien += tien;
                    }
                    System.out.println("Bạn có muốn thêm chi tiết hóa đơn nữa không? (Y/N)");
                    choice = Rangbuoc.rangbuocYesno();
                } while (choice.equals("y"));
                for (CTHD cthd : temp) {
                    CTHDList.add(cthd);
                }
                hoadon.setTienHoadon(tongTien);
                DSHD.add(hoadon);
                System.out.println("");
                System.out.println("THEM HOA DON MOI THANH CONG!");
            }
        }
    }
    public void xoa() {
        
    }
    public void timkiem() {

    }
    public String getTenSP(String ma) {
        String ten = "";
        for (spCaPhe sp : data.getCaPhe("caphe.txt")) {
            if (ma.equals(sp.getId())) {
                ten = sp.getTen();
            }
        }
        for (spTraSua ts : data.getTraSua("trasua.txt")) {
            if (ma.equals(ts.getId())) {
                ten = ts.getTen();
            }
        }
        return ten;
    }
    public String getLoaiSP(String ma) {
        String loai = "";
        for (spCaPhe sp : data.getCaPhe("caphe.txt")) {
            if (ma.equals(sp.getId())) {
                loai = sp.getLoaiCaphe();
            }
        }
        for (spTraSua ts : data.getTraSua("trasua.txt")) {
            if (ma.equals(ts.getId())) {
                loai = ts.getLoaiTrasua();
            }
        }
        return loai;
    }
    public void xuat() {
        System.out.println("+=============================+");
        System.out.println("|        XUAT HOA DON         |");
        System.out.println("+-----------------------------+");
        System.out.printf("| Nhap ma hoa don: ");
        String ma = Rangbuoc.rangbuocMaHoadon();
        int found = 0;
        for (Hoadon hd : DSHD) {
            if (hd.getMaHoadon().equals(ma)) {
                Rangbuoc.clrscr();
                System.out.println("+===============================================================+");
                System.out.println("|                           HOA DON                             |");
                System.out.println("+---------------------------------------------------------------+");
                System.out.printf("| Ma hoa don: %-49s |\n", hd.getMaHoadon());
                System.out.printf("| Ma nhan vien: %-47s |\n", hd.getMaNhanvien());
                System.out.printf("| Ngay: %-55s |\n", Rangbuoc.traDate(hd.getNgayHoadon()));
                System.out.println("+_______________________________________________________________+");
                System.out.println("| STT   Ten      Loai    Size    SL      Don gia      Tien      |");
                System.out.println("+---------------------------------------------------------------+");
                int stt = 1;
                for (CTHD cthd : CTHDList) {
                    if (cthd.getMaChitetHoadon().equals(ma)) {
                        System.out.printf("| %-5s%-9s%-10s%-8s%-6d%-13.2f%-10.2f |\n",
                                stt++, getTenSP(cthd.getId()), getLoaiSP(cthd.getId()),
                                cthd.getSize(), cthd.getSoluongSanpham(),
                                cthd.getDonGia(), cthd.getDonGia() * cthd.getSoluongSanpham());
                    }
                }
                System.out.println("+---------------------------------------------------------------+");
                System.out.printf("| Tong tien: %-50.2f |\n", hd.getTienHoadon());
                System.out.println("+===============================================================+");
                found ++;
            }
        }
        if (found == 0) {
            System.out.println("Khong co thong tin hoa don!");
            System.out.println("Vui long thu lai!");
            return;
        }
    }
    public void xuatToanboHoadon() {
        System.out.println("+===================================+");
        System.out.println("|         DANH SÁCH HÓA ĐƠN         |");
        System.out.println("+-----------------------------------+");
        for (Hoadon hoadon : DSHD) {
        }
        System.out.println("+-----------------------------------+");
    }
    public void quanlyDanhsachHoadon() {
        setData();
        int choose;
        do {
            System.out.println("+-----------------------------------------+");
            System.out.println("|        DANH MUC QUAN LY HOA DON         |");
            System.out.println("+-----------------------------------------+");
            System.out.println("|    1. Them hoa don                      |");
            System.out.println("|    2. Xoa hoa don                       |");
            System.out.println("|    3. Tim kiem hoa don                  |");
            System.out.println("|    4. Xuat hoa don                      |");
            System.out.println("|    5. Xuat toan bo hoa don              |");
            System.out.println("|    6. Xoa toan bo hoa don               |");
            System.out.println("|    7. Sua thong tin hoa don             |");
            System.out.println("|    0. Ket thuc thao tac voi hoa don     |");
            System.out.println("+-----------------------------------------+");
            System.out.println("|          Moi chon 1 thao tac !          |");
            System.out.println("+-----------------------------------------+");
            choose = Rangbuoc.rangbuocSo();
            switch (choose) {
                case 1:
                    them();
                    break;
                case 2:
                    xoa();
                    break;
                case 3:
                    timkiem();
                    break;
                case 4:
                    xuat();
                    break;
//                case 5:
//                    break;
//                case 6:
//                    List<Hoadon> hoadonsFromFile = readHD();
//                    hoadonList.addAll(hoadonsFromFile);
//                    break;
//                case 7:
//                    writeHD(hoadonList);
//                    break;
                case 0:
                    data.setHoaDon(DSHD, "hoadon.txt");
                    data.setCTHD(CTHDList, "cthd.txt");
                    System.out.println("==== KET THUC THAO TAC VOI HOA DON =====");
            }
        } while (choose != 0);
    }

    public static void main(String[] args) {
        DSHoaDon dsHoaDon = new DSHoaDon();
        dsHoaDon.quanlyDanhsachHoadon();
    }
}
