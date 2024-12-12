package com.cooper.filme.main.repository;

import com.cooper.filme.main.models.RoadMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoadMapRepository extends JpaRepository<RoadMap, Long> {

    List<RoadMap> findByClientEmail(String email);

    @Query("SELECT r FROM RoadMap r WHERE " +
            "(:status IS NULL OR r.status = :status) AND " +
            "(:email IS NULL OR r.clientEmail = :email) AND " +
            "(:date IS NULL OR r.createdAt = :date)")
    List<RoadMap> findByFilters(@Param("status") String status,
                                @Param("email") String email,
                                @Param("date") LocalDate date);
}
