package SANPHAM;

import CHUCNANG.Rangbuoc;

public class spTraSua extends SanPham {
	public String loaiTrasua;
	public spTraSua() {
		super();
	}

	public spTraSua(String id, String ten, String loaiTrasua, double giaTien) {
		super(id, ten, giaTien);
		this.loaiTrasua = loaiTrasua;
	}

	public String getLoaiTrasua() {
		return loaiTrasua;
	}

	public void setLoaiTrasua(String loaiTrasua) {
		this.loaiTrasua = loaiTrasua;
	}

	@Override
	public void nhapThongTin(){
		super.nhapThongTin();
		this.setTen("Tra sua");
		System.out.print("Nhap gia tra sua: ");
		double gia = Rangbuoc.rangbuocTien();
		this.setGiaTien(gia);
		System.out.print("Nhap loai tra sua: ");
		setLoaiTrasua(scan.nextLine());
	}
	public void xuat() {
		System.out.printf("%-2s %-9s %-2s %-9s %-2s %-20s %-2s %-8s %-2s", "|", getId(), "|", getTen(), "|", getLoaiTrasua(), "|", getGiaTien(), "|");
	}
}
