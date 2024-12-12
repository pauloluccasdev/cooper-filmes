package com.cooper.filme.main.repository;

import com.cooper.filme.main.models.ApprovalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRecordRepository extends JpaRepository<ApprovalRecord, Long> {
}
