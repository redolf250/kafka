package org.redolf.model;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTwo {
    private String id;
    private String gender;
    private String phone;
    private String country;
    private String dob;
}
