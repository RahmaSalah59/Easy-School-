package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
public class Address extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int ID;

    @NotBlank(message = "address must not be empty")
    private String Address1;


    private String Address2;

    @NotBlank(message = "city must not be empty")
    @Size(min=5, message="City must be at least 5 characters long")
    private String city;

    @NotBlank(message = "state must not be empty")
    @Size(min=5, message="State must be at least 5 characters long")
    private String state;

    @NotBlank(message = "Zip-Code must not be empty")
    @Pattern(regexp="(^$|[0-9]{5})",message = "Zip Code must be 5 digits")
    private int zip_code;
}
