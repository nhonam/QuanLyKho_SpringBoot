package net.nhonam.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.nhonam.springboot.Entity.NhaCungCap;
import net.nhonam.springboot.Entity.PhieuNhapKho;
import net.nhonam.springboot.response.ApiResponse;
import net.nhonam.springboot.service.PhieuNhapKhoService;

@RestController
@RequestMapping("/api/v1/PN")
public class PhieuNhapConTroller {
    @Autowired
    PhieuNhapKhoService PNKhoService;

    @GetMapping()
    public ApiResponse getAllPNKho() {
        try {
            List<PhieuNhapKho> PNKhoList = PNKhoService.getAllPNKho();
            return new ApiResponse(true, PNKhoList, "lay PN thành công!");
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    @PostMapping()
    public ApiResponse createPNKho(@Valid @RequestBody PhieuNhapKho PNKho) {
        try {
            PhieuNhapKho PNCreate = PNKhoService.createPNKho(PNKho);
            return new ApiResponse(true, PNCreate, "them PN thành công!");

        }catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ApiResponse getNhaPNKhoId(@PathVariable long id){

        try {
            PhieuNhapKho PNKho = PNKhoService.getPNKhoById(id);
            return new ApiResponse(true, PNKho, "Tìm kiếm nha cung cap thanh cong: "+id);
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteNhaCC(@PathVariable long id){

        PhieuNhapKho PNKho = PNKhoService.getPNKhoById(id);
        if(PNKho==null) {
            return new ApiResponse(false, null, "Khong co nha cung cap nay!");
        }
        PNKhoService.deletePNKho(id);
        return new ApiResponse(true, PNKho , "Xóa nha cung cap thanh cong");

    }
    
    }
