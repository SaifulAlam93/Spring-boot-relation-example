package com.practice.SpringBootCRUD.repository;

import com.practice.SpringBootCRUD.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query(value = "select * from user where address = :address and name=:name", nativeQuery = true)
    List<User> getAllByActiveTrue(@Param("address") String address, @Param("name") String name);

    List<User> findByAddress(String add);
}
