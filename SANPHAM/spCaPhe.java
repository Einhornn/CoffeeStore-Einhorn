package SANPHAM;
import CHUCNANG.Rangbuoc;

public class spCaPhe extends SanPham {
//	private boolean coDa;
//	public CaPhe(String id, String ten) {
//		super(id, ten);
//	}
//	public CaPhe(boolean coDa) {
//		super();
//		this.coDa = coDa;
//	}
//	public boolean isIced() {
//		return coDa;
//	}
//
//	public void setIced(boolean isIced) {
//		this.coDa = isIced;
//	}
//	thông tin này dùng để lưu vào kho chứ k phải menu nên chắc k nên dùng có đá
//	thay vào đó nên là như này
	public String loaiCaphe;
	public spCaPhe() {
	}

	public spCaPhe(String id, String ten, String loaiCaphe, double giaTien) {
		super(id, ten, giaTien);
		this.loaiCaphe = loaiCaphe;
	}

	public String getLoaiCaphe() {
		return loaiCaphe;
	}

	public void setLoaiCaphe(String loaiCaphe) {
		this.loaiCaphe = loaiCaphe;
	}

	@Override
	public void nhapThongTin(){
		super.nhapThongTin();
		this.setTen("Ca phe");
		System.out.print("Nhap gia ca phe: ");
		double gia = Rangbuoc.rangbuocTien();
		this.setGiaTien(gia);
		System.out.print("Nhap loai ca phe: ");
		setLoaiCaphe(scan.nextLine());
	}
	public void xuat() {
		System.out.printf("%-2s %-9s %-2s %-9s %-2s %-20s %-2s %-8s %-2s", "|", getId(), "|", getTen(), "|", getLoaiCaphe(), "|", getGiaTien(), "|");
	}
}
