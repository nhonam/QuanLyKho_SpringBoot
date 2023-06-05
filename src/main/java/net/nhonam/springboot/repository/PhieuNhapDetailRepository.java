package net.nhonam.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.nhonam.springboot.Entity.PhieuNhapDetail;

@Repository
public interface PhieuNhapDetailRepository extends JpaRepository<PhieuNhapDetail, Long> {
    // all crud database methods
}
