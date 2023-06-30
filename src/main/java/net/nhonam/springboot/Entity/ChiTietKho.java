package net.nhonam.springboot.Entity;

//import jakarta.persistence.*;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity // Đánh dấu đây là table trong db
@Table(name = "ChiTietKho")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietKho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kho") // // thông qua khóa ngoại id
    private Kho kho;

    @OneToMany(mappedBy = "chiTietKho", cascade = CascadeType.ALL,fetch = FetchType.LAZY) // Quan hệ 1-n với đối tượng PhieuNhapKho (1 NHANVIEN co nhiều phiếu nhập kho)
    // MapopedBy trỏ tới private User user ở trong PhieuNhapKho.
    private Collection<ViTriSP> viTriSPS;

    @Column(name = "Cot",  length = 2)
    private String cot;

    @Column(name = "Hang", length = 2)
    private String hang;

    @Column(name = "Day",  length = 2)
    private String day;
    @Column(name = "SoLuong")
    private int soLuong;


}
