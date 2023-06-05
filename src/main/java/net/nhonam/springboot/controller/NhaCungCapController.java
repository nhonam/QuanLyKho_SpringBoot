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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.nhonam.springboot.Entity.NhaCungCap;
import net.nhonam.springboot.response.ApiResponse;
import net.nhonam.springboot.service.NhaCungCapService;

@RestController
@RequestMapping("/api/v1/supplier")
public class NhaCungCapController {

    @Autowired
    NhaCungCapService NhacungcapService;

    @GetMapping()
    public ApiResponse getAllNhaCungCap() {
        try {
            List<NhaCungCap> NhacungcapList = NhacungcapService.getAllNhaCungCap();
            return new ApiResponse(true, NhacungcapList, "Lấy danh sách nha cung cap thành công!");
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    @PostMapping()
    public ApiResponse createSanPham(@Valid @RequestBody NhaCungCap nhacungcap) {
        try {
            NhaCungCap nhacungcapCreate = NhacungcapService.createNhaCC(nhacungcap);
            return new ApiResponse(true, nhacungcapCreate, "them nha cung cap thành công!");

        }catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ApiResponse getNhaCungCapId(@PathVariable long id){

        try {
            NhaCungCap nhacungcap = NhacungcapService.getNhaCungCapById(id);
            return new ApiResponse(true, nhacungcap, "Tìm kiếm nha cung cap thanh cong: "+id);
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteNhaCC(@PathVariable long id){

        NhaCungCap nhacungcap = NhacungcapService.getNhaCungCapById(id);
        if(nhacungcap==null) {
            return new ApiResponse(false, null, "Khong co nha cung cap nay!");
        }
        NhacungcapService.deleteNhaCC(id);
        return new ApiResponse(true, nhacungcap , "Xóa nha cung cap thanh cong");

    }
    @PutMapping("/{id}")
    public ApiResponse updateSanPham(@PathVariable long id,@RequestBody NhaCungCap nhacungcap) {

        try {
            NhaCungCap updateNhaCC = NhacungcapService.getNhaCungCapById(id);
            if(updateNhaCC==null) {
                return new ApiResponse(false, null, "Nha CC không tồn tại");
            }else {
                if (nhacungcap.getTenNhaCungCap() != null) {
                    updateNhaCC.setTenNhaCungCap(nhacungcap.getTenNhaCungCap());
                }
                if (nhacungcap.getDiaChi() != null) {
                    updateNhaCC.setDiaChi(nhacungcap.getDiaChi());
                }

                if (nhacungcap.getSdt() != null) {
                    updateNhaCC.setSdt(nhacungcap.getSdt());
                }

                if (nhacungcap.getEmail() != null) {
                    updateNhaCC.setEmail(nhacungcap.getEmail());
                }


                NhacungcapService.updateNhaCC(id, updateNhaCC);
                return new ApiResponse(true, updateNhaCC, "Cập nhật thông tin thành công");

            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ApiResponse(false, null, e.getMessage());
        }



    }
}
