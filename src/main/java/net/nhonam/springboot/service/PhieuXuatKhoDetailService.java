package net.nhonam.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.nhonam.springboot.Entity.PhieuXuatDetail;
import net.nhonam.springboot.repository.PhieuXuatKhoDetailRepository;
@Service
public class PhieuXuatKhoDetailService {
    @Autowired
    private PhieuXuatKhoDetailRepository pxdetailrepo;

    public List<PhieuXuatDetail> getAllPhieuXuatDetail(){
        return  pxdetailrepo.findAll();
    }

    public PhieuXuatDetail getPhieuXuatDetailKhoById(Long id) {
        return pxdetailrepo.findById(id).orElse(null);
    }
    public PhieuXuatDetail createPhieuXuatDetail(PhieuXuatDetail pxdetail) {
        return pxdetailrepo.save(pxdetail);
    }
    public PhieuXuatDetail updatePhieuXuatDetail(Long id, PhieuXuatDetail pxdetail) {
        return pxdetailrepo.save(pxdetail);
    }
    public void deletePhieuXuatDetail(Long id) {
        pxdetailrepo.deleteById(id);
    }
}
