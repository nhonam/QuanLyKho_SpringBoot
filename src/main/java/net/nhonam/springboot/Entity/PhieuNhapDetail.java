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
public class PhieuNhapDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
        

    @ManyToOne
    @JoinColumn(name = "id_PhieuNhapKho") // // thông qua khóa ngoại id
    private PhieuNhapKho phieuNhapKho;

    @ManyToOne
    @JoinColumn(name = "id_SanPham") // // thông qua khóa ngoại id
    private SanPham sanPham;




}
