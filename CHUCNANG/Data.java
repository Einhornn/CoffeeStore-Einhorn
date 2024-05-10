package CHUCNANG;

import HOADON.Hoadon;
import HOADON.CTHD;
import SANPHAM.spCaPhe;
import SANPHAM.SanPham;
import SANPHAM.spTraSua;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class Data {
    public Data() {
    }
    public void setCaPhe(List<spCaPhe> dscp, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            for (spCaPhe spCaPhe : dscp) {
                    writer.println(spCaPhe.getTen() + "/" + spCaPhe.getId() + "/" + spCaPhe.getLoaiCaphe() + "/" + spCaPhe.getGiaTien());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    public void setTraSua(List<spTraSua> dsts, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            for (spTraSua spTraSua : dsts) {
                writer.println(spTraSua.getTen() + "/" + spTraSua.getId() + "/" + spTraSua.getLoaiTrasua() + "/" + spTraSua.getGiaTien());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    public void setSanPham(List<? extends SanPham> danhSachSanPham, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            for (SanPham sanPham : danhSachSanPham) {
                if (sanPham instanceof spCaPhe spCaPhe) {
                    writer.println(spCaPhe.getTen() + "/" + spCaPhe.getId() + "/" + spCaPhe.getLoaiCaphe() + "/" + spCaPhe.getGiaTien());
                } else if (sanPham instanceof spTraSua) {
                    spTraSua spTraSua = (spTraSua) sanPham;
                    writer.println(spTraSua.getTen() + "/" + spTraSua.getId() + "/" + spTraSua.getLoaiTrasua() + "/" + spTraSua.getGiaTien());
                }
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    public List<spCaPhe> getCaPhe(String fileName) {
        List<spCaPhe> dscp = new ArrayList<>();
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File không tồn tại: " + fileName);
                return dscp;
            }
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] fields = data.split("/");
                if (fields.length >= 4) {
                    String loai = fields[0];
                    String id = fields[1];
                    String ten = fields[0];
                    String loaiCaphe = fields[2];
                    double giaTien = Double.parseDouble(fields[3]);
                    spCaPhe spCaPhe = new spCaPhe();
                    spCaPhe.setId(id);
                    spCaPhe.setTen(ten);
                    spCaPhe.setLoaiCaphe(loaiCaphe);
                    spCaPhe.setGiaTien(giaTien);
                    dscp.add(spCaPhe);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return dscp;
    }

    public List<spTraSua> getTraSua(String fileName) {
        List<spTraSua> dsts = new ArrayList<>();
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File không tồn tại: " + fileName);
                return dsts;
            }
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] fields = data.split("/");
                if (fields.length >= 4) {
                    String loai = fields[0];
                    String id = fields[1];
                    String ten = fields[0];
                    String loaiTraSua = fields[2];
                    double giaTien = Double.parseDouble(fields[3]);
                    spTraSua spTraSua = new spTraSua();
                    spTraSua.setId(id);
                    spTraSua.setTen(ten);
                    spTraSua.setLoaiTrasua(loaiTraSua);
                    spTraSua.setGiaTien(giaTien);
                    dsts.add(spTraSua);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return dsts;
    }
    public void setHoaDon(List<Hoadon> dshd, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            for (Hoadon hoadon : dshd) {
                String formattedDate = Rangbuoc.traDate(hoadon.getNgayHoadon());
                writer.println(hoadon.getMaHoadon() + "|" + hoadon.getMaNhanvien() + "|" + formattedDate + "|" + hoadon.getTienHoadon());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu dữ liệu vào tệp " + filename + ": " + e.getMessage());
        }
    }
    public List<Hoadon> getHoaDon(String fileName) {
        List<Hoadon> dshd = new ArrayList<>();
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File không tồn tại: " + fileName);
                return dshd;
            }
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] fields = data.split("\\|");
                if (fields.length >= 4) {
                    String maHoadon = fields[0];
                    String maNhanvien = fields[1];
                    Date ngayHoadon = Rangbuoc.parseDate(fields[2]);
                    double tienHoadon = Double.parseDouble(fields[3]);
                    Hoadon hoadon = new Hoadon(maHoadon, maNhanvien, ngayHoadon, tienHoadon);
                    dshd.add(hoadon);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return dshd;
    }
    public void setCTHD(List<CTHD> dscthd, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            for (CTHD cthd : dscthd) {
                writer.println(cthd.getMaChitetHoadon() + "," + cthd.getId() + "," +
                        cthd.getSize() + "," + cthd.getSoluongSanpham() + "," + cthd.getDonGia());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu dữ liệu vào tệp " + filename + ": " + e.getMessage());
        }
    }
    public List<CTHD> getCTHD(String fileName) {
        List<CTHD> dscthd = new ArrayList<>();
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File không tồn tại: " + fileName);
                return dscthd;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 5) {
                    String maHoadon = fields[0];
                    String id = fields[1];
                    String size = fields[2];
                    int soluongSanpham = Integer.parseInt(fields[3]);
                    double donGia = Double.parseDouble(fields[4]);
                    CTHD cthd = new CTHD(maHoadon, id, soluongSanpham, size, donGia);
                    cthd.setSize(size);
                    dscthd.add(cthd);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc dữ liệu từ tệp " + fileName + ": " + e.getMessage());
        }
        return dscthd;
    }
}
