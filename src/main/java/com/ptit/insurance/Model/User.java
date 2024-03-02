package com.ptit.insurance.Model;

import com.ptit.insurance.Lib.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @NotNull("Mã y tế không được null")
    private String insuranceCode;
    @NotNull("Mật khẩu không được null")
    private String passworđ;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String insuranceCode,String passworđ){
        this.insuranceCode=insuranceCode;
        this.passworđ=passworđ;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.passworđ;
    }

    @Override
    public String getUsername() {
        return this.insuranceCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
