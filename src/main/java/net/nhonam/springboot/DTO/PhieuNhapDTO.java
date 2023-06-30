package net.nhonam.springboot.DTO;

import java.sql.Date;

public interface PhieuNhapDTO {
    long getid_phieunhap();
    long getid_user();
    long getid_nha_cung_cap();
    String getten_nhacungcap();

    String getfirst_name();
    String getlast_name();
}
