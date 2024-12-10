package com.cg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
// to requesting data from the user, temp to hold data of auth, store data in auth object
    private String username ;
    private String password;
}
