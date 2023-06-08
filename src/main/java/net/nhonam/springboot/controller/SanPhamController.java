package net.nhonam.springboot.controller;

import net.nhonam.springboot.Entity.SanPham;
import net.nhonam.springboot.Entity.User;
import net.nhonam.springboot.response.ApiResponse;
import net.nhonam.springboot.response.Response;
import net.nhonam.springboot.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.awt.image.SampleModel;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class SanPhamController {

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping()
    public Response getAllSanPham() {
        try {
            List<SanPham> sanPhamList = sanPhamService.getAllSanPham();
            Response res = Response.getInstance();
            res.setData(sanPhamList);
            res.setStatus(HttpStatus.OK);
            res.setMessage("Lấy danh sách sản phẩm thành công!");

            return res;
            // return new ApiResponse(true, sanPhamList, "Lấy danh sách sản phẩm thành công!");
        }catch (Exception e) {
            Response res = Response.getInstance();
            res.setData(null);
            res.setStatus(HttpStatus.BAD_REQUEST);
            res.setMessage(e.getMessage());

            return res;
            // return new ApiResponse(false, null, e.getMessage());
        }
    }

    @PostMapping()
    public Response createSanPham(@Valid @RequestBody SanPham sanPham) {
        try {

            // Khi muốn tạo một object ta chỉ cần khai báo như sau
            SanPham product = SanPham.newSanPham()
                    .id(sanPham.getId())
                    .ngaySanXuat(sanPham.getNgaySanXuat())
                    .soluong(sanPham.getSoluong())
                    .thang(sanPham.getThang())
                    .tenSanPham(sanPham.getTenSanPham())
                    .build();
            // nhìn vào có thể thấy code khá dễ hiểu trong việc mô tả các trường trong trường hợp cần nhiều biến cần truyền vào

            SanPham sanPhamCreate = sanPhamService.createSanPham(product);
            Response res = Response.getInstance();
            res.setData(sanPhamCreate);
            res.setStatus(HttpStatus.OK);
            res.setMessage("Tạo sản phẩm thành công!");

            return res;
            // return new ApiResponse(true, sanPhamCreate, "Tạo sản phẩm thành công!");

        }catch (Exception e) {
            Response res = Response.getInstance();
            res.setData(null);
            res.setStatus(HttpStatus.BAD_REQUEST);
            res.setMessage(e.getMessage());

            return res;
            // return new ApiResponse(false, null, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Response getSanPhamById(@PathVariable  long id){

        try {
            SanPham sanPham = sanPhamService.getSanPhamById(id);
            Response res = Response.getInstance();
            res.setData(sanPham);
            res.setStatus(HttpStatus.OK);
            res.setMessage("Tìm kiếm thành công sản phẩm : "+id);

            return res;
            // return new ApiResponse(true, sanPham, "Tìm kiếm thành công sản phẩm : "+id);
        } catch (Exception e) {
            Response res = Response.getInstance();
            res.setData(null);
            res.setStatus(HttpStatus.BAD_REQUEST);
            res.setMessage(e.getMessage());

            return res;
            // return new ApiResponse(false, null, e.getMessage());
        }

    }

    @PatchMapping("/{id}")
    public Response updateSanPham(@PathVariable long id,@RequestBody SanPham SanPham) {

        try {
            SanPham updateSanPham = sanPhamService.getSanPhamById(id);
            if(updateSanPham==null) {
                Response res = Response.getInstance();
                res.setData(null);
                res.setStatus(HttpStatus.BAD_REQUEST);
                res.setMessage("Sản phẩm không tồn tại");

                return res;
                // return new ApiResponse(false, null, "Sản phẩm không tồn tại");
            }else {
                if (SanPham.getTenSanPham() != null) {
                    updateSanPham.setTenSanPham(SanPham.getTenSanPham());
                }


                if (SanPham.getNgaySanXuat() != null) {
                    updateSanPham.setNgaySanXuat(SanPham.getNgaySanXuat());
                }

                if (SanPham.getSoluong() != 0) {
                    updateSanPham.setSoluong(SanPham.getSoluong());
                }

                if (SanPham.getThang() != 0) {
                    updateSanPham.setThang(SanPham.getThang());
                }


                sanPhamService.updateSanPham(id, updateSanPham);
                Response res = Response.getInstance();
                res.setData(updateSanPham);
                res.setStatus(HttpStatus.OK);
                res.setMessage("Cập nhật thông tin thành công");

                return res;
                // return new ApiResponse(true, updateSanPham, "Cập nhật thông tin thành công");

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

    // build delete employee REST API
    @DeleteMapping("/{id}")
    public Response deleteSanPham(@PathVariable long id){

        SanPham sanPham = sanPhamService.getSanPhamById(id);
        if(sanPham==null) {
            Response res = Response.getInstance();
            res.setData(null);
            res.setStatus(HttpStatus.BAD_REQUEST);
            res.setMessage("Sản phẩm không tồn tại");

            return res;
            // return new ApiResponse(false, null, "Sản phẩm không tồn tại");
        }


        sanPhamService.deleteSanPham(id);

        Response res = Response.getInstance();
        res.setData(sanPham);
        res.setStatus(HttpStatus.OK);
        res.setMessage("Xóa Sản phẩm thành công");
        return res;
        // return new ApiResponse(true, sanPham , "Xóa Sản phẩm thành công");

    }

}
