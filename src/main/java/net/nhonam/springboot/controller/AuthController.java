package net.nhonam.springboot.controller;

import net.nhonam.springboot.Entity.User;
import net.nhonam.springboot.Utils.RoleEnum;
import net.nhonam.springboot.config.JwtTokenUtil;
import net.nhonam.springboot.response.ApiResponse;
import net.nhonam.springboot.response.JwtRequest;
import net.nhonam.springboot.response.Response;
import net.nhonam.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public SingletonRes saveUser(@RequestBody User user) throws Exception {
//
//        SingletonRes singleton = SingletonRes.getInstance();
//        System.out.println(singleton);
//        return singleton;
//    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResponse registerUser(@RequestBody User user){
        try {

            if(userService.checkUserExist(user.getUserName()))
                return new ApiResponse(true, "exist", "User đã tồn tại");

            if(user.getRole().equals(RoleEnum.ADMIN))
                user.setRole(RoleEnum.ADMIN);
            else user.setRole(RoleEnum.EMPLOYEE);
            User users = userService.createUser(user);

            return new ApiResponse(true, users, "Tạo nhân viên thành công!");
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }


    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

//        JSONPObject jsonpObject = new JSONPObject();
        Response singleton = Response.getInstance();
        singleton.setData(token);
        singleton.setStatus(HttpStatus.OK);
        singleton.setMessage("đăng nhập thành công");

        return singleton;

    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public Response VerifyToken(@RequestBody String token) throws Exception {

//        JSONPObject jsonpObject = new JSONPObject();
        Response singleton = Response.getInstance();
        System.out.println(jwtTokenUtil.validateTokenData(token).getUserName());
        singleton.setData("jwtTokenUtil.validateTokenData(token)");
        singleton.setStatus(HttpStatus.OK);
        singleton.setMessage("Verify thafnh coong");

        return singleton;

    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
//
//
//        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//
//
//
//        final UserDetails userDetails = userService
//                .loadUserByUsername(authenticationRequest.getUsername());
//
//        final String token = jwtTokenUtil.generateToken(userDetails);
//
//
//
//        return ResponseEntity.ok(new JwtResponse(token));
//    }


    private void authenticate(String username, String password) throws Exception {
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
