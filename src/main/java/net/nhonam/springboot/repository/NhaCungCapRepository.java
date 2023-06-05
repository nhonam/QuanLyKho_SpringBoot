package net.nhonam.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.nhonam.springboot.Entity.NhaCungCap;

@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Long> {
    // all crud database methods
}


