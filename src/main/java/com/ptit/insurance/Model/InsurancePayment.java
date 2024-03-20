package com.ptit.insurance.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.sql.Time;
import java.util.List;

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
    private long totalPayment;
    private boolean isPayment;
    @Transient
    private List<MonthPayment> monthPaymentList;

    public InsurancePayment(@NotNull String id, @NotNull Personal personal, int totalPayment, boolean isPayment) {
        Id = id;
        this.personal = personal;
        this.totalPayment = totalPayment;
        this.isPayment = isPayment;
    }

}
