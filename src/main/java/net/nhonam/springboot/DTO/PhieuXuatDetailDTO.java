package net.nhonam.springboot.DTO;

import java.sql.Date;

public interface PhieuXuatDetailDTO {

    long getid_phieuxuat();

    Date getngay_xuat();

    long getid_user();

    long getso_luong();

    String getten_san_pham();

    int getgia();

}
