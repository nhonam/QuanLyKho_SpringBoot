package net.nhonam.springboot.Entity;

import java.sql.Date;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhieuNhapKho { // person
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ngay_nhap",nullable = false)
    private Date ngayNhap;

    // Many to One có nhiều phieesuu nhập kho do 1 NhanVien tạo
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_User") // // thông qua khóa ngoại id
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_NhaCungCap") // // thông qua khóa ngoại id
    private NhaCungCap nhaCungCap;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_Kho") // // thông qua khóa ngoại id
    private Kho kho;

    @OneToMany(mappedBy = "phieuNhapKho", cascade = CascadeType.ALL,
            orphanRemoval = true) // Quan hệ 1-n với đối tượng PhieuNhapKho (1 NHANVIEN co nhiều phiếu nhập kho)
    // MapopedBy trỏ tới private User user ở trong PhieuNhapKho.
    @JsonIgnore
    private Collection<PhieuNhapDetail> phieuNhapDetails;

}
