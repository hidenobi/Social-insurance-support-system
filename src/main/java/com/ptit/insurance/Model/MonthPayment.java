package com.ptit.insurance.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "monthpayment")
@Entity
@ToString
@Setter
@Getter
public class MonthPayment {
    @Id
    @NotNull
    private String id;
    @JoinColumn(name = "income")
    @ManyToOne
    @NotNull
    private Income income;
    @JoinColumn(name = "exemptionLevel")
    @ManyToOne
    @NotNull
    private ExemptionLevel exemptionLevel;
    @NotNull
    private int monthPayment;
    private Date month;
    @JoinColumn(name = "insurancePayment")
    @ManyToOne
    @NotNull
    @JsonBackReference
    private InsurancePayment insurancePayment;
}
