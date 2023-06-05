package net.nhonam.springboot.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Kho {
    public static List<Kho> getTenKho;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull (message = "Can phai nhap ten")
    @Column(name = "TenKho")
    private String tenKho;
    @NotNull
    @Column(name = "DiaChi")
    private String diaChi;
    @NotNull
    @Column(name = "Sdt", length = 12, unique = true)
    private String SDT;

    @OneToMany(mappedBy = "kho", cascade = CascadeType.ALL)
    private Collection<ChiTietKho> chiTietKhos;

    @OneToMany(mappedBy = "kho", cascade = CascadeType.ALL)
    private Collection<PhieuXuatKho> phieuXuatKhos;

    @OneToMany(mappedBy = "kho", cascade = CascadeType.ALL)
    private Collection<PhieuNhapKho> phieuNhapKhos;

}
