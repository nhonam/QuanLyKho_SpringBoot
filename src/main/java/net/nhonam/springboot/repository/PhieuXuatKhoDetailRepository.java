package net.nhonam.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.nhonam.springboot.Entity.ChiTietKho;

@Repository
public interface ChiTietKhoRepository extends JpaRepository<ChiTietKho, Long> {
    
}
