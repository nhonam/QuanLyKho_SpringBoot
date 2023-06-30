package net.nhonam.springboot.repository;

import net.nhonam.springboot.DTO.PhieuNhapDTO;
import net.nhonam.springboot.DTO.PhieuNhapDetailDTO;
import net.nhonam.springboot.DTO.SanPhamDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.nhonam.springboot.Entity.PhieuNhapKho;

import java.util.List;

@Repository
public interface PhieuNhapKhoRepository extends JpaRepository<PhieuNhapKho, Long> {
    // all crud database methods

    @Query(value = "CALL allPhieuNhap_admin();", nativeQuery = true)
    List<PhieuNhapDTO> getPhieuNhapByAdmin();

    @Query(value = "CALL allPhieuNhap_employee(:id);", nativeQuery = true)
    List<PhieuNhapDTO> getPhieuNhapByEmployee(@Param("id") long id_user);

    @Query(value = "CALL chi_tiet_phieu_nhap(:id);", nativeQuery = true)
    List<PhieuNhapDetailDTO> getPhieuNhapDetail(@Param("id") long id_phieunhap);
}
