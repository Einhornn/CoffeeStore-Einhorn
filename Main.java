import CHUCNANG.Rangbuoc;
import DATHANG.DatHang;
import HOADON.DSHoaDon;
import NHANVIEN.DSNhanVien;
import SANPHAM.DSSanPham;
import java.util.Scanner;

public class Main {
    DatHang datHang = new DatHang();
    DSSanPham sanPham = new DSSanPham();
    DSHoaDon hoaDon = new DSHoaDon();
    DSNhanVien nhanVien = new DSNhanVien();
    public void menu() {
        Scanner scan = new Scanner(System.in);
        int choose;
        System.out.println("+========================================+");
        System.out.println("|         EINHORN COFFE - TEA SHOP       |");
        System.out.println("+----------------------------------------+");
        System.out.println("|                 DANG NHAP              |");
        System.out.println("+----------------------------------------+");
        System.out.print("| Username: ");
        String name = Rangbuoc.rangbuocTen();
        System.out.print("| Password: ");
        String pass = scan.nextLine();
        while (name.equals("admin") && pass.equals("123456")) {
            do {
                System.out.println("+----------------------------------------+");
                System.out.println("|            QUAN LY BAN CA PHE          |");
                System.out.println("+----------------------------------------+");
                System.out.println("|    1. Dat hang                         |");
                System.out.println("|    2. Thao tac voi san pham            |");
                System.out.println("|    3. Thao tac voi hoa don             |");
                System.out.println("|    4. Thao tac voi nhan vien           |");
                System.out.println("|    5. Thong ke                         |");
//                System.out.println("|    6. Xoa toan bo hoa don              |");
//                System.out.println("|    7. Sua thong tin hoa don            |");
                System.out.println("|    0. Ket thuc                         |");
                System.out.println("+----------------------------------------+");
                System.out.println("|       CHON MOT THAO TAC THUC HIEN      |");
                System.out.println("+----------------------------------------+");
                choose = Rangbuoc.rangbuocSo();
                switch (choose) {
                    case 1:
                        datHang.quanlyDathang();
                        break;
                    case 2:
                        sanPham.quanlyDSSP();
                        break;
                    case 3:
                        hoaDon.quanlyDanhsachHoadon();
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
//                case 6:
//                    break;
//                case 7:
//                    break;
                    case 0:
                        System.exit(0);
                        System.out.println("============== CHAO TAM BIET =============");
                }
            }
            while (choose != 0) ;
        }
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }
}
