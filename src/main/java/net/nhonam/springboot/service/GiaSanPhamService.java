package net.nhonam.springboot.service;

import java.util.List;

import net.nhonam.springboot.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.nhonam.springboot.Entity.GiaSanPham;
import net.nhonam.springboot.repository.GiaSanPhamRepository;

@Service
public class GiaSanPhamService {

    @Autowired
    private GiaSanPhamRepository GiaSPrepo;



    public List<GiaSanPham> getAllGiaSP(){
        return  GiaSPrepo.findAll();
    }

    public GiaSanPham getGiaSpById(Long id) {
        return GiaSPrepo.findById(id).orElse(null);
    }
    public GiaSanPham createGiaSp(GiaSanPham giasp) {
        return GiaSPrepo.save(giasp);
    }
    public GiaSanPham updateGiaSp(Long id, GiaSanPham giasp) {
        return GiaSPrepo.save(giasp);
    }
    public void deleteGiaSp(Long id) {
        GiaSPrepo.deleteById(id);
    }
}
