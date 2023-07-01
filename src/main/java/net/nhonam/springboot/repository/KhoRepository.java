package net.nhonam.springboot.repository;

import net.nhonam.springboot.DTO.PhieuNhapDTO;
import net.nhonam.springboot.Entity.Kho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface KhoRepository extends JpaRepository<Kho, Long> {
    Kho findByTenKho(String tenKho);

    @Query(value = "CALL search_KhoByName(:name);", nativeQuery = true)
    List<Kho> search_Product_ByName(@Param("name") String name);
}
