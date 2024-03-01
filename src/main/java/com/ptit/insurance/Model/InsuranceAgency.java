package com.ptit.insurance.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class InsuranceAgency {
    @Id
    @NotNull("Id không được null")
    private String id;
    @NotNull("Tên không được null")
    private String name;
    @NotNull("Địa chỉ không được null")
    private String address;
    @NotNull("Số tài khoản không được null")
    private String accountNumber;

}
