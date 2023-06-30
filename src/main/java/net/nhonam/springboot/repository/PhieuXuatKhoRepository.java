package net.nhonam.springboot.repository;

import net.nhonam.springboot.DTO.PhieuNhapDTO;
import net.nhonam.springboot.DTO.PhieuXuatDTO;
import net.nhonam.springboot.DTO.PhieuXuatDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.nhonam.springboot.Entity.PhieuXuatKho;

import java.util.List;

@Repository
public interface PhieuXuatKhoRepository extends JpaRepository<PhieuXuatKho, Long> {
    // all crud database methods


    @Query(value = "CALL getAllPhieuXuatByAdmin();", nativeQuery = true)
    List<PhieuXuatDTO> getAllPhieuXuatByAdmin();

    @Query(value = "CALL getAllPhieuXuatBy_Employee(:id);", nativeQuery = true)
    List<PhieuXuatDTO> getAllPhieuXuatByIdUser(@Param("id") long idUser);

    @Query(value = "CALL PhieuXuatDetailById(:id);", nativeQuery = true)
    List<PhieuXuatDetailDTO> PhieuXuatDetailById(@Param("id") long id);
}
