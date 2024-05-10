package NHANVIEN;

import CHUCNANG.DiaChi;

import java.util.Scanner;

public abstract class NhanVien {
	private String id;
	private String ten;
	private DiaChi dc;
	private String sdt;

	public NhanVien() {
	}

	public NhanVien(String id, String ten) {
		this.id = id;
		this.ten = ten;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public DiaChi getDc() {
		return dc;
	}

	public void setDc(DiaChi dc) {
		this.dc = dc;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String std) {
		this.sdt = std;
	}

//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}

//	public CHUCNANG.NgayThang getBirthDay() {
//		return birthDay;
//	}
//
//	public void setBirthDay(CHUCNANG.NgayThang birthDay) {
//		this.birthDay = birthDay;
//	}

	public void nhapThongTin() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Nhap id: ");
		this.setId(scan.nextLine());
		System.out.print("Nhap ten: ");
		this.setTen(scan.nextLine());
		System.out.print("Nhap dia chi: ");
		DiaChi dc = new DiaChi();
		dc.nhapThongTin();
		this.setDc(dc);
		System.out.print("Nhap so dien thoai: ");
		this.setSdt(scan.nextLine());
		scan.close();
	}

}
