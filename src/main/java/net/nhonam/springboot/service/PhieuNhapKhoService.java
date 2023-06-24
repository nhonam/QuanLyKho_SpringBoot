package net.nhonam.springboot.service;

import java.util.List;

import net.nhonam.springboot.Entity.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.nhonam.springboot.Entity.PhieuNhapKho;
import net.nhonam.springboot.repository.PhieuNhapKhoRepository;

@Service
public class PhieuNhapKhoService {
    @Autowired
    private PhieuNhapKhoRepository phieunhaprepo;

    public List<PhieuNhapKho> getAllPNKho(){
        return  phieunhaprepo.findAll();
    }
    public Page<PhieuNhapKho> allPhieuNhapPaging(Pageable paging){
        return phieunhaprepo.findAll(paging);
    }
    public PhieuNhapKho getPNKhoById(Long id) {
        return phieunhaprepo.findById(id).orElse(null);
    }
    public PhieuNhapKho createPNKho(PhieuNhapKho kho) {
        return phieunhaprepo.save(kho);
    }
    public PhieuNhapKho updatePNKho(Long id, PhieuNhapKho ctkho) {
        return phieunhaprepo.save(ctkho);
    }
    public void deletePNKho(Long id) {
        phieunhaprepo.deleteById(id);
    }
}
