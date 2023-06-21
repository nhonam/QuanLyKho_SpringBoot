package net.nhonam.springboot.repository;

import net.nhonam.springboot.Entity.Kho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface KhoRepository extends JpaRepository<Kho, Long> {
    Kho findByTenKho(String tenKho);
}
