package net.nhonam.springboot.service;

import java.util.List;
import java.util.stream.Collectors;

import net.nhonam.springboot.Utils.Mapper.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.nhonam.springboot.DTO.UserDTO;
import net.nhonam.springboot.Entity.User;
import net.nhonam.springboot.Utils.Mapper.UserMapper;
import net.nhonam.springboot.repository.IUserRepository;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Boolean checkUserExist(String userName){
        for (User user: getAllUsers()
             ) {
            if(userName.equals(user.getUserName()))
                return true;
        }
        return  false;
        
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
//        if(user.getRole().equals("ADMIN"))
//            user.setRole(RoleEnum.ADMIN);
//        else user.setRole(RoleEnum.EMPLOYEE);
        String passWord = passwordEncoder.encode(user.getPassword());
        user.setPassword(passWord);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
