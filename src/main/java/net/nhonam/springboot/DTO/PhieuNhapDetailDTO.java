package net.nhonam.springboot.DTO;

import java.sql.Date;

public interface PhieuNhapDetailDTO {
    long getId();

    int getso_luong();
    String getten_san_pham();
    int getgia();
    long getid_user();
    Date getngay_nhap();

}
