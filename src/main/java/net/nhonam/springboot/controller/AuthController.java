package net.nhonam.springboot.controller;

import net.nhonam.springboot.Entity.User;
import net.nhonam.springboot.config.JwtTokenUtil;
import net.nhonam.springboot.response.ApiResponse;
import net.nhonam.springboot.response.JwtRequest;
import net.nhonam.springboot.response.JwtResponse;
import net.nhonam.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.createUser(user));
    }


//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ApiResponse registerUser(@RequestBody User user){
//        try {
//
//            if(userService.checkUserExist(user.getUserName()))
//                return new ApiResponse(true, "exist", "User đã tồn tại");
//            User users = userService.createUser(user);
//            return new ApiResponse(true, user, "Tạo nhân viên thành công!");
//        } catch (Exception e) {
//            return new ApiResponse(false, null, e.getMessage());
//        }
//
//
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {


        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());



        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);


        return ResponseEntity.ok(new JwtResponse(token));
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            System.out.println("nam nam nam");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
