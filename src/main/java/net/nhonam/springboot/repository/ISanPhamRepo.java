package net.nhonam.springboot.repository;

import net.nhonam.springboot.Entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ISanPhamRepo extends JpaRepository<SanPham, Long> {

//    Page<SanPham> findAll(boolean published, Pageable pageable);
//    Page<SanPham> findByTitleContaining(String title, Pageable pageable);
}
