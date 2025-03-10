package com.reservationStore.reservationServiceProject.service;


import com.reservationStore.reservationServiceProject.domain.Partner;
import com.reservationStore.reservationServiceProject.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

//인메모리 비밀번호로 로그인 오류 -> 커스터마이징 필요
@Service
public class CustomPartnerLoginService implements UserDetailsService {

    private final PartnerRepository partnerRepository;

    @Lazy
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomPartnerLoginService(PartnerRepository partnerRepository, PasswordEncoder passwordEncoder) {
        this.partnerRepository = partnerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Partner partner = partnerRepository.findByPartnerId(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 파트너가 없습니다."));

        return new org.springframework.security.core.userdetails.User(
                partner.getPartnerId(),
                partner.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_PARTNER"))
        );
    }
}
