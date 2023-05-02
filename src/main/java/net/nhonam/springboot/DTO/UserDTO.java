package net.nhonam.springboot.DTO;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable{
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<RoleDTO> roles;
}
