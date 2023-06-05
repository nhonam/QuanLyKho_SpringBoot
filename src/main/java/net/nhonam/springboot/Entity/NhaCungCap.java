package net.nhonam.springboot.Entity;

import java.util.Collection;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NhaCungCap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(name = "ten_nhacungcap")
    private String tenNhaCungCap;
    @NotNull
    @Column(name = "dia_chi")
    private String diaChi;
    @NotNull
    @Column(name = "sdt")
    private String sdt;
    @Email(message = "vui long nhap dung dinh dang email")
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "nhaCungCap", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng PhieuNhapKho (1 NHANVIEN co nhiều phiếu nhập kho)
    // MapopedBy trỏ tới private User user ở trong PhieuNhapKho.
    private Collection<PhieuNhapKho> PhieuNhapKhos;
    
}
