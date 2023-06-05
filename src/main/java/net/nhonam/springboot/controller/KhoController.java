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
import net.nhonam.springboot.Entity.Kho;
import net.nhonam.springboot.response.ApiResponse;
import net.nhonam.springboot.service.KhoService;

// import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/kho")
public class KhoController {
    @Autowired
    KhoService khoService;

    @GetMapping()
    public ApiResponse getAllkho() {
        try {
            List<Kho> khoList = khoService.getAllKho();
            return new ApiResponse(true, khoList, "Lấy danh sách kho thành công!");
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    @GetMapping("/name")
    public ApiResponse getkhoByName(@RequestParam String tenkho) {
        try {
            List<Kho> khoList = khoService.getKhoByName(tenkho);
            if(!khoList.isEmpty()){
                return new ApiResponse(true, khoList, "Lấy ten kho thành công!");
            }
            else{
                return new ApiResponse(false, null, "Lấy ten kho khong thành công!");
            }
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    @PostMapping()
    public ApiResponse createkho(@Valid @RequestBody Kho kho) {
        try {
            // if(k.findByTenKho(kho.getTenKho())!=null) throw new RuntimeException("ten da duoc tao");
            if(khoService.getKhoByName(kho.getTenKho()).isEmpty()){
                Kho khoAdd = khoService.createKho(kho);
                return new ApiResponse(true, khoAdd, "them kho thành công!");
            }
            else{
                return new ApiResponse(false, null, "Ten Kho da duoc su dung!");
            }
        }catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ApiResponse getKhoById(@PathVariable long id){

        try {
            Kho kho = khoService.getKhoById(id);
            return new ApiResponse(true, kho, "Tìm kiếm kho thanh cong: "+id);
        } catch (Exception e) {
            return new ApiResponse(false, null, e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletekho(@PathVariable long id){

        Kho kho = khoService.getKhoById(id);
        if(kho==null) {
            return new ApiResponse(false, null, "Kho khong ton tai!");
        }
        khoService.deleteKho(id);
        return new ApiResponse(true, kho , "Xóa kho thanh cong");

    }
    @PutMapping("/{id}")
    public ApiResponse updateSanPham(@PathVariable long id,@RequestBody Kho kho) {

        try {
            Kho updatekho = khoService.getKhoById(id);
            if(updatekho==null) {
                return new ApiResponse(false, null, "Kho không tồn tại");
            }else {
                if (kho.getTenKho() != null) {
                    updatekho.setTenKho(kho.getTenKho());
                }
                if (kho.getSDT() != null) {
                    updatekho.setSDT(kho.getSDT());
                }

                if (kho.getDiaChi() != null) {
                    updatekho.setDiaChi(kho.getDiaChi());
                }

                khoService.updateKho(id, updatekho);
                return new ApiResponse(true, updatekho, "Cập nhật thông tin thành công");

            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ApiResponse(false, null, e.getMessage());
        }



    }
}
