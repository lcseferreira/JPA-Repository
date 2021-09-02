package com.lucasferreira.JPArepository.repositories;

import com.lucasferreira.JPArepository.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<User> findBySalaryBetween(Double minSalary, Double maxSalary, Pageable pageable);

    Page<User> findById(long id, Pageable pageable);
}
