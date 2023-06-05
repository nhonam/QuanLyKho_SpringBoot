package net.nhonam.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.nhonam.springboot.Entity.PhieuXuatKho;
import net.nhonam.springboot.repository.PhieuXuatKhoRepository;

@Service
public class PhieuXuatKhoService {
    @Autowired
    private PhieuXuatKhoRepository PXKhorepo;

    public List<PhieuXuatKho> getAllPXKho(){
        return  PXKhorepo.findAll();
    }

    public PhieuXuatKho getPXKhoById(Long id) {
        return PXKhorepo.findById(id).orElse(null);
    }
    public PhieuXuatKho createPXKho(PhieuXuatKho PXkho) {
        return PXKhorepo.save(PXkho);
    }
    public PhieuXuatKho updatePXKho(Long id, PhieuXuatKho PXkho) {
        return PXKhorepo.save(PXkho);
    }
    public void deletePXKho(Long id) {
        PXKhorepo.deleteById(id);
    }
}
