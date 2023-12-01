package com.example.userservicemsp.vo;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @NotNull , @Size 쓰기위해
 * <dependency>
 *     <groupId>javax.validation</groupId>
 *     <artifactId>validation-api</artifactId>
 *     <version>2.0.1.Final</version>
 * </dependency>
 * 해당 디펜던시 추가함.
 */

@Data
public class RequestUser {

    @NotNull(message = "Name can not be null")
    @Size(min = 2, message = "Name not be less than two character")
    @Email
    private String email;

    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "Email not be less than two character")
    private String name;


    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be equal or grater than eight character")
    private String pwd;
}
