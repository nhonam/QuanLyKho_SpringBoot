package net.nhonam.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.nhonam.springboot.Entity.PhieuXuatKho;

@Repository
public interface PhieuXuatKhoRepository extends JpaRepository<PhieuXuatKho, Long> {
    // all crud database methods
}
