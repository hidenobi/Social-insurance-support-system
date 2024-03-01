package com.ptit.insurance.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Personal")
public class Personal {
    @Id
    @NotNull("Mã y tế không được null")
    private String insuranceCode;
    @NotNull("Số căn cước không được null")
    private String idPersonal;
    @NotNull("Họ tên không được thiếu")
    private String fullName;
    @NotNull("Ảnh cá nhân không được thiếu")
    private String imgPersonal;
    @NotNull("Ảnh căn cước công dân mặt trước không được thiếu")
    private String imgFrontId;
    @NotNull("Ảnh căn cước công dân mặt sau không được thiếu")
    private String imgBackId;
    @NotNull("Không được thiếu địa chỉ")
    private String address;
    @NotNull("Không được thiếu email")
    private String email;
    @NotNull( "Không được thiếu số điện thoại")
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "idInsuranceAgency")
    private InsuranceAgency InsuranceAgency;
    private boolean isForeigner;
    private float exemptionLevel;
    private String timeMethodPayment;
    private int income;


}
