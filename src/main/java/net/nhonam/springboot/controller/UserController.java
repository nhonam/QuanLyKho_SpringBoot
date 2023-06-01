package net.nhonam.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.nhonam.springboot.DTO.UserDTO;
import net.nhonam.springboot.Entity.User;
import net.nhonam.springboot.response.ApiResponse;
import net.nhonam.springboot.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ApiResponse getAllEmployees(){
        try {
            List<User> users = userService.getAllUsers();
            return new ApiResponse(true, users, "Lấy danh sách nhân viên thành công!");
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }

       
    }

    // build create employee REST API
    @PostMapping
    public ApiResponse createEmployee(@RequestBody User user) {
        try {
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
                userService.updateUser(id, updateUser);
            return new ApiResponse(true, updateUser, "Cập nhật thông tin thành công");
    
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ApiResponse(false, null, e.getMessage());
        }
       
      

    }

    // build delete employee REST API
    @DeleteMapping("/{id}")
    public ApiResponse deleteEmployee(@PathVariable long id){

        User user = userService.getUserById(id);
        if(user==null) {
            return new ApiResponse(false, null, "Nhân viên không tồn tại");
        }   


        userService.deleteUser(id);


        return new ApiResponse(true, user , "Xóa nhân viên thành công");

    }
}
