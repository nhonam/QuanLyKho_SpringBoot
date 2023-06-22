package net.nhonam.springboot.controller;

import io.jsonwebtoken.ExpiredJwtException;
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
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@RestController()
@CrossOrigin
@RequestMapping("/auth")
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

            return new ApiResponse(true, users, "Đăng ký tài khoản thành công!");
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }


    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ApiResponse login() throws Exception {
//
//
//        return new ApiResponse(false, null, "e.getMessage()");
//
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResponse login(@RequestBody JwtRequest authenticationRequest, HttpStatus httpStatus) throws Exception {
        try {
            System.out.println(authenticationRequest.getUsername() +"-------");
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
//        User user = jwtTokenUtil.validateTokenUser(token);

            return new ApiResponse(true, token, "Đăng nhập thành công!");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"đăng nhập thất bại");
//            System.out.println("aaa");
//            return new ApiResponse(false, null, e.getMessage());
        }


    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public Response createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
//
//        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//        final UserDetails userDetails = userService
//                .loadUserByUsername(authenticationRequest.getUsername());
//
//        final String token = jwtTokenUtil.generateToken(userDetails);
//
////        JSONPObject jsonpObject = new JSONPObject();
//        Response singleton = Response.getInstance();
//        singleton.setData(token);
//        singleton.setStatus(HttpStatus.OK);
//        singleton.setMessage("đăng nhập thành công");
//
//        return singleton;
//
//    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public ApiResponse VerifyToken( HttpServletRequest request, HttpStatus httpStatus) throws Exception {
        Response response = Response.getInstance();
        final String requestTokenHeader = request.getHeader("Authorization");
//        if (requestTokenHeader== null) {
//            return new ApiResponse(false, requestTokenHeader, "verify thất bại token null");
//        }

        if ( requestTokenHeader.contains(".")) {
            String jwtToken = null;
            String username = null;
            if (requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.substring(7);
                try {
                    User user = jwtTokenUtil.validateTokenUser(jwtToken);
                    System.out.println("hehe");
                    return new ApiResponse(true, user, "verify thành công");

                } catch (IllegalArgumentException e) {
                    System.out.println("Unable to get JWT Token");
                    return new ApiResponse(true, null, "Unable to get JWT Token");
                } catch (ExpiredJwtException e) {
                    System.out.println("JWT Token has expired");
                    return new ApiResponse(true, null, "JWT Token đã hết hạn");

                }


            }

        };
        return new ApiResponse(false, null, "verify thất bại token null");


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
            throw new Exception("tên tài khoản hoặc mật khẩu không chính xác", e);
        }
    }
}
