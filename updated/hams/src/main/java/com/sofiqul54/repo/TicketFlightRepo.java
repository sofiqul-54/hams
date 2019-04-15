package com.sofiqul54.repo;

import com.sofiqul54.entity.Role;
import com.sofiqul54.entity.TicketFlightInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketFlightRepo extends JpaRepository<TicketFlightInfo, Long> {
    Optional<TicketFlightInfo> findByTicketNo(String ticketNo);
    boolean existsByTicketNo(String ticketNo);
}
