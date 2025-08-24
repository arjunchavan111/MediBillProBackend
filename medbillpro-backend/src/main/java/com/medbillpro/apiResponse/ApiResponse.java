package com.medbillpro.apiResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor  // Generates default no-args constructor
@AllArgsConstructor // Generates constructor with all fields as parameters
public class ApiResponse<T> {
    private boolean success;   // true if request was successful
    private String message;    // descriptive message
    private String errorMessage;
    private T data;            // generic payload
    private int status;     // optional status string
}
