package DATHANG;
import CHUCNANG.Data;
import CHUCNANG.Rangbuoc;
import HOADON.DSHoaDon;
import HOADON.Hoadon;
import SANPHAM.spCaPhe;
import SANPHAM.spTraSua;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class DatHang {
	public List<DSDatHang> DSDH;
	public ThucDon thucdon;
	public List<spCaPhe> DSCP;
	public List<spTraSua> DSTS;
	private Data data;
	private String fcaphe = "caphe.txt";
	private String ftrasua = "trasua.txt";
	public DatHang() {
		thucdon = new ThucDon();
		data = new Data();
		DSDH = new ArrayList<>();
		DSCP = data.getCaPhe(fcaphe);
		DSTS = data.getTraSua(ftrasua);
	}
	public void xuatDonHang() {
		Rangbuoc.clrscr();
		System.out.println("+================================================================================+");
		System.out.println("|                                THONG TIN DON HANG                              |");
		System.out.println("+--------------------------------------------------------------------------------+");
		System.out.println("|   Ten   |  Loai san pham   |  Size  |  So luong  |    Don gia   |  Thanh tien  |");
		System.out.println("+--------------------------------------------------------------------------------+");
		for (DSDatHang mathang : DSDH) {
			xuat(mathang);
		}
		System.out.println("+================================================================================+");
		System.out.println();
		System.out.println("Ban muon thay doi gi trong don hang ?");
		System.out.println("1. Thay doi so luong mon hang");
		System.out.println("2. Xoa mon hang");
		System.out.println("0. Thoat");
		System.out.print("Lua chon cua ban: ");
		int luaChon = Rangbuoc.rangbuocSo();
		switch (luaChon) {
			case 1:
				break;
			case 2:
				break;
			case 0:
				return;
			default:
				System.out.println("Lua chon khong hop le!");
		}
	}
	public void xuat(DSDatHang matHang) {
		System.out.printf("| %-9s %-20s %-9s %-10d %-14.2f %-12.2f|\n", matHang.getTenMatHang(), matHang.getLoaiMatHang(), matHang.getKichThuoc(),
				matHang.getSoLuong(), matHang.getDonGia(), matHang.getThanhTien());
	}
	public double sizeS(double tien) {
		return tien - tien * 0.1;
	}
	public double sizeL(double tien) {
		return tien + tien * 0.15;
	}
	public void nhanDonHang() {
		Scanner scan = new Scanner(System.in);
		while (true) {
			Rangbuoc.clrscr();
			thucdon.xuatMenu();
			System.out.println("+=============================+");
			System.out.println("|           DAT HANG          |");
			System.out.println("+-----------------------------+");
			System.out.println("| 1. Ca phe                   |");
			System.out.println("| 2. Tra sua                  |");
			System.out.println("| 0. Thoat                    |");
			System.out.println("+-----------------------------+");
			System.out.println("|       VUI LONG LUA CHON     |");
			System.out.println("+=============================+");
			System.out.print("Moi nhap lua chon: ");
			int choice = Rangbuoc.rangbuocSo();
			if (choice == 0) break;
			String tenMon = "";
			String loaiMon = "";
			double donGia = 0;
			String kichThuoc = "";
			double gia = 0;
			if (choice == 1) {
				System.out.print("Moi chon mon: ");
				int vitriMon = Rangbuoc.rangbuocSo();
				while (vitriMon < 1 || vitriMon > DSCP.size()) {
					System.out.println("Vui long nhap lai!");
					System.out.print("Moi chon mon: ");
					vitriMon = Rangbuoc.rangbuocSo();
				}
				spCaPhe caPhe = DSCP.get(vitriMon - 1);
				tenMon = "Ca phe";
				loaiMon = caPhe.getLoaiCaphe();
				System.out.println("Vui long chon size: ");
				System.out.println("1. S");
				System.out.println("2. M");
				System.out.println("3. L");
				int sizeChoice = Rangbuoc.rangbuocSo();
				while (sizeChoice < 1 || sizeChoice > 3) {
					System.out.println("Vui long nhap lai!");
					System.out.print("Moi chon size: ");
					sizeChoice = Rangbuoc.rangbuocSo();
				}
				if (sizeChoice == 1) {
					kichThuoc = "S";
					donGia = gia = sizeS(caPhe.getGiaTien());
				} else if (sizeChoice == 2) {
					kichThuoc = "M";
					donGia = caPhe.getGiaTien();
					gia = caPhe.getGiaTien();
				} else {
					donGia = gia = sizeL(caPhe.getGiaTien());
					kichThuoc = "L";
				}
				boolean found = false;
				for (DSDatHang dh : DSDH) {
					if (dh.getTenMatHang().equals(tenMon) && dh.getLoaiMatHang().equals(loaiMon) && dh.getKichThuoc().equals(kichThuoc)) {
						// nếu trùng thì up số lượng lên
						dh.setSoLuong(dh.getSoLuong() + 1);
						dh.setThanhTien(dh.getThanhTien() + gia);
						found = true;
						break;
					}
				}
				if (!found) {
					// sp mà chưa có thì add vô
					DSDatHang dsdh = new DSDatHang(tenMon, loaiMon, kichThuoc, 1, donGia, gia);
					DSDH.add(dsdh);
				}
			}
			else if (choice == 2) {
				System.out.print("Moi chon mon: ");
				int vitriMon = Rangbuoc.rangbuocSo();
				while (vitriMon < 1 || vitriMon > DSTS.size()) {
					System.out.println("Vui long nhap lai!");
					System.out.print("Moi chon mon: ");
					vitriMon = Rangbuoc.rangbuocSo();
				}
				spTraSua traSua = DSTS.get(vitriMon - 1);
				loaiMon = traSua.getLoaiTrasua();
				tenMon = "Tra sua";
				System.out.println("Vui long chon size: ");
				System.out.println("1. S");
				System.out.println("2. M");
				System.out.println("3. L");
				int sizeChoice = Rangbuoc.rangbuocSo();
				while (sizeChoice < 1 || sizeChoice > 3) {
					System.out.println("Vui long nhap lai!");
					System.out.print("Moi chon size: ");
					sizeChoice = Rangbuoc.rangbuocSo();
				}
				if (sizeChoice == 1) {
					kichThuoc = "S";
					donGia = gia = sizeS(traSua.getGiaTien());
				} else if (sizeChoice == 2) {
					kichThuoc = "M";
					donGia = traSua.getGiaTien();
					gia = traSua.getGiaTien();
				} else {
					kichThuoc = "L";
					donGia = gia = sizeL(traSua.getGiaTien());
				}
				boolean found = false;
				for (DSDatHang dh : DSDH) {
					if (dh.getTenMatHang().equals(tenMon) && dh.getLoaiMatHang().equals(loaiMon) && dh.getKichThuoc().equals(kichThuoc)) {
						dh.setSoLuong(dh.getSoLuong() + 1);
						dh.setThanhTien(dh.getThanhTien() + gia);
						found = true;
						break;
					}
				}
				if (!found) {
					DSDatHang dsdh = new DSDatHang(tenMon, loaiMon, kichThuoc, 1, donGia, gia);
					DSDH.add(dsdh);
				}
			}
			else {
				System.out.println("Lua chon khong hop le!");
			}
		}
		System.out.println("Ban co muon chon nua khong ?");
		System.out.println("1. Co");
		System.out.println("2. Khong");
		int chonTiep = Rangbuoc.rangbuocSo();
		while (chonTiep < 1 || chonTiep > 2) {
			System.out.println("Vui long nhap lai!");
			System.out.println("Ban co muon chon nua khong ?");
			System.out.println("1. Co");
			System.out.println("2. Khong");
			chonTiep = Rangbuoc.rangbuocSo();
		}
		this.xuatDonHang();
		scan.close();
	}
	public void quanlyDathang() {
		DatHang nvDatHang = new DatHang();
		nvDatHang.nhanDonHang();
	}
}
