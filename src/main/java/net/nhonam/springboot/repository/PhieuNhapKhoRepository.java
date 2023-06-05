package net.nhonam.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.nhonam.springboot.Entity.PhieuNhapKho;

@Repository
public interface PhieuNhapKhoRepository extends JpaRepository<PhieuNhapKho, Long> {
    // all crud database methods
}
