package net.nhonam.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.nhonam.springboot.Entity.Gia;

@Repository
public interface GiaRepository extends JpaRepository<Gia, Long>{
    
}
