package net.nhonam.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.nhonam.springboot.Entity.User;

import java.util.List;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
    // all crud database methods

    //findBy...
    // ... =userName(method in Entity)
    User findByuserName(String userName);

    @Query(value = "SELECT u FROM User u WHERE u.Role = 'EMPLOYEE' and u.status= true ")
    List<User> findAllEmployee();

    // Native SQL
//    @Query(value = "SELECT u FROM User u WHERE and u. like :name", nativeQuery = true)
//    User searchUser(@Param("email") String email,@Param("SDT") String SDT, @Param("name") String name);


    @Query(value = "CALL search_user(:email, :name, :sdt);", nativeQuery = true)
    List<User> searchUser(@Param("email") String email,@Param("name") String name,@Param("sdt") String sdt);
}


