package net.nhonam.springboot.controller;

import java.util.List;

import net.nhonam.springboot.Utils.TotalPriceStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.nhonam.springboot.Entity.NhaCungCap;
import net.nhonam.springboot.Entity.PhieuNhapDetail;
import net.nhonam.springboot.Entity.PhieuNhapKho;
import net.nhonam.springboot.response.ApiResponse;
import net.nhonam.springboot.service.PhieuNhapDetailService;
import net.nhonam.springboot.service.PhieuNhapKhoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/PNDetail")
public class PhieuNhapDetailController {
    @Autowired
    PhieuNhapDetailService PNKhoDetailService;

    @GetMapping()
    public ApiResponse getAllPNKho() {
        try {
            List<PhieuNhapDetail> PNKhoDetailList = PNKhoDetailService.getAllphieunhapDetail();
            return new ApiResponse(true, PNKhoDetailList, "lay chi tiet PN thành công!");
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    private final TotalPriceStrategy totalquantity;
    public PhieuNhapDetailController(TotalPriceStrategy totalquantity) {
        this.totalquantity = totalquantity;
    }
    @GetMapping("/total")
    public ResponseEntity<?> getTotalQuantity() {
        List<PhieuNhapDetail> products =  PNKhoDetailService.getAllphieunhapDetail();
        int totalproduct = totalquantity.calculateTotalPrice(products);
        return ResponseEntity.ok(totalproduct);
    }

    @PostMapping()
    public ApiResponse createPNKhoDetail(@Valid @RequestBody PhieuNhapDetail PNKhoDetail) {
        try {
            PhieuNhapDetail PNDetailCreate = PNKhoDetailService.createphieunhapDetail(PNKhoDetail);
            return new ApiResponse(true, PNDetailCreate, "them chi tiet PN thành công!");

        }catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ApiResponse getPNKhoDetailById(@PathVariable long id){

        try {
            PhieuNhapDetail PNKhoDetail = PNKhoDetailService.getphieunhapDetailById(id);
            return new ApiResponse(true, PNKhoDetail, "Tìm kiếm chi tiet PN thanh cong: "+id);
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteNhaCC(@PathVariable long id){

        PhieuNhapDetail PNKhoDetail = PNKhoDetailService.getphieunhapDetailById(id);
        if(PNKhoDetail == null) {
            return new ApiResponse(false, null, "Khong co PN nay!");
        }
        PNKhoDetailService.deletephieunhapDetail(id);
        return new ApiResponse(true, PNKhoDetail , "Xóa PN thanh cong");

    }

    }
