package net.nhonam.springboot.Entity;


import java.util.Collection;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.nhonam.springboot.Utils.ERole;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "Sdt")
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng PhieuNhapKho (1 NHANVIEN co nhiều phiếu nhập kho)
    // MapopedBy trỏ tới private User user ở trong PhieuNhapKho.
    private Collection<PhieuNhapKho> PhieuNhapKhos;

    @Column(name = "Role")
    private ERole Role;

    // @ManyToMany(fetch = FetchType.LAZY)
	// @JoinTable(	name = "user_roles", 
	// 			joinColumns = @JoinColumn(name = "user_id"), 
	// 			inverseJoinColumns = @JoinColumn(name = "role_id"))
	// private Set<Role> roles = new HashSet<>();

  
}
