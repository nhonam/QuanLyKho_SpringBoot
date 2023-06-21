package net.nhonam.springboot.controller;

import java.util.List;


import net.nhonam.springboot.response.Response;
import net.nhonam.springboot.response.ResponseSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getAllWarehouse() {
        ResponseSingleton responseHandler = ResponseSingleton.getInstance();
        try {
            List<Kho> result = khoService.getAllKho();
            return responseHandler.generateResponse("Get AllWarehouse Successfully", HttpStatus.OK, result);
        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping("/name")
    public ResponseEntity<Object>  getkhoByName(@RequestParam String nameWarehouse) {
        ResponseSingleton responseHandler = ResponseSingleton.getInstance();

        try {
            Kho kho = khoService.getKhoByName(nameWarehouse);
            if(kho != null){
                return responseHandler.generateResponse("Get Warehouse Successfully", HttpStatus.OK, kho);
            }
            else{
                return responseHandler.generateResponse("Warehouse is not exist", HttpStatus.OK, kho);
            }
        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);

        }
    }
    @PostMapping()
    public  ResponseEntity<Object> createKho( @RequestBody Kho kho) {
        ResponseSingleton responseHandler = ResponseSingleton.getInstance();

        try {

            Kho exsit = khoService.getKhoByName(kho.getTenKho());
            if(exsit != null) {
                return responseHandler.generateResponse("Warehouse Exsit", HttpStatus.OK, null);
            }else {
                Kho khoAdd = khoService.createKho(kho);
                return responseHandler.generateResponse("Create Warehouse Successfully", HttpStatus.OK, khoAdd);
            }

        }catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
    @GetMapping("/{id}")
    public  ResponseEntity<Object>  getKhoById(@PathVariable long id){
        ResponseSingleton responseHandler = ResponseSingleton.getInstance();

        try {
            Kho kho = khoService.getKhoById(id);
            if(kho != null)
            return responseHandler.generateResponse("get warehouse by id successfully", HttpStatus.OK, kho);
            return responseHandler.generateResponse("Warehouse is not Exsit", HttpStatus.OK, null);

        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.OK, null);

        }
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Object>  deletekho(@PathVariable long id){
        ResponseSingleton responseHandler = ResponseSingleton.getInstance();

        Kho kho = khoService.getKhoById(id);
        if(kho==null) {
            return responseHandler.generateResponse("Warehouse is not exsit", HttpStatus.OK, null);

        }
        khoService.deleteKho(id);
        return responseHandler.generateResponse("Warehouse successfully", HttpStatus.OK, kho);

    }
    @PutMapping("/{id}")
    public  ResponseEntity<Object> updateKho(@PathVariable long id,@RequestBody Kho kho) {
        ResponseSingleton responseHandler = ResponseSingleton.getInstance();

        try {
            System.out.println(id);
            Kho updatekho = khoService.getKhoById(id);
            System.out.println(updatekho+"nam");
            if(updatekho == null ) {
                return responseHandler.generateResponse("Warehouse is not Exsit", HttpStatus.OK, null);
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
                return responseHandler.generateResponse("Updata Warehouse successfully", HttpStatus.OK, updatekho);


            }
        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);

        }
    }
}
