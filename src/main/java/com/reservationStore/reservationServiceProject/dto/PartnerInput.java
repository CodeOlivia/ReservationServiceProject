package com.reservationStore.reservationServiceProject.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerInput {

    private String partnerId;
    private String partnerName;
    private String password;
    private String phoneNumber;
}
