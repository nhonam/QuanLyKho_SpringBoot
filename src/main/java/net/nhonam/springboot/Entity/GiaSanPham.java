package net.nhonam.springboot.Entity;

import lombok.*;

import javax.persistence.*;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GiaSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_SanPham") // // thông qua khóa ngoại id
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "id_GiaSP") // // thông qua khóa ngoại id
    private Gia gia;
}
