package com.example.carrental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name= "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany(mappedBy = "equipmentList")
    private List<Car> carList = new ArrayList<>();
}
