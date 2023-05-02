package net.nhonam.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.nhonam.springboot.Entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    // all crud database methods
}


