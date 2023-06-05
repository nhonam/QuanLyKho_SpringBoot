//package net.nhonam.springboot.Utils.Mapper;
//
//
//import net.nhonam.springboot.DTO.UserDTO;
//import net.nhonam.springboot.Entity.User;
//
//public class UserMapper {
//
//    private static UserMapper INSTANCE;
//
//    public static UserMapper getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new UserMapper();
//        }
//
//        return INSTANCE;
//    }
//
//    public User toEntity(UserCreationDTO dto) {
//        User user = new User();
//        user.setUserName(dto.getUserName());
//        user.setPassword(dto.getPassword());
//        // user.setRoles(dto.getRoleIds());
//        return user;
//    }
//
//    public UserDTO toDTO(User user) {
//        UserDTO dto = new UserDTO();
//        dto.setUserName(user.getUserName());
//
//        return dto;
//    }
//}
