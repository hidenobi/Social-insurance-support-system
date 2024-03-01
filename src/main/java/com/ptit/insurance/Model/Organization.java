package com.ptit.insurance.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Organization {
    @Id
    @NotNull("Id không được null")
    private String id;
    @NotNull("Tên không được null")
    private String name;
    @NotNull("Mã số thuế không được null")
    private String taxCode;
    @NotNull("Địa chỉ không được null")
    private String address;
    @NotNull("Không được null")
    private String representative;
    @NotNull("Email không được null")
    private String email;
    @NotNull("Số điện thoại không được null")
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "idInsuranceAgency")
    private InsuranceAgency idInsuranceAgency;
}
