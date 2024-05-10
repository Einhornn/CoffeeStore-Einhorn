package HOADON;

import CHUCNANG.Chucnang;
import CHUCNANG.Data;
import CHUCNANG.Rangbuoc;
import SANPHAM.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.*;

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
        System.out.println("+=============================+");
        System.out.println("|        XOA HOA DON         |");
        System.out.println("+-----------------------------+");
        System.out.print("Nhap ma hoa don: ");
        String mahd = Rangbuoc.rangbuocMaHoadon();
        String choice;
        do {
            Iterator<Hoadon> iterator = this.DSHD.iterator();
            int found = 0;
            while (iterator.hasNext()) {
                Hoadon hd = iterator.next();
                if (mahd.equalsIgnoreCase(hd.maHoadon)) {
                    System.out.println("Ban co that su muon xoa? (y/n)");
                    String chon = Rangbuoc.rangbuocYesno();
                    if (chon.equals("y")) {
                        iterator.remove();
                        System.out.println("Xoa thanh cong!");
                    }
                    else {
                        System.out.println("Khong the thuc hien xoa!");
                        return;
                    }
                    found++;
                }
            }
            if (found == 0) {
                System.out.println("Khong ton tai hoa don muon xoa!");
            }
            System.out.println("Bạn có muốn xóa hóa đơn nữa không? (Y/N)");
            choice = Rangbuoc.rangbuocYesno();
        } while (choice.equals("y"));
    }
    public void timkiem() {
        System.out.println("+=============================+");
        System.out.println("|        TIM KIEM HOA DON     |");
        System.out.println("+-----------------------------+");
        String choice;
        do{
            System.out.print("Nhap ma hoa don: ");
            String mahd = Rangbuoc.rangbuocMaHoadon();
            int found = 0;
            for(Hoadon hd : this.DSHD) {
                if(hd.maHoadon.equalsIgnoreCase(mahd)) {
                    hd.xuatHoadon();
                    for(CTHD cthd : this.CTHDList){
                        if(cthd.maChitetHoadon.equals(hd.maHoadon)){
                            cthd.xuatHoadon();
                            found++;
                        }
                    }
                    System.out.println("+---------------------------------------------------------+");
                }
            }
            if(found == 0){
                System.out.println("Khong co ma hoa don can tim");
            }
            System.out.println("Bạn có muốn tiếp tục tìm kiếm hóa đơn (y/n)");
            choice = Rangbuoc.rangbuocYesno();
        }while(choice.equals("y"));
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
                                stt++, Rangbuoc.getTenSP(cthd.getId()), Rangbuoc.getLoaiSP(cthd.getId()),
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
        if (this.DSHD.size() == 0) {
            System.out.println("Khong co hoa don de xuat");
        } else {
            System.out.println("+===============================================================+");
            System.out.println("|                          DANH SACH HOA DON                    |");
            System.out.println("+---------------------------------------------------------------+");
            System.out.println("| Mã hóa đơn        Mã nhân viên        Ngày        Tổng tiền   |");
            System.out.println("+---------------------------------------------------------------+");
            for (Hoadon hd : this.DSHD) {
                System.out.printf("| %-20s%-14s%-17s%-10s |\n", hd.getMaHoadon(), hd.getMaNhanvien(), Rangbuoc.traDate(hd.getNgayHoadon()), hd.getTienHoadon());
            }
            System.out.println("+===============================================================+");
        }
    }
    public void xoaToanboHoadon() {
            if (this.DSHD.size() == 0) {
                System.out.println("Khong co hoa don de xoa!");
            } else {
                Iterator<Hoadon> iterator = this.DSHD.iterator();
                while (iterator.hasNext()) {
                    Hoadon hd = iterator.next();
                    iterator.remove();
                }
                Iterator<CTHD> iterator1 = this.CTHDList.iterator();
                while (iterator1.hasNext()) {
                    CTHD ct = iterator1.next();
                    iterator1.remove();
                }
            }
        System.out.println("Xoa toan bo hoa don thanh cong!");
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
                case 5:
                    xuatToanboHoadon();
                    break;
                case 6:
                    xoaToanboHoadon();
                    break;
                case 0:
                    data.setHoaDon(DSHD, "hoadon.txt");
                    data.setCTHD(CTHDList, "cthd.txt");
                    System.out.println("Thoat.........");
                    return;
            }
        } while (choose != 0);
    }

    public static void main(String[] args) {
        DSHoaDon dsHoaDon = new DSHoaDon();
        dsHoaDon.quanlyDanhsachHoadon();
    }
}
