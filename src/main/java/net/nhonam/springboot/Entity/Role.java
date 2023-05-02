// package net.nhonam.springboot.Entity;

// import java.util.List;

// import jakarta.persistence.*;
// import net.nhonam.springboot.Utils.ERole;

// @Entity
// @Table(name = "roles")
// public class Role {
// 	@Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private Long id;

// 	@Enumerated(EnumType.STRING)
// 	@Column(length = 20)
// 	private ERole name;

// 	@ManyToMany(mappedBy="roles") //roles in User.java
//     private List<User> users;

// 	public Role() {

// 	}

// 	public Role(ERole name) {
// 		this.name = name;
// 	}

// 	public Long getId() {
// 		return id;
// 	}

// 	public void setId(Long id) {
// 		this.id = id;
// 	}

// 	public ERole getName() {
// 		return name;
// 	}

// 	public void setName(ERole name) {
// 		this.name = name;
// 	}
// }
