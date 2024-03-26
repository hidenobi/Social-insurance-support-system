package com.ptit.insurance.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "income")
@Entity
public class Income {
    @Id
    @NotNull
    private String id;
    @JoinColumn(name = "insuranceCode")
    @ManyToOne
    @NotNull
    private Personal personal;
    @NotNull
    private int income;
    @NotNull
    private Date beginAt;
    private Date endAt;

}
