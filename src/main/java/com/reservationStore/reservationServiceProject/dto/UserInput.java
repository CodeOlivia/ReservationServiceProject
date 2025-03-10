package com.reservationStore.reservationServiceProject.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {

    private String userName;
    private String userEmail;
    private String password;
}
