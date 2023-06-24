package net.nhonam.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.nhonam.springboot.Entity.SanPham;
import net.nhonam.springboot.response.ResponseSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.nhonam.springboot.Entity.NhaCungCap;
import net.nhonam.springboot.Entity.PhieuNhapKho;
import net.nhonam.springboot.response.ApiResponse;
import net.nhonam.springboot.service.PhieuNhapKhoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/receipt") //phiếu nhập
public class PhieuNhapConTroller {
    @Autowired
    PhieuNhapKhoService PNKhoService;
    ResponseSingleton responseHandler = ResponseSingleton.getInstance();

    @GetMapping()
    public ResponseEntity<Object> getAllPNKho(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            List<PhieuNhapKho> PNKhoList = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);

            Page<PhieuNhapKho> pageTuts = PNKhoService.allPhieuNhapPaging(paging);
            PNKhoList = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("listRecipt", PNKhoList);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            return responseHandler.generateResponse("get listRecipt successfully!", HttpStatus.OK, response);

        } catch (Exception e) {
            return responseHandler.generateResponse("get listRecipt successfully!", HttpStatus.BAD_REQUEST, null);
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
