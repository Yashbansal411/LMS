package com.lms.user.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
@Data
public class Book {

    @Id
    private int bookId;
    @NotBlank
    private String bookName;
    @NotEmpty(message = "Author name can't be empty")
    private String authorName;
    @Min(value=1, message = "number of copies can't be less than 1")
    private int numberOfCopies;
}
