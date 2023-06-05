package net.nhonam.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.nhonam.springboot.Entity.ChiTietKho;
import net.nhonam.springboot.repository.KhoDetailRepository;

@Service
public class KhoDetailService {
    @Autowired
    private KhoDetailRepository KhoDetailrepo;

    public List<ChiTietKho> getAllKhoDetail(){
        return  KhoDetailrepo.findAll();
    }

    public ChiTietKho getKhoDetailById(Long id) {
        return KhoDetailrepo.findById(id).orElse(null);
    }
    public ChiTietKho createKhoDetail(ChiTietKho kho) {
        return KhoDetailrepo.save(kho);
    }
    public ChiTietKho updateKhoDetail(Long id, ChiTietKho ctkho) {
        return KhoDetailrepo.save(ctkho);
    }
    public void deleteKhoDetail(Long id) {
        KhoDetailrepo.deleteById(id);
    }
}
