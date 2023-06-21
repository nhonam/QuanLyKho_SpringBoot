package net.nhonam.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.nhonam.springboot.Entity.GiaSanPham;
import net.nhonam.springboot.repository.GiaSanPhamRepository;

@Service
public class GiaSanPhamService {

    @Autowired
    private GiaSanPhamRepository GiaSPrepo;

    public List<GiaSanPham> getAllKhoDetail(){
        return  GiaSPrepo.findAll();
    }

    public GiaSanPham getKhoDetailById(Long id) {
        return GiaSPrepo.findById(id).orElse(null);
    }
    public GiaSanPham createKhoDetail(GiaSanPham giasp) {
        return GiaSPrepo.save(giasp);
    }
    public GiaSanPham updateKhoDetail(Long id, GiaSanPham giasp) {
        return GiaSPrepo.save(giasp);
    }
    public void deleteKhoDetail(Long id) {
        GiaSPrepo.deleteById(id);
    }
}
