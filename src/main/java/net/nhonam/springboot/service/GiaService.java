package net.nhonam.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.nhonam.springboot.Entity.Gia;
import net.nhonam.springboot.repository.GiaRepository;
@Service
public class GiaService {
    @Autowired
    private GiaRepository Giarepo;

    public List<Gia> getAllKhoDetail(){
        return  Giarepo.findAll();
    }

    public Gia getKhoDetailById(Long id) {
        return Giarepo.findById(id).orElse(null);
    }
    public Gia createKhoDetail(Gia gia) {
        return Giarepo.save(gia);
    }
    public Gia updateKhoDetail(Long id, Gia gia) {
        return Giarepo.save(gia);
    }
    public void deleteKhoDetail(Long id) {
        Giarepo.deleteById(id);
    }
}
