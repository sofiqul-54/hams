package com.sofiqul54.repo;

import com.sofiqul54.entity.GroupLeaderSummary;
import com.sofiqul54.entity.Groupleader;
import com.sofiqul54.entity.Pilgrim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupleaderSummaryRepo extends JpaRepository<GroupLeaderSummary, Long> {
GroupLeaderSummary findByLeaderName(String leaderName);
GroupLeaderSummary findByTotalCommission(Double totalCommission);
    GroupLeaderSummary findByGroupleader(Groupleader groupleader);
}
