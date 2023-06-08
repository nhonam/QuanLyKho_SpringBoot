package net.nhonam.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import net.nhonam.springboot.response.Response;
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

import net.nhonam.springboot.Entity.Kho;
import net.nhonam.springboot.response.ApiResponse;
import net.nhonam.springboot.service.KhoService;

@RestController
@RequestMapping("/api/v1/kho")
public class KhoController {
    @Autowired
    KhoService khoService;

    @GetMapping()
    public Response getAllkho() {
        try {
            List<Kho> khoList = khoService.getAllKho();
            Response res = Response.getInstance();
            res.setData(khoList);
            res.setStatus(HttpStatus.OK);
            res.setMessage("thêm kho ok");

            return res;
            // return new ApiResponse(true, khoList, "Lấy danh sách kho thành công!");
        } catch (Exception e) {
            Response res = Response.getInstance();
            res.setData(null);
            res.setStatus(HttpStatus.BAD_REQUEST);
            res.setMessage("lay ds kho thất bại");

            return res;
            // return new ApiResponse(false, null, e.getMessage());
        }
    }
    @GetMapping("/name")
    public Response getkhoByName(@RequestParam String tenkho) {
        try {
            List<Kho> khoList = khoService.getKhoByName(tenkho);
            if(!khoList.isEmpty()){
                Response res = Response.getInstance();
                res.setData(khoList);
                res.setStatus(HttpStatus.OK);
                res.setMessage("thêm kho ok");

            return res;
                // return new ApiResponse(true, khoList, "Lấy ten kho thành công!");
            }
            else{
                Response res = Response.getInstance();
                res.setData(null);
                res.setStatus(HttpStatus.BAD_REQUEST);
                res.setMessage("lay ten kho thất bại");

                return res;
                // return new ApiResponse(false, null, "Lấy ten kho khong thành công!");
            }
        } catch (Exception e) {
            Response res = Response.getInstance();
            res.setData(null);
            res.setStatus(HttpStatus.BAD_REQUEST);
            res.setMessage(e.getMessage());

            return res;
            // return new ApiResponse(false, null, e.getMessage());
        }
    }
    @PostMapping()
    public Response createkho(@Valid @RequestBody Kho kho) {
        try {
            Kho khoAdd = khoService.createKho(kho);


            Response Res = Response.getInstance();
            Res.setData(khoAdd);
            Res.setStatus(HttpStatus.OK);
            Res.setMessage("thêm kho thành công");

            return Res;


        }catch (Exception e) {
            Response res = Response.getInstance();
            res.setData(null);
            res.setStatus(HttpStatus.BAD_REQUEST);
            res.setMessage(e.getMessage());
            return res;
        }
    }
    @GetMapping("/{id}")
    public Response getKhoById(@PathVariable long id){

        try {
            Kho kho = khoService.getKhoById(id);
            Response res = Response.getInstance();
            res.setData(kho);
            res.setStatus(HttpStatus.OK);
            res.setMessage("lay kho by id");
            return res;
            // return new ApiResponse(true, kho, "Tìm kiếm kho thanh cong: "+id);
        } catch (Exception e) {
            Response res = Response.getInstance();
            res.setData(null);
            res.setStatus(HttpStatus.BAD_REQUEST);
            res.setMessage("Lay kho by id that bai");
            return res;
            // return new ApiResponse(false, null, e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public Response deletekho(@PathVariable long id){

        Kho kho = khoService.getKhoById(id);
        if(kho==null) {
            Response res = Response.getInstance();
            res.setData(null);
            res.setStatus(HttpStatus.BAD_REQUEST);
            res.setMessage("Kho khong ton tai!");
            return res;
            // return new ApiResponse(false, null, "Kho khong ton tai!");
        }
        khoService.deleteKho(id);
            Response res = Response.getInstance();
            res.setData(kho);
            res.setStatus(HttpStatus.OK);
            res.setMessage("Xóa kho thanh cong");
            return res;
        // return new ApiResponse(true, kho , "Xóa kho thanh cong");

    }
    @PutMapping("/{id}")
    public Response updateSanPham(@PathVariable long id,@RequestBody Kho kho) {

        try {
            Kho updatekho = khoService.getKhoById(id);
            if(updatekho==null) {
                Response res = Response.getInstance();
                res.setData(null);
                res.setStatus(HttpStatus.BAD_REQUEST);
                res.setMessage("Kho khong ton tai!");
                return res;
                // return new ApiResponse(false, null, "Kho không tồn tại");
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
                Response res = Response.getInstance();
                res.setData(updatekho);
                res.setStatus(HttpStatus.OK);
                res.setMessage("Cập nhật thông tin thành công");
                return res;
                // return new ApiResponse(true, updatekho, "Cập nhật thông tin thành công");

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
