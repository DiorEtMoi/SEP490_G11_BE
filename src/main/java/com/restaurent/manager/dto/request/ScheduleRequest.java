package com.restaurent.manager.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleRequest {
    String customerName;
    String customerPhone;
    String note;
    LocalDate bookedDate;
    String time;
    double deposit;
    int intendTimeMinutes;
    int numbersOfCustomer;
    List<Long> tables;
}
