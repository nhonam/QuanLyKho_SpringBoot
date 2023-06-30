package net.nhonam.springboot.DTO;

import java.sql.Date;

public interface PhieuXuatDTO {

    long getid_phieu_xuat();
    long getid_employee();
    Date getngay_xuat();

    String getfirst_name();

    String getlast_name();
}
