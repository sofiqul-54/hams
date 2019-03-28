package com.sofiqul54.repo;

import com.sofiqul54.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PilgrimRepo extends JpaRepository<Pilgrim, Long> {
    Optional<Pilgrim> findByNameOrPilgrimNumber(String name, String number);
    Optional<Pilgrim> findByPilgrimNumber(String number);
    Optional<Pilgrim> findByEmail(String email);
    List<Pilgrim> findAllByPpackage(Set<Ppackage> ppackages);
    List<Pilgrim> findByGroupleader(Set<Groupleader> groupleaders);
    boolean existsByPilgrimNumber(String pilgrimNumber);
    boolean existsByEmail(String email);
}
