package net.nhonam.springboot.Entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Gia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Gia", unique = true)
    private int gia;

    @Column(name = "NgayBatDau", unique = true)
    private Date ngayBatDau;

    @Column(name = "NgayKetThuc", unique = true)
    private Date ngayKetThuc;

    @OneToMany(mappedBy = "gia", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng PhieuNhapKho (1 NHANVIEN co nhiều phiếu nhập kho)
    // MapopedBy trỏ tới private User user ở trong PhieuNhapKho.
    private Collection<GiaSanPham> giaSanPhams;


}
