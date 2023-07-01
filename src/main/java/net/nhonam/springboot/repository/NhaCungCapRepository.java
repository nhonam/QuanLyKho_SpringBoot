package net.nhonam.springboot.repository;

import net.nhonam.springboot.Entity.Kho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.nhonam.springboot.Entity.NhaCungCap;

import java.util.List;

@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Long> {
    // all crud database methods


    @Query(value = "CALL search_NhaCungCapbyName(:name);", nativeQuery = true)
    List<NhaCungCap> search_NhaCungCapbyName(@Param("name") String name);
}


