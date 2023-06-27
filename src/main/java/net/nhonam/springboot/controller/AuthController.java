package net.nhonam.springboot.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import io.jsonwebtoken.ExpiredJwtException;
import net.nhonam.springboot.Entity.User;
import net.nhonam.springboot.Utils.RoleEnum;
import net.nhonam.springboot.config.JwtTokenUtil;
import net.nhonam.springboot.response.JwtRequest;
import net.nhonam.springboot.response.Response;
import net.nhonam.springboot.response.ResponseSingleton;
import net.nhonam.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController()
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    ResponseSingleton responseHandler = ResponseSingleton.getInstance();

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;




    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public  ResponseEntity<Object> registerUser(@Valid @RequestBody User user){
        ResponseSingleton responseHandler = ResponseSingleton.getInstance();
        try {
            if(userService.checkUserExist(user.getUserName()))
            return responseHandler.generateResponse("User exist!", HttpStatus.OK, null);

            if(user.getRole().equals(RoleEnum.ADMIN))
                user.setRole(RoleEnum.ADMIN);
            else user.setRole(RoleEnum.EMPLOYEE);
            user.setStatus(true);
            User users = userService.createUser(user);
            return responseHandler.generateResponse("Register Successfully !", HttpStatus.OK, users);

        } catch (Exception e) {
            return responseHandler.generateResponse("Register Failure !" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }


    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestBody JwtRequest authenticationRequest, HttpStatus httpStatus) throws Exception {

        ResponseSingleton responseHandler = ResponseSingleton.getInstance();

        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

            return responseHandler.generateResponse("login successfully !", HttpStatus.OK, token);
        } catch (Exception e) {
            return responseHandler.generateResponse("username or password invalid", HttpStatus.BAD_REQUEST, null);


        }


    }
//    @Autowired
//    private CloudinaryService cloudinaryGifService;

//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadGif(@RequestParam("image")
//                                                                   MultipartFile gifFile) throws IOException {
//        // User currentUser =
//// userService.findUserByEmail(authentication.getName()); // Authorization
//        String url = cloudinaryGifService.uploadFile(gifFile);
////        cloudinaryGifService.saveGifToDB(url, title , currentUser);
//
//
//        // LinkedHashMap<String, Object> jsonResponse = cloudinaryGifService.modifyJsonResponse("create", URL);
//        return new ResponseEntity<>(url,HttpStatus.CREATED);
//    }


    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public ResponseEntity<Object> VerifyToken( HttpServletRequest request) throws Exception {

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
                    return responseHandler.generateResponse("Verify successfully", HttpStatus.OK, user);


                } catch (IllegalArgumentException e) {
                    System.out.println("Unable to get JWT Token");
                    return responseHandler.generateResponse("Unable to get JWT Token", HttpStatus.BAD_REQUEST, null);

                } catch (ExpiredJwtException e) {
                    System.out.println("JWT Token has expired");
                    return responseHandler.generateResponse("JWT Token has expired", HttpStatus.BAD_REQUEST, null);

                }


            }

        };
        return responseHandler.generateResponse("Verify Failure", HttpStatus.BAD_REQUEST, null);


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
