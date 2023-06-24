package net.nhonam.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.nhonam.springboot.Entity.ViTriSP;
import net.nhonam.springboot.repository.ViTriSPRepository;

public class ViTriSPService {
    @Autowired
    private ViTriSPRepository ViTriSPrepo;

    public List<ViTriSP> getAllPNKho(){
        return  ViTriSPrepo.findAll();
    }

    public ViTriSP getPNKhoById(Long id) {
        return ViTriSPrepo.findById(id).orElse(null);
    }
    public ViTriSP createPNKho(ViTriSP vitriSP) {
        return ViTriSPrepo.save(vitriSP);
    }
    public ViTriSP updatePNKho(Long id, ViTriSP vitriSP) {
        return ViTriSPrepo.save(vitriSP);
    }
    public void deletePNKho(Long id) {
        ViTriSPrepo.deleteById(id);
    }
}
