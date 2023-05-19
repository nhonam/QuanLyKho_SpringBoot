package net.nhonam.springboot.Entity;


import java.util.Collection;

import jakarta.persistence.*;
import lombok.*;
import net.nhonam.springboot.Utils.Mapper.RoleEnum;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "password",nullable = false)
	private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "user_name",nullable = false, unique = true)
    private String userName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "Sdt", length = 12, unique = true)
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng PhieuNhapKho (1 NHANVIEN co nhiều phiếu nhập kho)
    // MapopedBy trỏ tới private User user ở trong PhieuNhapKho.
    private Collection<PhieuNhapKho> PhieuNhapKhos;


    @Column(name = "Role",nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum Role;

    @Column(name = "status")
    private Boolean status;


    
  
}
