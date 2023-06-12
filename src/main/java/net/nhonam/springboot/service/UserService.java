package net.nhonam.springboot.service;

import java.util.Collection;
import java.util.List;

import net.nhonam.springboot.Utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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

//    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByuserName(username);
//        System.out.println(user.getId()+"nam nè ");
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        UserDetails userDetails = new UserDetails() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUserName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }


        };
        return userDetails;
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByuserName(username);
//        return (UserDetails) user;
//    }
}
