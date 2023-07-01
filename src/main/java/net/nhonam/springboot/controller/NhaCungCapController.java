package net.nhonam.springboot.controller;

import net.nhonam.springboot.Entity.NhaCungCap;
import net.nhonam.springboot.response.ResponseSingleton;
import net.nhonam.springboot.service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/supplier")
public class NhaCungCapController {
    ResponseSingleton responseHandler = ResponseSingleton.getInstance();

    @Autowired
    NhaCungCapService NhacungcapService;

    @GetMapping()
    public ResponseEntity<Object> getAllNhaCungCap(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            List<NhaCungCap> NhacungcapList = new ArrayList<>();

            Pageable paging = PageRequest.of(page, size);

            Page<NhaCungCap> pageTuts = NhacungcapService.allNhaCungCapPage(paging);
            NhacungcapList = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("listsupplier", NhacungcapList);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return responseHandler.generateResponse("get all supplier successfully!", HttpStatus.OK, response);

            // return new ApiResponse(true, NhacungcapList, "Lấy danh sách nha cung cap thành công!");
        } catch (Exception e) {
            return responseHandler.generateResponse("get all supplier fail !", HttpStatus.BAD_REQUEST, null);

        }
    }
    @PostMapping()
    public ResponseEntity<Object> createSupplier(@Valid @RequestBody NhaCungCap nhacungcap) {
        try {
            NhaCungCap nhacungcapCreate = NhacungcapService.createNhaCC(nhacungcap);
            return responseHandler.generateResponse("create Supplier successfully!", HttpStatus.OK, nhacungcapCreate);


        }catch (Exception e) {
            return responseHandler.generateResponse("create Supplier fail!", HttpStatus.OK, null);

        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getNhaCungCapId(@PathVariable long id){

        try {
            NhaCungCap nhacungcap = NhacungcapService.getNhaCungCapById(id);
            if (nhacungcap==null) {
                return responseHandler.generateResponse(" supplier not found!", HttpStatus.OK, null);

            }
            return responseHandler.generateResponse("get supplier by id successfully!", HttpStatus.OK, nhacungcap);

            // return new ApiResponse(true, nhacungcap, "Tìm kiếm nha cung cap thanh cong: "+id);
        } catch (Exception e) {
            return responseHandler.generateResponse(" supplier not found!", HttpStatus.BAD_REQUEST, null);

        }
    }


    @GetMapping("/search/{name}")
    public ResponseEntity<Object> searchNhaCungCap(@PathVariable String name){

        try {
            List<NhaCungCap> nhacungcap = NhacungcapService.search_ByName(name);
            if (nhacungcap==null) {
                return responseHandler.generateResponse(" searchNhaCungCap not found!", HttpStatus.OK, null);

            }
            return responseHandler.generateResponse("searchNhaCungCap successfully!", HttpStatus.OK, nhacungcap);

            // return new ApiResponse(true, nhacungcap, "Tìm kiếm nha cung cap thanh cong: "+id);
        } catch (Exception e) {
            return responseHandler.generateResponse(" searchNhaCungCap not found!", HttpStatus.BAD_REQUEST, null);

        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteNhaCCById(@PathVariable long id){

        NhaCungCap nhacungcap = NhacungcapService.getNhaCungCapById(id);
        if(nhacungcap==null) {
            return responseHandler.generateResponse("Supplier not exist!", HttpStatus.OK, null);

        }
        NhacungcapService.deleteNhaCC(id);
        return responseHandler.generateResponse("Delete supplier successfully", HttpStatus.BAD_REQUEST, null);


    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSupplier(@PathVariable long id,@RequestBody NhaCungCap nhacungcap) {

        try {
            NhaCungCap updateNhaCC = NhacungcapService.getNhaCungCapById(id);
            if(updateNhaCC==null) {
                return responseHandler.generateResponse("Supplier not exist!", HttpStatus.BAD_REQUEST, null);

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
                return responseHandler.generateResponse("updata Supplier successfully", HttpStatus.OK, updateNhaCC);


            }
        } catch (Exception e) {
            return responseHandler.generateResponse("Supplier not exist!", HttpStatus.BAD_REQUEST, null);

        }



    }
}
