package net.nhonam.springboot.controller;

import net.nhonam.springboot.DTO.SanPhamDTO;
import net.nhonam.springboot.Entity.Gia;
import net.nhonam.springboot.Entity.GiaSanPham;
import net.nhonam.springboot.Entity.SanPham;
import net.nhonam.springboot.Utils.Ultil;
import net.nhonam.springboot.response.Response;
import net.nhonam.springboot.response.ResponseSingleton;
import net.nhonam.springboot.service.GiaSanPhamService;
import net.nhonam.springboot.service.GiaService;
import net.nhonam.springboot.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
public class SanPhamController {
    ResponseSingleton responseHandler = ResponseSingleton.getInstance();
    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private GiaSanPhamService giaSanPhamService;
    @Autowired
    private GiaService giaService;
    @GetMapping()
    public ResponseEntity<Object> getAllSanPham(  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        try {
            List sanPhamList = sanPhamService.getAllSanPham();

            return responseHandler.generateResponse("Get All Product Successfully", HttpStatus.OK, Ultil.ListToPage(sanPhamList, page, size));

        }catch (Exception e) {
            return responseHandler.generateResponse("Get All Product fail (SanPhamController.java) "+e.getMessage(), HttpStatus.BAD_REQUEST, null);

        }
    }

//    @GetMapping()
//    public ResponseEntity<Object> getAllSanPham(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        try {
//            List<SanPham> listProduct;
//            Pageable paging = PageRequest.of(page, size);
//
//            Page<SanPham> pageTuts = sanPhamService.allSanPhamPaging(paging);
//            listProduct = pageTuts.getContent();
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("listProduct", listProduct);
//            response.put("currentPage", pageTuts.getNumber());
//            response.put("totalItems", pageTuts.getTotalElements());
//            response.put("totalPages", pageTuts.getTotalPages());
//
//            return responseHandler.generateResponse("get all product successfully!", HttpStatus.OK, response);
//        } catch (Exception e) {
//            return responseHandler.generateResponse("get all product fail !", HttpStatus.BAD_REQUEST, null);
//        }
//    }

    @PostMapping()
    public ResponseEntity<Object> createSanPham(
            @RequestBody Map<String,Object> req
    ) {
        try {
            // Khi muốn tạo một object ta chỉ cần khai báo như sau
            SanPham product = SanPham.newSanPham()
                    .ngaySanXuat(Ultil.convertStringToSqlDate((String) req.get("ngaySanXuat")))
                    .thang((Integer) req.get("hsd"))
                    .tenSanPham((String) req.get("tenSanPham"))
                    .build();
            // nhìn vào có thể thấy code khá dễ hiểu trong việc mô tả các trường trong trường hợp cần nhiều biến cần truyền vào

            SanPham sanPhamCreate = sanPhamService.createSanPham(product);


            //tạo giá sp
            Gia gia = new Gia();
            gia.setGia((Integer) req.get("gia"));
            gia.setNgayBatDau(Ultil.convertStringToSqlDate((String) req.get("start")));
            gia.setNgayKetThuc(Ultil.convertStringToSqlDate((String) req.get("end")));
            Gia giaCreat = giaService.createGia(gia);

            //tạo giá sp
            GiaSanPham giaSanPham = new GiaSanPham();
            giaSanPham.setSanPham(sanPhamCreate);
            giaSanPham.setGia(giaCreat);
            GiaSanPham giaSanPham1= giaSanPhamService.createGiaSp(giaSanPham);



            return responseHandler.generateResponse("create product successfully", HttpStatus.OK, sanPhamCreate);


        }catch (Exception e) {
            return responseHandler.generateResponse("create product fail (SanPhamController)"+e.getMessage(), HttpStatus.OK, null);

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
