// package net.nhonam.springboot.Utils.Mapper;

// import net.nhonam.springboot.DTO.RoleDTO;
// import net.nhonam.springboot.Entity.Role;

// public class RoleMapper {

//     private static RoleMapper INSTANCE;

//     public static RoleMapper getInstance() {
//         if (INSTANCE == null) {
//             INSTANCE = new RoleMapper();
//         }

//         return INSTANCE;
//     }

//     public Role toEntity(RoleDTO roleDTO) {
//         Role role = new Role();
//         role.setName(roleDTO.getName());
//         return role;
//     }

//     public RoleDTO toDTO(Role role) {
//         RoleDTO dto = new RoleDTO();
//         dto.setName(role.getName());
//         dto.setId(role.getId());
//         return dto;
//     }
// }
