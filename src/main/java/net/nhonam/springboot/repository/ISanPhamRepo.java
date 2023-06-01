package net.nhonam.springboot.repository;

import net.nhonam.springboot.Entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISanPhamRepo extends JpaRepository<SanPham, Long> {
}
