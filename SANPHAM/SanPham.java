package SANPHAM;

import CHUCNANG.Rangbuoc;

import java.util.Scanner;
//không dùng abstract khi không cần
public class SanPham {
//	mã sp nên theo 1 quy chế SP__ k có nhiều loại mã, để tiện cho ràng buộc và tìm kiếm
	private String id;
	private String ten;
	private double giaTien;

//	public static int maxLength = 0;
////	public static int validSize = 3;
//	public static String size[] = { "S", "M", "L", "XL", "XXL" };

//	chỉ cần import Scanner và gọi Scanner ở lớp cha thì lớp con auto có Scanner
	public static Scanner scan = new Scanner(System.in);

	public SanPham() {
	}

	public SanPham(String id, String ten) {
		this.id = id;
		this.ten = ten;
	}

	public SanPham(String id, String ten, double giaTien) {
		this.id = id;
		this.ten = ten;
		this.giaTien = giaTien;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}
	public void nhapThongTin(){
		System.out.print("Nhap ma san pham (dinh dang SP__): ");
		String idSP = Rangbuoc.rangbuocMaSanpham();
		this.setId(idSP);
	}
}
