package com.ptit.insurance.Model;


import com.ptit.insurance.Lib.TypeInsurance;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;

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
    private boolean isForeigner ;
    private boolean verifyPhoneNumber;
    private float exemptionLevel;
    private String timeMethodPayment;
    private TypeInsurance typeInsurance = TypeInsurance.NONE;
    private int income;
    public boolean Check(){
        System.out.println("Check: "+getIdPersonal());
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if(fullName==null) {
            System.out.println("FullName is null");
            return false;
        }
        if(idPersonal==null){
            System.out.println("IdPersonal is null");
            return false;
        }
        if(insuranceCode==null){
            System.out.println("InsuranceCode is null");
            return false;
        }
        if(email==null)
        {
            System.out.println("Email is not null");
            return false;
        }
        if(phoneNumber==null){
            System.out.println("PhoneNumber is null");
            return false;
        }
        if(imgPersonal==null){
            System.out.println("ImgPersonal is null");
            return false;
        }
        if(imgBackId==null){
            System.out.println("ImgBackId is null");
            return false;
        }
        if(imgFrontId==null){
            System.out.println("ImgFrontId is null");
            return false;
        }
       if(address==null){
           System.out.println("Address is null");
           return false;
       }
       //------------------
        if(getFullName().isEmpty()) {
            System.out.println("FullName is empty");
            return false;
        }
        if(getIdPersonal().isEmpty()){
            System.out.println("IdPersonal is empty");
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
        if(getImgPersonal().isEmpty()){
            System.out.println("ImgPersonal is empty");
            return false;
        }
        if(getImgBackId().isEmpty()){
            System.out.println("ImgBackId is empty");
            return false;
        }
        if(getImgFrontId().isEmpty()){
            System.out.println("ImgFrontId is empty");
            return false;
        }
        if(getAddress().isEmpty()){
            System.out.println("Address is empty");
            return false;
        }
        return true;
    }

    public boolean CheckDeclaration(){
        if(exemptionLevel==0){
            System.out.println("Exemption Level not declared");
            return false;
        }
        if(income==0){
            System.out.println("Income not declared");
            return false;
        }
        if(timeMethodPayment==null){
            System.out.println("Time Method Payment not declared");
        }
        return true;
    }

    public boolean checkDeclaration(Personal personalCheck) {
        if(!this.idPersonal.equals(personalCheck.idPersonal)) return false;
        if(!this.address.equals(personalCheck.address)) return false;
        if(!this.imgPersonal.equals(personalCheck.imgPersonal)) return false;
        if(!this.imgFrontId.equals(personalCheck.imgFrontId)) return false;
        if(!this.imgBackId.equals(personalCheck.imgBackId)) return false;
        if(!this.phoneNumber.equals(personalCheck.phoneNumber)) return false;
        if(!this.fullName.equals(personalCheck.fullName)) return false;
        if(!this.InsuranceAgency.equals(personalCheck.InsuranceAgency)) return false;
        if(!this.email.equals(personalCheck.email)) return false;
        return true;

    }
}
