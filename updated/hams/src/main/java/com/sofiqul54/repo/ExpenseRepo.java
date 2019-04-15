package com.sofiqul54.repo;

import com.sofiqul54.entity.Exprnses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepo extends JpaRepository<Exprnses, Long> {
}
