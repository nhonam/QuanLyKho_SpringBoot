package net.nhonam.springboot.Entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(name = "TenSanPham", unique = true)
    private String tenSanPham;

    @Column(name = "NgaySanXuat", unique = true)
    private Date ngaySanXuat;

    @Column(name = "Soluong", unique = true)
    private int soluong;

    @Column(name = "HanSuDung", unique = true)
    private int thang;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL)
    private Collection<ViTriSP> viTriSPs;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL)
    private Collection<PhieuXuatDetail> phieuXuatDetails;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL)
    private Collection<PhieuNhapDetail> phieuNhapDetails;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL)
    private Collection<GiaSanPham> giaSanPhams;


}
