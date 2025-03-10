package com.reservationStore.reservationServiceProject.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="partner")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Partner {

    @Id
    @Column(name = "partner_id")
    private String partnerId;

    @Column(name = "partner_name")
    private String partnerName;

    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private List<Store> stores;
}