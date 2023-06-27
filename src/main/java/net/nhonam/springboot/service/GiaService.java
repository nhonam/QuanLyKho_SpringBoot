package net.nhonam.springboot.service;

import java.util.List;

import net.nhonam.springboot.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.nhonam.springboot.Entity.Gia;
import net.nhonam.springboot.repository.GiaRepository;
@Service
public class GiaService {
    @Autowired
    private GiaRepository Giarepo;

    public Gia checkGia(int price){
        for (Gia gia: getAllGia()
        ) {
            if(price == gia.getGia())
                return gia;
        }
        return  null;

    }

    public List<Gia> getAllGia(){
        return  Giarepo.findAll();
    }

    public Gia getGiaById(Long id) {
        return Giarepo.findById(id).orElse(null);
    }
    public Gia createGia(Gia gia) {
        return Giarepo.save(gia);
    }
    public Gia updateGia(Long id, Gia gia) {
        return Giarepo.save(gia);
    }
    public void deleteGia(Long id) {
        Giarepo.deleteById(id);
    }
}
