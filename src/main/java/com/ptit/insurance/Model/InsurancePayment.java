package com.ptit.insurance.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.sql.Time;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class InsurancePayment {
    @jakarta.persistence.Id
    @NotNull
    private String Id;
    @ManyToOne
    @JoinColumn(name = "insuranceCode")
    @NotNull
    private Personal personal;
    @NotNull
    private Time beginAt;
    @NotNull
    private Time endAt;
    private int montDebt;
    private int totalPayment;
    private boolean isPayment;


}
