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
    @NotNull("Mã bảo hiểm không được null")
    private String insuranceCode;
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

    public boolean Check(){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if(getId().isEmpty()) {
            System.out.println("Id is empty");
            return false;
        }
        if(getName().isEmpty()){
            System.out.println("Name is empty");
            return false;
        }
        if(getInsuranceCode().isEmpty()){
            System.out.println("InsuranceCode is empty");
            return false;
        }
        if(getEmail().isEmpty()||!getEmail().matches(emailRegex))
        {
            System.out.println("Email is not format");
            return false;
        }
        if(getPhoneNumber().isEmpty()){
            System.out.println("PhoneNumber is empty");
            return false;
        }
        if(getTaxCode().isEmpty()){
            System.out.println("TaxCode is empty");
            return false;
        }
        if(getRepresentative().isEmpty()){
            System.out.println("Representative is empty");
            return false;
        }
        if(getAddress().isEmpty()){
            System.out.println("Address is empty");
            return false;
        }
        return true;
    }
}
