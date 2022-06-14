package com.example.carrental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(
        name = "Car.findByDate",
        query = "SELECT *  FROM CARS C WHERE " +
                "ID NOT IN " +
                "(SELECT R.CAR_ID FROM RENTS R WHERE " +
                "(:START_RENT BETWEEN R.DATE_START_RENT AND R.DATE_END_RENT) OR" +
                "(:END_RENT BETWEEN R.DATE_START_RENT AND R.DATE_END_RENT) OR " +
                "(:START_RENT >= R.DATE_START_RENT AND :END_RENT <= DATE_END_RENT  )" +
                "AND C.CATEGORY = :CAR_CLASS)",
        resultClass = Car.class
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "cars")
//@builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "registration")
    private String registration;

    @Column(name = "model")
    private String model;

    @Column(name = "company")
    private String company;

    @Column(name = "category")
    private String category;

    @Column(name = "gearbox")
    private String gearBox;

    @Column(name = "dailycost")
    private BigDecimal dailyCost;

    @Column(name = "isAvailable")
    private boolean isAvailable;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_CAR_EQUIPMENT",
            joinColumns = {@JoinColumn(name = "CAR_ID")},
            inverseJoinColumns = {@JoinColumn(name = "EQUIPMENT_ID")}
    )
    private List<Equipment> equipmentList = new ArrayList<>();

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "car",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)

    private List<Rent> rentList = new ArrayList<>();


    public Car(Long id, String registration, String model, String company, String category, String gearBox, BigDecimal dailyCost, boolean isAvailable) {
        this.id = id;
        this.registration = registration;
        this.model = model;
        this.company = company;
        this.category = category;
        this.gearBox = gearBox;
        this.dailyCost = dailyCost;
        this.isAvailable = isAvailable;
    }
}
