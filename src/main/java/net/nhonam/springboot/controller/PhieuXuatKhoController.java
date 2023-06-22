package net.nhonam.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.nhonam.springboot.Entity.PhieuXuatKho;
import net.nhonam.springboot.response.ApiResponse;
import net.nhonam.springboot.service.PhieuXuatKhoService;


@RestController
@RequestMapping("/api/v1/PX")
public class PhieuXuatKhoController {
    @Autowired
    PhieuXuatKhoService PXService;

    @GetMapping()
    public ApiResponse getAllPXKho() {
        try {
            List<PhieuXuatKho> PXKhoList = PXService.getAllPXKho();
            return new ApiResponse(true, PXKhoList, "lay PX thành công!");
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    @PostMapping()
    public ApiResponse createPXKho(@Valid @RequestBody PhieuXuatKho PXKho) {
        try {
            PhieuXuatKho PXCreate = PXService.createPXKho(PXKho);
            return new ApiResponse(true, PXCreate, "them PX thành công!");

        }catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ApiResponse getNhaPXKhoId(@PathVariable long id){

        try {
            PhieuXuatKho PXKho = PXService.getPXKhoById(id);
            return new ApiResponse(true, PXKho, "Tìm kiếm PX thanh cong: "+id);
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletePXKho(@PathVariable long id){

        PhieuXuatKho PXKho = PXService.getPXKhoById(id);
        if(PXKho==null) {
            return new ApiResponse(false, null, "Khong co PX nay!");
        }
        PXService.deletePXKho(id);
        return new ApiResponse(true, PXKho , "Xóa PX thanh cong");

    }
    
    }
