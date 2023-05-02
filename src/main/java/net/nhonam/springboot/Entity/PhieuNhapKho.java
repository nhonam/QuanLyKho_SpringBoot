package net.nhonam.springboot.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
public class PhieuNhapKho { // person
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_PhieuNhapKho;

    @Column(name = "ngay_nhap",nullable = false)
    private Date ngayNhap;
    @Column(name = "tong_tien")
    private Double tongTien;
    // Many to One có nhiều phieesuu nhập kho do 1 NhanVien tạo
    @ManyToOne
    @JoinColumn(name = "user_id") // // thông qua khóa ngoại id
    private User user;

}
