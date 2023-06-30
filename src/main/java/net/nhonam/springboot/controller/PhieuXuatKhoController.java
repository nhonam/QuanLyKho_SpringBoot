package net.nhonam.springboot.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import net.nhonam.springboot.DTO.PhieuXuatDetailDTO;
import net.nhonam.springboot.Entity.PhieuNhapDetail;
import net.nhonam.springboot.Entity.PhieuXuatDetail;
import net.nhonam.springboot.Utils.Ultil;
import net.nhonam.springboot.response.ResponseSingleton;
import net.nhonam.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.nhonam.springboot.Entity.PhieuXuatKho;
import net.nhonam.springboot.response.ApiResponse;


@RestController
@RequestMapping("/api/v1/bill")
public class PhieuXuatKhoController {
    @Autowired
    PhieuXuatKhoService PXService;
    @Autowired
    KhoService khoService;
    @Autowired
    UserService userService;
    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    PhieuXuatKhoDetailService phieuXuatKhoDetailService;
    ResponseSingleton responseHandler = ResponseSingleton.getInstance();

    @GetMapping("/admin")
    public ResponseEntity<Object> getAllPXKhoByAdmin() {
        try {
            List PXKhoList = PXService.getAllPhieuXuatByAdmin();
            return responseHandler.generateResponse("get all bill successfully", HttpStatus.OK, PXKhoList);
        } catch (Exception e) {
            return responseHandler.generateResponse("get all bill fail", HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getAllPXKhoByEmployee(
            @PathVariable() long id
    ) {
        try {
            List PXKhoList = PXService.getAllPhieuXuatByIdUser(id);
            return responseHandler.generateResponse("get all bill successfully", HttpStatus.OK, PXKhoList);
        } catch (Exception e) {
            return responseHandler.generateResponse("get all bill fail", HttpStatus.BAD_REQUEST, null);
        }
    }
    @PostMapping()
    public ResponseEntity<Object> createPXKho(@RequestBody Map<String,Object> body) {
        try {

            Date ngay_xuat = Ultil.convertStringToSqlDate((String) body.get("ngay_xuat"));
            int id_Kho = (int) body.get("id_kho");
            int id_employee = (int) body.get("id_employee");
            List<Integer> list_idSP = (List<Integer>) body.get("list_id_Sp");
            List<Integer> list_SoLuongSp = (List<Integer>) body.get("list_so_luong");

            PhieuXuatKho phieuXuatKho = new PhieuXuatKho();
            phieuXuatKho.setKho(khoService.getKhoById((long) id_Kho));
            phieuXuatKho.setNgayXuat(ngay_xuat);
            phieuXuatKho.setUser(userService.getUserById((long) id_employee));

            PhieuXuatKho phieuXuatKho1 = PXService.createPXKho(phieuXuatKho);

            //taoj phiếu xuat detail
            for (int i = 0; i < list_idSP.size(); i++) {

                PhieuXuatDetail phieuXuatDetail = new PhieuXuatDetail();
                phieuXuatDetail.setPhieuXuatKho(phieuXuatKho1);
                phieuXuatDetail.setSanPham(sanPhamService.getSanPhamById((long) list_idSP.get(i)));
                phieuXuatDetail.setSoLuong(list_SoLuongSp.get(i));

                phieuXuatKhoDetailService.createPhieuXuatDetail(phieuXuatDetail);

            }
            return responseHandler.generateResponse("create bill successfully", HttpStatus.OK, phieuXuatKho1);

        }catch (Exception e) {
            return responseHandler.generateResponse("create bill fail", HttpStatus.BAD_REQUEST, null);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBillDetail(@PathVariable long id){

        try {
            List PXKho = PXService.getPhieuXuatDetailById(id);
            if (PXKho==null){
                return responseHandler.generateResponse("get Bill is not exsit", HttpStatus.BAD_REQUEST, null);

            }
            return responseHandler.generateResponse("get Bill Detail successfully", HttpStatus.OK, PXKho);
        } catch (Exception e) {
            return responseHandler.generateResponse("get Bill Detail fail", HttpStatus.BAD_REQUEST, null);
        }
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletePXKho(@PathVariable long id){

        PhieuXuatKho PXKho = PXService.getPXKhoById(id);
        if(PXKho==null) {
            return new ApiResponse(false, null, "Khong co PX nay!");
        }
        PXService.deletePXKho(id);
        return new ApiResponse(true, PXKho , "Xóa PX thanh cong");

    }
    
    }
