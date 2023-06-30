package net.nhonam.springboot.controller;

import java.sql.Date;
import java.util.*;

import net.nhonam.springboot.DTO.PhieuNhapDTO;
import net.nhonam.springboot.Entity.*;
import net.nhonam.springboot.Utils.Ultil;
import net.nhonam.springboot.response.ResponseSingleton;
import net.nhonam.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.nhonam.springboot.response.ApiResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/receipt") //phiếu nhập
public class PhieuNhapConTroller {
    @Autowired
    PhieuNhapKhoService PNKhoService;

    @Autowired
    PhieuNhapDetailService phieuNhapDetailService;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    KhoService khoService;

    @Autowired
    UserService userService;

    @Autowired
    NhaCungCapService nhaCungCapService;
    ResponseSingleton responseHandler = ResponseSingleton.getInstance();

    @GetMapping("/admin")
    public ResponseEntity<Object> getAllPNKhoByAdmin(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {

            List listPNKho = PNKhoService.getAllPhieuNhapByAdmin();

            Pageable paging = PageRequest.of(page, size);
            Page<PhieuNhapDTO> pagelist = Ultil.toPage(listPNKho, paging);

            listPNKho = pagelist.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("listPhieuNhap", listPNKho);
            response.put("currentPage", pagelist.getNumber());
            response.put("totalItems", pagelist.getTotalElements());
            response.put("totalPages", pagelist.getTotalPages());

            return responseHandler.generateResponse("get listRecipt successfully!", HttpStatus.OK, response);

        } catch (Exception e) {
            return responseHandler.generateResponse("get listRecipt successfully!", HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPhieuNhapDetail(
            @PathVariable() long id
    ) {
        List phieunhap = PNKhoService.getPhieuNhapDetail(id);

        try {
            return responseHandler.generateResponse("get Recipt Detail successfully!", HttpStatus.OK, phieunhap);

        } catch (Exception e) {
            return responseHandler.generateResponse("Fail!", HttpStatus.OK, null);

        }

    }


    @GetMapping("/employee")
    public ResponseEntity<Object> getAllPNKhoByEmployee(
            @RequestParam() long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {

            List listPNKho = PNKhoService.getPhieuNhapByEmployee(id);

            Pageable paging = PageRequest.of(page, size);
            Page<PhieuNhapDTO> pagelist = Ultil.toPage(listPNKho, paging);

            listPNKho = pagelist.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("listPhieuNhap", listPNKho);
            response.put("currentPage", pagelist.getNumber());
            response.put("totalItems", pagelist.getTotalElements());
            response.put("totalPages", pagelist.getTotalPages());

            return responseHandler.generateResponse("get listRecipt successfully!", HttpStatus.OK, response);

        } catch (Exception e) {
            return responseHandler.generateResponse("get listRecipt successfully!", HttpStatus.BAD_REQUEST, null);
        }
    }


    @PostMapping()
    public ResponseEntity<Object> createPNKho(@RequestBody Map<String, Object> body) {
        try {
            Date ngay_nhap = Ultil.convertStringToSqlDate((String) body.get("ngayNhap"));
            int id_Kho = (int) body.get("id_kho");
            int id_nhaCungCap = (int) body.get("id_nhaCungCap");
            int id_User = (int) body.get("id_user");
            List<Integer> list_idSP = (List<Integer>) body.get("list_id_Sp");
            List<Integer> list_SoLuongSp = (List<Integer>) body.get("list_so_luong");


            PhieuNhapKho phieuNhapKhoTmp = new PhieuNhapKho();

            phieuNhapKhoTmp.setKho(khoService.getKhoById((long) id_Kho));
            phieuNhapKhoTmp.setNgayNhap(ngay_nhap);
            phieuNhapKhoTmp.setUser(userService.getUserById((long) id_User));
            phieuNhapKhoTmp.setNhaCungCap(nhaCungCapService.getNhaCungCapById((long) id_nhaCungCap));


            PhieuNhapKho phieuNhapKho = PNKhoService.createPNKho(phieuNhapKhoTmp);

            //taoj phiếu nhập detail
            for (int i = 0; i < list_idSP.size(); i++) {

                PhieuNhapDetail phieuNhapDetail = new PhieuNhapDetail();
                phieuNhapDetail.setPhieuNhapKho(phieuNhapKho);
                phieuNhapDetail.setSanPham(sanPhamService.getSanPhamById((long) list_idSP.get(i)));
                phieuNhapDetail.setSoLuong(list_SoLuongSp.get(i));
//                sanPhamService.getSanPhamById(Long.valueOf(list_idSP.get(i)));
                phieuNhapDetailService.createphieunhapDetail(phieuNhapDetail);

            }


            return responseHandler.generateResponse("create recipt successfully", HttpStatus.OK, phieuNhapKho);

        } catch (Exception e) {
            return responseHandler.generateResponse("create recipt fail", HttpStatus.BAD_REQUEST, null);
        }
    }


    @DeleteMapping("/{id}")
    public ApiResponse deleteNhaCC(@PathVariable long id) {

        PhieuNhapKho PNKho = PNKhoService.getPNKhoById(id);
        if (PNKho == null) {
            return new ApiResponse(false, null, "Khong co PN nay!");
        }
        PNKhoService.deletePNKho(id);
        return new ApiResponse(true, PNKho, "Xóa PN thanh cong");

    }

}
