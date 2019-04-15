package com.sofiqul54.repo;

import com.sofiqul54.entity.PassportInfo;
import com.sofiqul54.entity.VisaInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisaInfoRepo extends JpaRepository<VisaInfo, Long> {
    Optional<VisaInfo> findByVisaNo(String visaNo);
    boolean existsByVisaNo(String visaNo);
}
