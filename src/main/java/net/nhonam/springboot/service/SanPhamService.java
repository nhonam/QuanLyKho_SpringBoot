package net.nhonam.springboot.service;

import net.nhonam.springboot.DTO.SanPhamDTO;
import net.nhonam.springboot.Entity.SanPham;
import net.nhonam.springboot.repository.ISanPhamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamService {

    @Autowired
    private ISanPhamRepo sanPhamRepo;

//    public List<SanPham> getAllSanPham(){
//        return  sanPhamRepo.findAll();
//    }

        public List<SanPhamDTO>  getAllSanPham(){
        return  sanPhamRepo.getAllProduct();
    }
    public SanPham getSanPhamById(Long id) {
        return sanPhamRepo.findById(id).orElse(null);
    }



    public SanPham createSanPham(SanPham sanPham) {
//        if(user.getRole().equals("ADMIN"))
//            user.setRole(RoleEnum.ADMIN);
//        else user.setRole(RoleEnum.EMPLOYEE);

        return sanPhamRepo.save(sanPham);
    }

    public SanPham updateSanPham(Long id, SanPham sanPham) {
        return sanPhamRepo.save(sanPham);
    }

    public void deleteSanPham(Long id) {
        sanPhamRepo.deleteById(id);
    }

}
