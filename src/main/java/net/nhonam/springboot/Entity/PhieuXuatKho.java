package net.nhonam.springboot.Entity;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Collection;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhieuXuatKho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_PhieuXuat;

    @Column(name = "NgayXuat")
    private Date NgayXuat;

    @ManyToOne
    @JsonIgnore

    @JoinColumn(name = "user_id") // // thông qua khóa ngoại id
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_Kho") // // thông qua khóa ngoại id
    private Kho kho;

    @OneToMany(mappedBy = "phieuXuatKho", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng PhieuNhapKho (1 NHANVIEN co nhiều phiếu nhập kho)
    // MapopedBy trỏ tới private User user ở trong PhieuNhapKho.
    @JsonIgnore
    private Collection<PhieuXuatDetail> phieuXuatDetails;
    
}
