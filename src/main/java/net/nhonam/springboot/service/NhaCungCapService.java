package net.nhonam.springboot.service;

import java.util.List;

import net.nhonam.springboot.Entity.SanPham;
import org.springframework.beans.factory.annotation.Autowired;

import net.nhonam.springboot.Entity.NhaCungCap;
import net.nhonam.springboot.repository.NhaCungCapRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public class NhaCungCapService {
    @Autowired
    private NhaCungCapRepository NhaCungCaprepo;

    public List<NhaCungCap> getAllNhaCungCap(){
        return  NhaCungCaprepo.findAll();
    }

    public Page<NhaCungCap> allNhaCungCapPage(Pageable paging){
        return NhaCungCaprepo.findAll(paging);
    }
    public NhaCungCap getNhaCungCapById(Long id) {
        return NhaCungCaprepo.findById(id).orElse(null);
    }
    public NhaCungCap createNhaCC(NhaCungCap nhacungcap) {
        return NhaCungCaprepo.save(nhacungcap);
    }
    public NhaCungCap updateNhaCC(Long id, NhaCungCap nhacungcap) {
        return NhaCungCaprepo.save(nhacungcap);
    }
    public void deleteNhaCC(Long id) {
        NhaCungCaprepo.deleteById(id);
    }
}
