package net.nhonam.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.nhonam.springboot.Entity.PhieuNhapDetail;
import net.nhonam.springboot.repository.PhieuNhapDetailRepository;

@Service
public class PhieuNhapDetailService {
    @Autowired
    private PhieuNhapDetailRepository PhieuNhapDetailrepo;

    public List<PhieuNhapDetail> getAllphieunhapDetail(){
        return  PhieuNhapDetailrepo.findAll();
    }

    public PhieuNhapDetail getphieunhapDetailById(Long id) {
        return PhieuNhapDetailrepo.findById(id).orElse(null);
    }
    public PhieuNhapDetail createphieunhapDetail(PhieuNhapDetail phieunhapDetail) {
        return PhieuNhapDetailrepo.save(phieunhapDetail);
    }
    public PhieuNhapDetail updatephieunhapDetail(Long id, PhieuNhapDetail phieunhapDetail) {
        return PhieuNhapDetailrepo.save(phieunhapDetail);
    }
    public void deletephieunhapDetail(Long id) {
        PhieuNhapDetailrepo.deleteById(id);
    }
}
