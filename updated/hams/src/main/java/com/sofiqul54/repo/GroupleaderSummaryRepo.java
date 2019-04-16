package com.sofiqul54.repo;

import com.sofiqul54.entity.GroupLeaderSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupleaderSummaryRepo extends JpaRepository<GroupLeaderSummary, Long> {
GroupleaderSummaryRepo findByLeaderName(String leaderName);

}
