package org.redolf.model;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String phone;
    private String country;
    private String dob;
}
