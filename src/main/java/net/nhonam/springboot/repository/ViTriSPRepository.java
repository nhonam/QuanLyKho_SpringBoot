package net.nhonam.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.nhonam.springboot.Entity.ViTriSP;

@Repository
public interface  ViTriSPRepository extends JpaRepository<ViTriSP, Long>{
    
}
