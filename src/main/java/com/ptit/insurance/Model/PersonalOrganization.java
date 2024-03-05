package com.ptit.insurance.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor

public class PersonalOrganization {
    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "insurancePersonal")
    private Personal personal;
    @ManyToOne
    @JoinColumn(name = "insuranceOrganization")
    private Organization organization;
}
