package DATHANG;

import CHUCNANG.Data;
import SANPHAM.spCaPhe;
import SANPHAM.spTraSua;
import java.util.List;
public class ThucDon {
	public List<spCaPhe> DSCP;
    public List<spTraSua> DSTS;
    String fcaphe = "caphe.txt";
    String ftrasua = "trasua.txt";
    public Data data;
	public ThucDon() {
        data = new Data();
        DSCP = data.getCaPhe(fcaphe);
        DSTS = data.getTraSua(ftrasua);
    }
    public void xuatMenu() {
        System.out.println("");
        System.out.println("+========================================================================================+");
        System.out.println("|                                  TIEM NUOC CHU KY LAN                                  |");
        System.out.println("+========================================================================================+");
        System.out.println("|                                        CA PHE                                          |");
        System.out.println("+----------------------------------------------------------------------------------------+");
        System.out.println("|  STT  |    Ten    |           Loai ca phe          |     S     |     M     |     L     |");
        System.out.println("+----------------------------------------------------------------------------------------+");
        int stt = 1;
        for (spCaPhe cp : DSCP) {
            System.out.printf("|  %-4d | %-9s | %-30s | %-9s | %-9s | %-9s |\n", stt++, cp.getTen(), cp.getLoaiCaphe(), (cp.getGiaTien() - cp.getGiaTien() * 0.1), cp.getGiaTien(), (cp.getGiaTien() + cp.getGiaTien() * 0.15));
        }
        System.out.println("+========================================================================================+");
        System.out.println("|                                       TRA SUA                                          |");
        System.out.println("+----------------------------------------------------------------------------------------+");
        System.out.println("|  STT  |    Ten    |           Loai tra sua         |     S     |     M     |     L     |");
        System.out.println("+----------------------------------------------------------------------------------------+");
        stt = 1;
        for (spTraSua ts : DSTS) {
            System.out.printf("|  %-4d | %-9s | %-30s | %-9s | %-9s | %-9s |\n", stt++, ts.getTen(), ts.getLoaiTrasua(), (ts.getGiaTien() - ts.getGiaTien() * 0.1), ts.getGiaTien(), (ts.getGiaTien() + ts.getGiaTien() * 0.15));
        }
        System.out.println("+========================================================================================+");
        System.out.println("");
    }

    public static void main(String[] args) {
        ThucDon thucDon = new ThucDon();
        thucDon.xuatMenu();
    }
}
