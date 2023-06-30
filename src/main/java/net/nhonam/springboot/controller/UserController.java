package net.nhonam.springboot.controller;

import net.nhonam.springboot.Entity.Kho;
import net.nhonam.springboot.Utils.Ultil;
import net.nhonam.springboot.response.ResponseSingleton;
import net.nhonam.springboot.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.nhonam.springboot.Entity.User;
import net.nhonam.springboot.response.ApiResponse;
import net.nhonam.springboot.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    ResponseSingleton responseHandler = ResponseSingleton.getInstance();

    @Autowired
    FilesStorageService storageService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<Object> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        try {
            List<User> users = userService.getAllEmployee();

            return responseHandler.generateResponse("Get All employee Successfully", HttpStatus.OK, Ultil.ListToPage(users, page, size));
        } catch (Exception e) {
            return responseHandler.generateResponse("Get All employee fail", HttpStatus.BAD_REQUEST, null);
        }
    }


    @PostMapping("/search")
    public ResponseEntity<Object> searchUser(
            @RequestParam() int page,
            @RequestParam() int size,
            @RequestBody Map<String,Object> body
    ){
        try {
            if (body.get("email") == null)
                body.put("email","");
            if (body.get("name") == null)
                body.put("name","");
            if (body.get("sdt") == null)
                body.put("sdt","");
           List<User> listUser = userService.SearchUser(body.get("email").toString(), body.get("name").toString(),body.get("sdt").toString());
//            System.out.println(listUser.get(0).getUserName());
            Pageable paging = PageRequest.of(page, size);
            Page<User> pagelist = Ultil.toPage(listUser, paging);

            listUser = pagelist.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("listUser", listUser);
            response.put("currentPage", pagelist.getNumber());
            response.put("totalItems", pagelist.getTotalElements());
            response.put("totalPages", pagelist.getTotalPages());

            return responseHandler.generateResponse("Get employee Successfully", HttpStatus.OK, response);
        } catch (Exception e) {
            return responseHandler.generateResponse("Get employee fail", HttpStatus.BAD_REQUEST, null);
        }

    }

    // build create employee REST API
    @PostMapping
    public ApiResponse createEmployee(@RequestBody User user) {
        try {
            user.setStatus(true);
            User users = userService.createUser(user);

            return new ApiResponse(true, users, "Tạo nhân viên thành công!");
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }

    // build get employee by id REST API
    @GetMapping("/{id}")
    public ApiResponse getEmployeeById(@PathVariable  long id){

        try {
            User user = userService.getUserById(id);
            return new ApiResponse(true, user, "Tìm kiếm thành công nhân viên : "+id);
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }

    }

    // build update employee REST API
    @PatchMapping("/{id}")
    public ApiResponse updateEmployee(@PathVariable long id,@RequestBody User userDetail) {

        try {

            User updateUser = userService.getUserById(id);
            if(updateUser==null) {
                return new ApiResponse(false, null, "Nhân viên không tồn tại");
            }else {
                if (userDetail.getFirstName() != null) {
                    updateUser.setFirstName(userDetail.getFirstName());
                }

        
                if (userDetail.getLastName() != null) {
                    updateUser.setLastName(userDetail.getLastName());
                }

                if (userDetail.getUserName() != null) {
                    updateUser.setUserName(userDetail.getUserName());
                }

                if (userDetail.getPassword() != null) {
                    updateUser.setPassword(userDetail.getPassword());
                }
        
                if (userDetail.getEmail() != null) {
                    updateUser.setEmail(userDetail.getEmail());
                }

                if (userDetail.getSDT() != null) {
                    updateUser.setSDT(userDetail.getSDT());
                }
                updateUser.setStatus(true);
                userService.updateUser(id, updateUser);
            return new ApiResponse(true, updateUser, "Cập nhật thông tin thành công");
    
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ApiResponse(false, null, e.getMessage());
        }

    }

    @PatchMapping("/avatar")
    public ApiResponse updateAvatar(@RequestParam("id") long id,
                                    @RequestParam("file") MultipartFile file) throws IOException {


        User user = userService.getUserById((long)id);
        System.out.println(id);
        if(user==null) {
            return new ApiResponse(false, null, "Nhân viên không tồn tại");
        }



        if(file!=null) {
            if (user.getImage_url()!=null){
                storageService.deleteFile(user.getImage_url());

            }
            storageService.save(file);
            Resource resource= storageService.load(file.getOriginalFilename());
            String url_image = resource.getURL().toString().substring(6);
            user.setImage_url(url_image);
        }

//        user.setStatus(false);
        userService.updateUser(id, user);


        return new ApiResponse(true, user , "Cập nhật avatar thành coong");

    }

    // build delete employee REST API
    @DeleteMapping("/{id}")
    public ApiResponse deleteEmployee(@PathVariable long id){

        User user = userService.getUserById(id);
        if(user==null) {
            return new ApiResponse(false, null, "Nhân viên không tồn tại");
        }

        user.setStatus(false);
        userService.updateUser(id, user);


        return new ApiResponse(true, user , "Xóa nhân viên thành công");

    }
}
