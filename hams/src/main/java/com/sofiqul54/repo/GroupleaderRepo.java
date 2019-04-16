package com.sofiqul54.repo;

import com.sofiqul54.entity.Groupleader;
import com.sofiqul54.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GroupleaderRepo extends JpaRepository<Groupleader, Long> {

    boolean existsByEmail(String email);

    Optional<Groupleader> findByLeaderName(String leaderNamed);

    Groupleader findByCommission(Integer commission);


    boolean existsByLeaderName(String leaderName);

}
