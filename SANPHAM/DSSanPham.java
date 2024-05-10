package SANPHAM;
import CHUCNANG.Chucnang;
import CHUCNANG.Data;
import CHUCNANG.Rangbuoc;
import java.util.List;
import java.util.Scanner;

public class DSSanPham implements Chucnang {
    public Scanner scan = new Scanner(System.in);
    public List<spCaPhe> DSCP;
    public List<spTraSua> DSTS;
    private Data data;
    private String fcaphe = "caphe.txt";
    private String ftrasua = "trasua.txt";

    public DSSanPham() {
        data = new Data();
        DSCP = data.getCaPhe(fcaphe);
        DSTS = data.getTraSua(ftrasua);
    }
    @Override
    public void them() {
        System.out.println("+=============================+");
        System.out.println("|        THEM SAN PHAM        |");
        System.out.println("+-----------------------------+");
        System.out.println("| 1. Ca phe                   |");
        System.out.println("| 2. Tra sua                  |");
        System.out.println("| 0. Thoat                    |");
        System.out.println("+=============================+");
        int choice;
        do {
            System.out.print("Nhap lua chon: ");
            choice = Rangbuoc.rangbuocSo();
            switch (choice) {
                case 1:
                    spCaPhe spCaPhe = new spCaPhe();
                    spCaPhe.nhapThongTin();
                    if (!kiemTraTonTai(spCaPhe.getId())) {
                        DSCP.add(spCaPhe);
                        data.setCaPhe(DSCP, fcaphe);
                        System.out.println("THEM SAN PHAM THANH CONG!");
                    } else {
                        System.out.println("DA CO MA SAN PHAM NAY, VUI LONG THU LAI!");
                    }
                    break;
                case 2:
                    spTraSua spTraSua = new spTraSua();
                    spTraSua.nhapThongTin();
                    if (!kiemTraTonTai(spTraSua.getId())) {
                        DSTS.add(spTraSua);
                        data.setTraSua(DSTS, ftrasua);
                        System.out.println("THEM SAN PHAM THANH CONG!");
                    } else {
                        System.out.println("DA CO MA SAN PHAM NAY, VUI LONG THU LAI!");
                    }
                    break;
                case 0:
                    System.out.println("Quay lai Menu quan ly san pham.");
                    return;
                default:
                    System.out.println("LUA CHON KHONG HOP LE!");
            }
        } while (choice != 0);
    }
    private boolean kiemTraTonTai(String id) {
        for (spCaPhe cp : DSCP) {
            if (cp.getId().equals(id)) {
                return true;
            }
        }
        for (spTraSua ts : DSTS) {
            if (ts.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void sua() {
        System.out.println("+=============================+");
        System.out.println("|        SUA SAN PHAM         |");
        System.out.println("+-----------------------------+");
        System.out.println("| 1. Ca phe                   |");
        System.out.println("| 2. Tra sua                  |");
        System.out.println("| 0. Thoat                    |");
        System.out.println("+=============================+");
        int choice;
        do {
            System.out.print("Nhap lua chon loai san pham muon sua: ");
            choice = Rangbuoc.rangbuocSo();
            switch (choice) {
                case 1:
                    suaSanPham(DSCP, fcaphe);
                    break;
                case 2:
                    suaSanPham(DSTS, ftrasua);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }
    public void xuatBang(SanPham sanPham) {
        System.out.println("+=============================================================+");
        System.out.println("|                       THONG TIN SAN PHAM                    |");
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("|     Ma     |     Ten    |     Loai san pham     |    Gia    |");
        System.out.println("+-------------------------------------------------------------+");
        if (sanPham instanceof spCaPhe) {
            spCaPhe cp = (spCaPhe) sanPham;
            cp.xuat();
        }
        else {
            spTraSua ts = (spTraSua) sanPham;
            ts.xuat();
        }
        System.out.println("\n" +
                "+=============================================================+" +
                "");
    }
    private void xuatBangDanhSachSanPham(List<? extends SanPham> list) {
        if (list.isEmpty()) {
            System.out.println("Danh sach san pham rong!");
            return;
        }
        System.out.println("");
        System.out.println("+=============================================================+");
        System.out.println("|                       THONG TIN SAN PHAM                    |");
        System.out.println("+=============================================================+");
        System.out.println("|     Ma     |     Ten    |     Loai san pham     |    Gia    |");
        System.out.println("+-------------------------------------------------------------+");
        for (SanPham sp : list) {
            if (sp instanceof spCaPhe) {
                spCaPhe cp = (spCaPhe) sp;
                cp.xuat();
            }
            else {
                spTraSua ts = (spTraSua) sp;
                ts.xuat();
            }
            System.out.println("");
        }
        System.out.println("+=============================================================+");
        System.out.println("");
    }

    private void xuatToanBoSanPham(List<? extends SanPham> list) {
        if (list.isEmpty()) {
            System.out.println("Danh sach san pham rong!");
            return;
        }
        xuatBangDanhSachSanPham(list);
    }
    private void xoaToanBoSanPham(List<? extends SanPham> list, String fileName) {
        if (list.isEmpty()) {
            System.out.println("Danh sach san pham rong!");
            return;
        }
        System.out.println("Ban co chac chan muon xoa toan bo san pham(y/n)?");
        String choice = Rangbuoc.rangbuocYesno();
        if (choice.equals("y")) {
            list.clear();
            data.setSanPham(list, fileName);
            System.out.println("XOA TOAN BO SAN PHAM THANH CONG!");
        } else if (choice.equals("n")) {
            System.out.println("Huy xoa toan bo san pham!");
            return;
        } else {
            System.out.println("Lua chon khong hop le!");
        }
    }
    private void suaSanPham(List<? extends SanPham> listSanPham, String fileName) {
        System.out.print("Nhap ma san pham muon sua: ");
        String ma = Rangbuoc.rangbuocMaSanpham();
        SanPham sanPhamCanSua = timSanPhamTheoMa(listSanPham, ma);
        if (sanPhamCanSua != null) {
            System.out.println("Thong tin san pham can sua:");
            xuatBang(sanPhamCanSua);
            System.out.println("Chon thong tin muon sua:");
            System.out.println("1. Ma san pham");
            System.out.println("2. Gia tien");
            System.out.println("3. Loai san pham");
            System.out.println("0. Huy sua");
            System.out.print("Nhap lua chon: ");
            int luaChon = Rangbuoc.rangbuocSo();
            switch (luaChon) {
                case 1:
                    System.out.print("Nhap ma moi: ");
                    String maMoi = Rangbuoc.rangbuocMaSanpham();
                    sanPhamCanSua.setId(maMoi);
                    break;
                case 2:
                    System.out.print("Nhap gia moi: ");
                    double giaMoi = scan.nextDouble();
                    scan.nextLine();
                    sanPhamCanSua.setGiaTien(giaMoi);
                    break;
                case 3:
                    if (sanPhamCanSua instanceof spCaPhe) {
                        spCaPhe spCaPhe = (spCaPhe) sanPhamCanSua;
                        System.out.print("Nhap loai moi: ");
                        String loaiMoi = scan.nextLine();
                        spCaPhe.setLoaiCaphe(loaiMoi);
                    } else if (sanPhamCanSua instanceof spTraSua) {
                        spTraSua spTraSua = (spTraSua) sanPhamCanSua;
                        System.out.print("Nhap loai moi: ");
                        String loaiMoi = scan.nextLine();
                        spTraSua.setLoaiTrasua(loaiMoi);
                    }
                    break;
                case 0:
                    System.out.println("Huy thanh cong!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
                    return;
            }
            data.setSanPham(listSanPham, fileName);
            System.out.println("SUA THONG TIN SAN PHAM THANH CONG!");
        } else {
            System.out.println("KHONG TIM THAY SAN PHAM VOI MA " + ma);
        }
    }
    private SanPham timSanPhamTheoMa(List<? extends SanPham> listSanPham, String ma) {
        for (SanPham sanPham : listSanPham) {
            if (sanPham.getId().equals(ma)) {
                return sanPham;
            }
        }
        return null;
    }
    @Override
    public void xoa() {
        System.out.println("+=============================+");
        System.out.println("|        XOA SAN PHAM         |");
        System.out.println("+-----------------------------+");
        System.out.println("| 1. Ca phe                   |");
        System.out.println("| 2. Tra sua                  |");
        System.out.println("| 0. Thoat                    |");
        System.out.println("+=============================+");
        int choice;
        do {
            System.out.print("Nhap lua chon loai san pham muon xoa: ");
            choice = Rangbuoc.rangbuocSo();
            switch (choice) {
                case 1:
                    xoaSanPham(DSCP, fcaphe);
                    break;
                case 2:
                    xoaSanPham(DSTS, ftrasua);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    private void xoaSanPham(List<? extends SanPham> listSanPham, String fileName) {
        System.out.print("Nhap ma san pham muon xoa: ");
        String ma = Rangbuoc.rangbuocMaSanpham();
        SanPham sanPhamCanXoa = timSanPhamTheoMa(listSanPham, ma);
        if (sanPhamCanXoa != null) {
            xuatBang(sanPhamCanXoa);
            System.out.println("Ban co chac chan muon xoa san pham nay(y/n)?");
            String choice = Rangbuoc.rangbuocYesno();
            if (choice.equals("y")) {
                listSanPham.remove(sanPhamCanXoa);
                data.setSanPham(listSanPham, fileName);
                System.out.println("XOA SAN PHAM THANH CONG!");
            } else if (choice.equals("n")) {
                System.out.println("Huy xoa san pham!");
                return;
            } else {
                System.out.println("Lua chon khong hop le!");
            }
        } else {
            System.out.println("KHONG TIM THAY SAN PHAM VOI MA " + ma);
        }
    }
    @Override
    public void timkiem() {
        System.out.println("+=============================+");
        System.out.println("|       TIM KIEM SAN PHAM     |");
        System.out.println("+-----------------------------+");
        System.out.print("| Nhap ma san pham: ");
        String ma = Rangbuoc.rangbuocMaSanpham();
        System.out.println("+=============================+");
        SanPham sanPhamTimThay = timSanPhamTheoMa(DSCP, ma);
        if (sanPhamTimThay != null) {
            System.out.println("San pham tim thay:");
            xuatBang(sanPhamTimThay);
        } else {
            sanPhamTimThay = timSanPhamTheoMa(DSTS, ma);
            if (sanPhamTimThay != null) {
                System.out.println("San pham tim thay:");
                xuatBang(sanPhamTimThay);
            } else {
                System.out.println("KHONG TIM THAY SAN PHAM VOI MA " + ma);
            }
        }
    }
    @Override
    public void xuat() {
        int choice;
        do {
            System.out.println("");
            System.out.println("+=============================+");
            System.out.println("|        XUAT SAN PHAM        |");
            System.out.println("+-----------------------------+");
            System.out.println("| 1. Ca phe                   |");
            System.out.println("| 2. Tra sua                  |");
            System.out.println("| 0. Thoat                    |");
            System.out.println("+=============================+");
            System.out.print("Nhap lua chon [0-2]: ");
            choice = Rangbuoc.rangbuocSo();
            switch (choice) {
                case 1:
                    xuatSanPham(DSCP);
                    break;
                case 2:
                    xuatSanPham(DSTS);
                    break;
                case 0:
                    System.out.println("Thoat.........");
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }
    private void xuatSanPham(List<? extends SanPham> list) {
        System.out.print("Nhap ma san pham: ");
        String ma = Rangbuoc.rangbuocMaSanpham();
        boolean found = false;
        for (SanPham sp : list) {
            if (sp.getId().equals(ma)) {
                if (sp instanceof spCaPhe) {
                    spCaPhe spCaPhe = (spCaPhe) sp;
                    spCaPhe.xuat();
                } else if (sp instanceof spTraSua) {
                    spTraSua spTraSua = (spTraSua) sp;
                    spTraSua.xuat();
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("KHONG TIM THAY SAN PHAM VOI MA " + ma);
        }
    }
    public void quanlyDSSP() {
        int choice;
        do {
            System.out.println();
            System.out.println("+===================================+");
            System.out.println("|     QUAN LY DANH SACH SAN PHAM    |");
            System.out.println("+-----------------------------------+");
            System.out.println("| 1. Them san pham moi              |");
            System.out.println("| 2. Sua thong tin san pham         |");
            System.out.println("| 3. Xoa san pham moi               |");
            System.out.println("| 4. Tim kiem san pham              |");
            System.out.println("| 5. Xuat san pham                  |");
            System.out.println("| 6. Xuat toan bo san pham          |");
            System.out.println("| 7. Xoa toan bo ca phe             |");
            System.out.println("| 8. Xoa toan bo tra sua            |");
            System.out.println("| 0. Thoat hanh dong                |");
            System.out.println("+===================================+");
            System.out.print("NHAP VAO LUA CHON [0-7]: ");
            choice = Rangbuoc.rangbuocSo();
            switch (choice) {
                case 1:
                    them();
                    break;
                case 2:
                    sua();
                    break;
                case 3:
                    xoa();
                    break;
                case 4:
                    timkiem();
                    break;
                case 5:
                    xuat();
                    break;
                case 6:
                    xuatToanBoSanPham(DSCP);
                    xuatToanBoSanPham(DSTS);
                    break;
                case 7:
                    xoaToanBoSanPham(DSCP, fcaphe);
                    break;
                case 8:
                    xoaToanBoSanPham(DSTS, ftrasua);
                    break;
                case 0:
                    System.out.println("Thoat.........");
                    System.exit(0);
                default:
                    System.out.println("LUA CHON KHONG HOP LE [0-8]!");
            }
        } while (choice != 9);
    }
    public static void main(String[] args) {
        DSSanPham dsSanPham = new DSSanPham();
        dsSanPham.quanlyDSSP();
    }
}
