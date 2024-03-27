package com.ptit.insurance.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class InsurancePayment {
    @Id
    @NotNull
    private String Id;
    @ManyToOne
    @JoinColumn(name = "insuranceCode")
    @NotNull
    private Personal personal;
    private long totalPayment;
    private boolean isPayment;
    @Transient
    @JsonManagedReference
    private List<MonthPayment> monthPaymentList;

    public InsurancePayment(@NotNull String id, @NotNull Personal personal, int totalPayment, boolean isPayment) {
        this.Id = id;
        this.personal = personal;
        this.totalPayment = totalPayment;
        this.isPayment = isPayment;
    }

}
