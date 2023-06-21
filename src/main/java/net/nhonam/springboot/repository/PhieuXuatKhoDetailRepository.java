package net.nhonam.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.nhonam.springboot.Entity.PhieuXuatDetail;

@Repository
public interface PhieuXuatKhoDetailRepository extends JpaRepository<PhieuXuatDetail, Long> {
    
}
