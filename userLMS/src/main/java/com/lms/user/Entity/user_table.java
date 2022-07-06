package com.lms.user.Entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_table")
public class user_table {

    @Id
    int registrationNumber;
    String firstName;
    String lastName;
    String accountType;
    String password;
    String email;
}
