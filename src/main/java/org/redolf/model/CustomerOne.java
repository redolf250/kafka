package org.redolf.model;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOne {
    private String id;
    private String firstName;
    private String lastName;
    private  String email;
}
