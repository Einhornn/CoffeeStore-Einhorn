package CHUCNANG;

import NHANVIEN.NhanVien;
import SANPHAM.SanPham;
import SANPHAM.spCaPhe;
import SANPHAM.spTraSua;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Rangbuoc {
    public Rangbuoc() {}
    public static Data data = new Data();
    public static List<spCaPhe> DSCP = data.getCaPhe("caphe.txt");
    public static List<spTraSua> DSTS = data.getTraSua("trasua.txt");
    public static Scanner inp = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static Date rangbuocNgayhoadon() {
        while (true) {
            try {
                String ngayHDString = inp.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setLenient(false);
                Date ngayHD = dateFormat.parse(ngayHDString);
                return ngayHD;
            } catch (ParseException e) {
                System.out.println("Dinh dang ngay khong hop le. Vui long nhap theo dinh dang dd/MM/yyyy.");
                System.out.print("Moi nhap lai ngay hoadon: ");
            }
        }
    }

    public static String rangbuocMaNhanvien() {
        String Input;
        while (true) {
            Input = inp.nextLine();
            Input = Input.toUpperCase();
            if (Input.matches("NV" + "[0-9]+")) {
                return Input;
            } else {
                System.out.println("Dinh dang ma nhan vien: NV__. Vi du: NV01");
            }
            System.out.print("Vui nhap lai dung dinh dang: ");

        }
    }
    public static String rangbuocMaHoadon() {
        String Input;
        while (true) {
            Input = inp.nextLine();
            Input = Input.toUpperCase();
            if (Input.matches("HD" + "[0-9]+")) {
                return Input;
            } else {
                System.out.println("Dinh dang ma hoa don: HD__. Vi du: HD01");
            }
            System.out.print("Vui nhap lai dung dinh dang: ");
        }
    }
    public static String rangbuocMaKhachhang() {
        String Input;
        while (true) {
            Input = inp.nextLine();
            Input = Input.toUpperCase();
            if (Input.matches("KH" + "[0-9]+")) {
                return Input;
            } else {
                System.out.println("Dinh dang ma khach hang: KH__. Vi du: KH01");
            }
            System.out.print("Vui nhap lai dung dinh dang: ");

        }
    }
    public static String rangbuocMaSanpham() {
        String Input;
        while (true) {
            Input = inp.nextLine();
            Input = Input.toUpperCase();
            if (Input.matches("SP" + "[0-9]+")) {
                return Input;
            } else {
                System.out.println("Dinh dang ma san pham: SP__. Vi du: SP01");
            }
            System.out.print("Vui nhap lai dung dinh dang: ");

        }
    }
    public static String rangbuocTen() {
        String input;
        while (true) {
            input = inp.nextLine();
            if (input.matches("[^0-9]+") && input.length() > 0) {
                return input;
            } else {
                System.out.println("Ten khong duoc co so. Vui long nhap lai.");
            }
            System.out.print("Vui long nhap ten dung dinh dang: ");
        }
    }
    public static String rangbuocSdt() {
        String Input;
        while (true) {
            Input = inp.nextLine();
            if (Input.matches("0" + "[0-9]{1,9}")) {
                return Input;
            } else {
                System.out.println("So dien thoai gom 10 chu so, dinh dang: 0____. Vi du: 0912345678");
            }
            System.out.print("Vui nhap lai dung dinh dang: ");
        }
    }
    public static int rangbuocSo() {
        while (true) {
            try {
                int choice = inp.nextInt();
                inp.nextLine();
                if (choice >= 0) {
                    return choice;
                } else {
                    System.out.println("Nhap sai! Vui long nhap so nguyen khong am.");
                    System.out.print("Moi nhap lai: ");
                }
            } catch (Exception e) {
                System.out.println("Nhap sai! Vui long nhap so nguyen khong am.");
                System.out.print("Moi nhap lai: ");
                inp.nextLine();
            }
        }
    }
    public static String rangbuocYesno() {
        while (true) {
            String choice = inp.nextLine().trim().toLowerCase();
            if (choice.equals("y") || choice.equals("n")) {
                return choice;
            } else {
                System.out.println("Nhap sai! Vui long nhap y hoac n.");
                System.out.print("Moi nhap lai: ");
            }
        }
    }
    public static long rangbuocTien() {
        while (true) {
            try {
                long input = inp.nextLong();
                inp.nextLine();
                if (input >= 0) {
                    return input;
                } else {
                    System.out.println("Nhap so tien >= 0. ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Nhap sai! Vui long nhap so nguyen khong am.");
                System.out.print("Vui long nhap lai: ");
                inp.nextLine();
            }
        }
    }
    public static void xuatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHoaDon = dateFormat.format(date);
        System.out.println(ngayHoaDon);
    }
    public static String traDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
    public static Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Dinh dang ngay khong hop le. Vui long nhap theo dinh dang dd/MM/yyyy.");
            return null;
        }
    }
    public static boolean tontaiNhanvien(List<NhanVien> list, String ma) {
        for (NhanVien nv : list) {
            if (nv.getId().equals(ma)) {
                return true; // tim thay nhan vien
            }
        }
        return false;
    }
    public static boolean tontaiSanpham(String ma) {
        for (spCaPhe cp : DSCP) {
            if (cp.getId().equals(ma)) {
                return true; //tim thay
            }
        }
        for (spTraSua ts : DSTS) {
            if (ts.getId().equals(ma)) {
                return true;
            }
        }
        return false;
    }
    public static String rangBuocSize() {
        String input;
        while (true) {
            input = inp.nextLine().trim().toUpperCase();
            if (input.equals("S") || !input.equals("M") || !input.equals("L")) {
                return input;
            } else {
                System.out.println("Vui long chon S, M hoac L!");
            }
            System.out.print("Vui long nhap lai dung dinh dang: ");
        }
    }
    public static void clrscr(){
        for (int i = 0; i <= 20; i++) {
            System.out.println("");
        }
    }
}
