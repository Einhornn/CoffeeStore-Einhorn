package NHANVIEN;

import java.util.Scanner;

public class nvQuanLi extends NhanVien {
	private DSNhanVien dsNhanVien;

	public void themNhanVien(){
		Scanner scan = new Scanner(System.in);
		NhanVien nv = null;
		nv.nhapThongTin();
		System.out.println("Nhap kieu nhan vien can them: ");
		System.out.println("1. Nhan vien quan li");
		System.out.println("2. Nhan vien pha che");
		System.out.println("3. Nhan vien dat hang");
		System.out.print("Moi nhap lua chon: ");
		int LuaChon = Integer.parseInt(scan.nextLine());
		while (LuaChon < 1 || LuaChon > 3){
			System.out.println("Lua chon khong hop li, vui long nhap lai:");
			System.out.print("Moi nhap lua chon: ");
			LuaChon = Integer.parseInt(scan.nextLine());
		}
		switch (LuaChon) {
			case 1:
				nv = new nvQuanLi();
				break;
			case 2:
				nv = new nvBanHang();
				break;
			case 3:
				nv = new nvBanHang();
				break;
		}
		this.dsNhanVien.getDSNV().add(nv);
		scan.close();
	}
}
