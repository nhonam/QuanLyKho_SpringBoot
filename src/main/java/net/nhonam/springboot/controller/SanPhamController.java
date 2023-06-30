package net.nhonam.springboot.controller;

import net.nhonam.springboot.DTO.SanPhamDTO;
import net.nhonam.springboot.Entity.Gia;
import net.nhonam.springboot.Entity.GiaSanPham;
import net.nhonam.springboot.Entity.SanPham;
import net.nhonam.springboot.Utils.Ultil;
import net.nhonam.springboot.response.Response;
import net.nhonam.springboot.response.ResponseSingleton;
import net.nhonam.springboot.service.FilesStorageService;
import net.nhonam.springboot.service.GiaSanPhamService;
import net.nhonam.springboot.service.GiaService;
import net.nhonam.springboot.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

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

    @Autowired
    FilesStorageService storageService;
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

//     "tenSanPham":"nhog nfam ne",
//             "ngaySanXuat":"2001-03-30",
//             "hsd":333,
//             "gia":33332,
//             "start":"2023-02-02",
//             "end":"2022-02-01"
    @PostMapping()
    public ResponseEntity<Object> createSanPham(
            @RequestParam("file") MultipartFile file,
            @RequestParam("tenSanPham") String tenSanPham,
            @RequestParam("ngaySanXuat") Date ngaySanXuat,
            @RequestParam("start") Date start,
            @RequestParam("end") Date end,
            @RequestParam("hsd") int hsd,
            @RequestParam("gia") int price
    ) {
        try {
            storageService.save(file);
            Resource resource= storageService.load(file.getOriginalFilename());
            String url_image = resource.getURL().toString().substring(6);
            // Khi muốn tạo một object ta chỉ cần khai báo như sau
            SanPham product = SanPham.newSanPham()
                    .ngaySanXuat(ngaySanXuat)
                    .thang(hsd)
                    .tenSanPham(tenSanPham)
                    .url_image(url_image)
                    .build();
            // nhìn vào có thể thấy code khá dễ hiểu trong việc mô tả các trường trong trường hợp cần nhiều biến cần truyền vào

            if (sanPhamService.CheckSpExsit(product.getTenSanPham())) {
                return responseHandler.generateResponse("product is exsit (SanPhamController)", HttpStatus.BAD_REQUEST, null);

            }

            product.setStatus(true);
            SanPham sanPhamCreate = sanPhamService.createSanPham(product);


            //tạo giá sp
            Gia gia = new Gia();
            gia.setGia(price);
            gia.setNgayBatDau(start);
            gia.setNgayKetThuc(end);
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
    public ResponseEntity<Object> getSanPhamById(@PathVariable  long id){

        try {
            SanPhamDTO sanPham = sanPhamService.getSanPhamDTOById(id);
            if (sanPham==null) {
                return responseHandler.generateResponse("get product fail", HttpStatus.BAD_REQUEST, null);

            }
            return responseHandler.generateResponse("get product successfully", HttpStatus.OK, sanPham);

        } catch (Exception e) {
            return responseHandler.generateResponse("get product fail", HttpStatus.BAD_REQUEST, null);

        }

    }


    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateSanPham(@PathVariable long id
                                                ,@RequestParam("file") MultipartFile file,
                                                @RequestParam("tenSanPham") String tenSanPham,
                                                @RequestParam("ngaySanXuat") Date ngaySanXuat,
                                                @RequestParam(value = "hsd", defaultValue = "0")  int hsd ,
                                                @RequestParam("gia") int price  ) {

        SanPham updateSanPham = sanPhamService.getSanPhamById(id);
//        System.out.println(tenSanPham);
//        System.out.println(ngaySanXuat);
//        System.out.println(hsd);
//        System.out.println(price);
//        System.out.println(file.getOriginalFilename());
        try {



            if(updateSanPham==null) {
                return responseHandler.generateResponse("Product not found", HttpStatus.OK, null);
            }else {

                if (tenSanPham != null) {
                    updateSanPham.setTenSanPham(tenSanPham);
//                    if (sanPhamService.CheckSpExsit(tenSanPham)) {
//                        return responseHandler.generateResponse("product is exsit (SanPhamController)", HttpStatus.BAD_REQUEST, null);
//
//                    }
                }
                if (ngaySanXuat !=null) {
                    updateSanPham.setNgaySanXuat(ngaySanXuat);
                }
                if (hsd !=0 ) {
                    updateSanPham.setThang(hsd);
                }
                if(file!=null) {
                    storageService.deleteFile(updateSanPham.getImage_url());
                    storageService.save(file);
                    Resource resource= storageService.load(file.getOriginalFilename());
                    String url_image = resource.getURL().toString().substring(6);
                    updateSanPham.setImage_url(url_image);
                }


                sanPhamService.updateSanPham( updateSanPham);

                if (price !=0) {
                    Object a = sanPhamService.getIdSPandGia(id, price);
                }




                return responseHandler.generateResponse("Update Product successfully", HttpStatus.OK, updateSanPham);


            }
        } catch (Exception e) {
            return responseHandler.generateResponse("Update Product successfully"+e.getMessage(), HttpStatus.OK
                    , updateSanPham);

        }



    }

    // build delete employee REST API
    @DeleteMapping ("/{id}")
    public Response deleteSanPham(@PathVariable long id) throws IOException {

        SanPham sanPham = sanPhamService.getSanPhamById(id);
        if(sanPham==null) {
            Response res = Response.getInstance();
            res.setData(null);
            res.setStatus(HttpStatus.BAD_REQUEST);
            res.setMessage("Sản phẩm không tồn tại");

            return res;
            // return new ApiResponse(false, null, "Sản phẩm không tồn tại");
        }

        storageService.deleteFile(sanPham.getImage_url());

        sanPham.setStatus(false);
        sanPhamService.updateSanPham(sanPham);

        Response res = Response.getInstance();
        res.setData(sanPham);
        res.setStatus(HttpStatus.OK);
        res.setMessage("Xóa Sản phẩm thành công");
        return res;
        // return new ApiResponse(true, sanPham , "Xóa Sản phẩm thành công");

    }

}
