package net.nhonam.springboot.repository;

import net.nhonam.springboot.DTO.SanPhamDTO;
import net.nhonam.springboot.Entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ISanPhamRepo extends JpaRepository<SanPham, Long> {

//    Page<SanPham> findAll(boolean published, Pageable pageable);
//    Page<SanPham> findByTitleContaining(String title, Pageable pageable);

    @Query(value = "CALL get_All_Product();", nativeQuery = true)
    List<SanPhamDTO> getAllProduct();

//    @Procedure(name = "get_All_Product()")
//    List<SanPhamDTO> getAllProduct();
}
