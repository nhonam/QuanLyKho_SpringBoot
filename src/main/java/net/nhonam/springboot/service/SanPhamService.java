package net.nhonam.springboot.service;

import net.nhonam.springboot.DTO.SanPhamDTO;
import net.nhonam.springboot.Entity.SanPham;
import net.nhonam.springboot.repository.ISanPhamRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamService {

    @Autowired
    private ISanPhamRepo sanPhamRepo;


    public List<SanPham> searchSPbyName(String name){
        return  sanPhamRepo.searchSanPhamByName(name);
    }


    public List<SanPham> getAllSanPhamtontai(){
        return  sanPhamRepo.findAll();
    }

        public List<SanPhamDTO>  getAllSanPham(){
        return  sanPhamRepo.getAllProduct();
    }



    public Boolean CheckSpExsit(String name) {
            
            List<SanPhamDTO> list = getAllSanPham();
        for (SanPhamDTO sp: list
             ) {
            if(sp.getten_san_pham().equalsIgnoreCase(name)){
                return true;
            }

        }
            
            return false;
    }

    public Object getIdSPandGia(Long id, int gia) {
        return sanPhamRepo.getIdSPUpdate(id,gia);
    }
    public SanPham getSanPhamById(Long id) {
        return sanPhamRepo.findById(id).orElse(null);
    }

    public SanPhamDTO getSanPhamDTOById(Long id) {
        return sanPhamRepo.getProductById(id);
    }



    public SanPham createSanPham(SanPham sanPham) {
//        if(user.getRole().equals("ADMIN"))
//            user.setRole(RoleEnum.ADMIN);
//        else user.setRole(RoleEnum.EMPLOYEE);

        return sanPhamRepo.save(sanPham);
    }

    public SanPham updateSanPham(SanPham sanPham) {
        return sanPhamRepo.save(sanPham);
    }

    public void deleteSanPham(Long id) {
        sanPhamRepo.deleteById(id);
    }

}
