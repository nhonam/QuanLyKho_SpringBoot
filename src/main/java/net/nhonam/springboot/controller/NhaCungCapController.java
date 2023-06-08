package net.nhonam.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import net.nhonam.springboot.Entity.NhaCungCap;
import net.nhonam.springboot.response.ApiResponse;
import net.nhonam.springboot.response.Response;
import net.nhonam.springboot.service.NhaCungCapService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/supplier")
public class NhaCungCapController {

    @Autowired
    NhaCungCapService NhacungcapService;

    @GetMapping()
    public Response getAllNhaCungCap() {
        try {
            List<NhaCungCap> NhacungcapList = NhacungcapService.getAllNhaCungCap();
            Response res = Response.getInstance();
            res.setData(NhacungcapList);
            res.setStatus(HttpStatus.OK);
            res.setMessage("Lấy danh sách nha cung cap thành công!");

            return res;
            // return new ApiResponse(true, NhacungcapList, "Lấy danh sách nha cung cap thành công!");
        } catch (Exception e) {
            Response res = Response.getInstance();
            res.setData(null);
            res.setStatus(HttpStatus.BAD_REQUEST);
            res.setMessage("Lấy danh sách nha cung cap that bai!");

            return res;
            // return new ApiResponse(false, null, e.getMessage());
        }
    }
    @PostMapping()
    public Response createSanPham(@Valid @RequestBody NhaCungCap nhacungcap) {
        try {
            NhaCungCap nhacungcapCreate = NhacungcapService.createNhaCC(nhacungcap);
            Response res = Response.getInstance();
            res.setData(nhacungcapCreate);
            res.setStatus(HttpStatus.OK);
            res.setMessage("Them nha cung cap thành công!");

            return res;
            // return new ApiResponse(true, nhacungcapCreate, "them nha cung cap thành công!");

        }catch (Exception e) {
            Response res = Response.getInstance();
            res.setData(null);
            res.setStatus(HttpStatus.OK);
            res.setMessage(e.getMessage());

            return res;
            // return new ApiResponse(false, null, e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public Response getNhaCungCapId(@PathVariable long id){

        try {
            NhaCungCap nhacungcap = NhacungcapService.getNhaCungCapById(id);
            Response res = Response.getInstance();
            res.setData(nhacungcap);
            res.setStatus(HttpStatus.OK);
            res.setMessage("Them nha cung cap thành công!");

            return res;
            // return new ApiResponse(true, nhacungcap, "Tìm kiếm nha cung cap thanh cong: "+id);
        } catch (Exception e) {
            Response res = Response.getInstance();
            res.setData(null);
            res.setStatus(HttpStatus.BAD_REQUEST);
            res.setMessage(e.getMessage());

            return res;
            // return new ApiResponse(false, null, e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public Response deleteNhaCC(@PathVariable long id){

        NhaCungCap nhacungcap = NhacungcapService.getNhaCungCapById(id);
        if(nhacungcap==null) {
            Response res = Response.getInstance();
            res.setData(null);
            res.setStatus(HttpStatus.BAD_REQUEST);
            res.setMessage("Khong co nha cung cap nay!");

            return res;
            // return new ApiResponse(false, null, "Khong co nha cung cap nay!");
        }
        NhacungcapService.deleteNhaCC(id);
            Response res = Response.getInstance();
            res.setData(nhacungcap);
            res.setStatus(HttpStatus.OK);
            res.setMessage("Xóa nha cung cap thanh cong");

            return res;
        // return new ApiResponse(true, nhacungcap , "Xóa nha cung cap thanh cong");

    }
    @PutMapping("/{id}")
    public Response updateSanPham(@PathVariable long id,@RequestBody NhaCungCap nhacungcap) {

        try {
            NhaCungCap updateNhaCC = NhacungcapService.getNhaCungCapById(id);
            if(updateNhaCC==null) {
                Response res = Response.getInstance();
                res.setData(null);
                res.setStatus(HttpStatus.BAD_REQUEST);
                res.setMessage("Nha CC không tồn tại");

                return res;
                // return new ApiResponse(false, null, "Nha CC không tồn tại");
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
                Response res = Response.getInstance();
                res.setData(updateNhaCC);
                res.setStatus(HttpStatus.OK);
                res.setMessage("Cập nhật thông tin thành công");

                return res;
                // return new ApiResponse(true, updateNhaCC, "Cập nhật thông tin thành công");

            }
        } catch (Exception e) {
            // TODO: handle exception
            Response res = Response.getInstance();
                res.setData(null);
                res.setStatus(HttpStatus.BAD_REQUEST);
                res.setMessage(e.getMessage());

                return res;
            // return new ApiResponse(false, null, e.getMessage());
        }



    }
}
