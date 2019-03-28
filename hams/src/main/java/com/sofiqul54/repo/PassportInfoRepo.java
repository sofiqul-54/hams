package com.sofiqul54.repo;

import com.sofiqul54.entity.PassportInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassportInfoRepo extends JpaRepository<PassportInfo, Long> {
    Optional<PassportInfo> findByPassportNo(String passportNo);
    boolean existsByPassportNo(String passportNo);
}
