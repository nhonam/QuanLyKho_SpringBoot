package net.nhonam.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.nhonam.springboot.Entity.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
    // all crud database methods

    //findBy...
    // ... =userName(method in Entity)
    User findByuserName(String userName);
}


