package com.example.carrental.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "date_start_rent")
    private LocalDate startRent;

    @Column(name = "date_end_rent")
    private LocalDate endRent;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @Column(name = "is_paid")
    private boolean isPaid;

    @Column(name = "renting_status")
    private RentStatus rentStatus;
}

