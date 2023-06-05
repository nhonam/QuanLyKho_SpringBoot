package net.nhonam.springboot.Entity;


import lombok.*;

import javax.persistence.*;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhieuXuatDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "SoLuong")
    private int soLuong;

    @ManyToOne
    @JoinColumn(name = "id_PhieuXuatKho") // // thông qua khóa ngoại id
    private PhieuXuatKho phieuXuatKho;

    @ManyToOne
    @JoinColumn(name = "id_SanPham") // // thông qua khóa ngoại id
    private SanPham sanPham;


}
