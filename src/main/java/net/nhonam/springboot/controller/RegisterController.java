package net.nhonam.springboot.controller;

import net.nhonam.springboot.Entity.User;
import net.nhonam.springboot.response.ApiResponse;
import net.nhonam.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public ApiResponse registerUser(@RequestBody User user){
        try {


//            JwtResponse response = new JwtResponse(token);
            if(userService.checkUserExist(user.getUserName()))
                return new ApiResponse(true, "exist", "User đã tồn tại");
            User users = userService.createUser(user);
            return new ApiResponse(true, user, "Tạo nhân viên thành công!");
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }


    }
}
