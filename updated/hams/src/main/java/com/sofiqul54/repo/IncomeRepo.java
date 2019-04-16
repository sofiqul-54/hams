package com.sofiqul54.repo;

import com.sofiqul54.entity.Income;
import com.sofiqul54.entity.Pilgrim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {
Pilgrim findByPilgrimId(Long id);


}
