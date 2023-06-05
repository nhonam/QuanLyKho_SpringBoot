package net.nhonam.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Kho createKho(Kho kho) {
        return Khorepo.save(kho);
    }
    public Kho updateKho(Long id, Kho kho) {
        return Khorepo.save(kho);
    }
    public void deleteKho(Long id) {
        Khorepo.deleteById(id);
    }
    public List<Kho> getKhoByName(String tenKho) {
        return Khorepo.findByTenKho(tenKho);
    }
}
