package com.restaurent.manager.entity;

import com.restaurent.manager.enums.MethodPayment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bill {
    @Id
    Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    Order order;
    double total;
    LocalDate dateCreated;
    @Enumerated(EnumType.STRING)
    MethodPayment methodPayment;
}
