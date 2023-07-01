package net.nhonam.springboot.repository;

import net.nhonam.springboot.DTO.SanPhamDTO;
import net.nhonam.springboot.Entity.SanPham;
import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ISanPhamRepo extends JpaRepository<SanPham, Long> {

//    Page<SanPham> findAll(boolean published, Pageable pageable);
//    Page<SanPham> findByTitleContaining(String title, Pageable pageable);

    @Query(value = "CALL get_All_Product();", nativeQuery = true)
    List<SanPhamDTO> getAllProduct();

    @Query(value = "CALL update_product(:id_sanpham,:gia);", nativeQuery = true)
    Object getIdSPUpdate(@Param("id_sanpham") long id_sanpham, @Param("gia") int gia); // idsanpham , idgia , idgiasanpham

    @Query(value = "CALL getProductById(:id_sanpham);", nativeQuery = true)
    SanPhamDTO getProductById(@Param("id_sanpham") long id_sanpham); // idsanpham , idgia , idgiasanpham

    @Query(value = "CALL search_Product_ByName(:name);", nativeQuery = true)
    List<SanPham> searchSanPhamByName(@Param("name") String name);
}
