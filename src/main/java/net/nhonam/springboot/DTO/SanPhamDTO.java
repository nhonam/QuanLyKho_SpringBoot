package net.nhonam.springboot.DTO;

import lombok.Data;

import java.sql.Date;


public interface SanPhamDTO {

     long getId();
    Date getngay_san_xuat();
    String getten_san_pham();

    long gethan_su_dung();
    long getGia();
    Date getngay_bat_dau();
    Date getngay_ket_thuc();

    String getimage_url();
}
