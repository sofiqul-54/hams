package com.sofiqul54.repo;


import com.sofiqul54.entity.BookingSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingSummaryRepo extends JpaRepository<BookingSummary, Long> {

}
