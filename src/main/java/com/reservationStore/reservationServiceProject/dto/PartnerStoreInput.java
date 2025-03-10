package com.reservationStore.reservationServiceProject.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerStoreInput {
    private String storeName;
    private String storeAddress;
    private String storeDescription;
}
