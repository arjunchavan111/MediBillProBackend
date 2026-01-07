package com.medbillpro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                       // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor          // Generates a no-argument constructor
@AllArgsConstructor         // Generates an all-argument constructor
public class Employee {
    private String name;
    private String mobile;
    private String email;
    private String address;
}
