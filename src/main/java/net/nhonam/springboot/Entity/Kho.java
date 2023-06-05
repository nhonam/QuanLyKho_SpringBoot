package net.nhonam.springboot.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Kho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TenKho")
    private String tenKho;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "Sdt", length = 12, unique = true)
    private String SDT;

    @OneToMany(mappedBy = "kho", cascade = CascadeType.ALL)
    private Collection<ChiTietKho> chiTietKhos;

    @OneToMany(mappedBy = "kho", cascade = CascadeType.ALL)
    private Collection<PhieuXuatKho> phieuXuatKhos;

    @OneToMany(mappedBy = "kho", cascade = CascadeType.ALL)
    private Collection<PhieuNhapKho> phieuNhapKhos;

}
