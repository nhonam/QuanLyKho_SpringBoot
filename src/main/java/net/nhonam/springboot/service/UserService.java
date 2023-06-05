package net.nhonam.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.nhonam.springboot.Entity.User;
import net.nhonam.springboot.repository.IUserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByuserName(username);
//        System.out.println(user.getId()+"nam nè ");
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                new ArrayList<>());
    }
}
