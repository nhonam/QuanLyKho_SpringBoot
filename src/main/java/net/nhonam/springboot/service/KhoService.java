package net.nhonam.springboot.service;

import java.util.List;

import net.nhonam.springboot.Entity.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.nhonam.springboot.Entity.Kho;
import net.nhonam.springboot.repository.KhoRepository;

@Service
public class KhoService {
    @Autowired
    private KhoRepository Khorepo;

    public List<Kho> getAllKho(){
        return  Khorepo.findAll();
    }

    public Kho getKhoById(Long id) {
        return Khorepo.findById(id).orElse(null);
    }

    public Page<Kho> allKhoPaging(Pageable paging){
        return Khorepo.findAll(paging);
    }
    public Kho createKho(Kho kho) {
        return Khorepo.save(kho);
    }
    public Kho updateKho(Long id, Kho kho) {
        return Khorepo.save(kho);
    }
    public void deleteKho(Long id) {
        Khorepo.deleteById(id);
    }
    public Kho getKhoByName(String tenKho) {
        return Khorepo.findByTenKho(tenKho);
    }
}
