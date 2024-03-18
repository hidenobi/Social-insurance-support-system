package com.ptit.insurance.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exemptionlevel")
@Entity
public class ExemptionLevel {
    @Id
    @NotNull
    private String id;
    @JoinColumn(name = "insuranceCode")
    @ManyToOne
    @NotNull
    private Personal personal;
    private float exemptionLevel;
    @NotNull
    private Time beginAt;
    private Time endAt;
    @NotNull
    private String urlImg;
}
