package com.lms.user.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    private int id;

    @NotBlank(message = "First name can't be blank")
    private String firstName;

    private String lastName;
    @NotBlank(message = "please provide account type")
    private String accountType;
    @NotBlank(message = "password can't be blank")
    private String password;

    @Email(message = "please enter proper email address")
    @Column(unique = true)
    private String email;
    private int fine;
    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id_user", referencedColumnName = "Id")
    Set<Address> address;

}
