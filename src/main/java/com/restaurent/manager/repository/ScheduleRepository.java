package com.restaurent.manager.repository;

import com.restaurent.manager.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    @Query("SELECT s FROM Schedule s JOIN s.tableRestaurants tr " +
            "WHERE tr.id = :tableRestaurantId AND s.bookedDate = :bookedDate " +
            "AND s.intendTime BETWEEN :startTime AND :endTime")
    List<Schedule> findSchedulesByTableAndDateRange(
            @Param("tableRestaurantId") Long tableRestaurantId,
            @Param("bookedDate") LocalDate bookedDate,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );
}
