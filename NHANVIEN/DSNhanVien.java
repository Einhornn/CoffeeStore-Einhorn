package NHANVIEN;

import java.util.ArrayList;

public class DSNhanVien {
    private ArrayList<NhanVien> dsnv;

    public DSNhanVien(){
        this.dsnv = new ArrayList<NhanVien>();
    }

    public DSNhanVien(ArrayList<NhanVien> dsnv){
        this.dsnv = dsnv;
    }

    public ArrayList<NhanVien> getDSNV(){
        return this.dsnv;
    }
}
