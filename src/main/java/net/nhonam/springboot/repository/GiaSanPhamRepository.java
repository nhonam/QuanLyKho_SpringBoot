package net.nhonam.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.nhonam.springboot.Entity.GiaSanPham;

@Repository
public interface GiaSanPhamRepository extends JpaRepository<GiaSanPham, Long>{
    
}
