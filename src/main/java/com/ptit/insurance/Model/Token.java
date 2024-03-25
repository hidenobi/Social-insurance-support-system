package com.ptit.insurance.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @NotNull
    private String value;
    @NotNull
    private Date end;
    @NotNull
    private String name;

}
