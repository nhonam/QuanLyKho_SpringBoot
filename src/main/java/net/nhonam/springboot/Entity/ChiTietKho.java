package net.nhonam.springboot.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChiTietKho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_kho") // // thông qua khóa ngoại id
    private Kho kho;

    @OneToMany(mappedBy = "chiTietKho", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng PhieuNhapKho (1 NHANVIEN co nhiều phiếu nhập kho)
    // MapopedBy trỏ tới private User user ở trong PhieuNhapKho.
    private Collection<ViTriSP> viTriSPS;

    @Column(name = "Cot", unique = true,  length = 2)
    private String cot;

    @Column(name = "Hang", unique = true, length = 2)
    private String hang;

    @Column(name = "Day", unique = true, length = 2)
    private String day;
}
