package com.reservationStore.reservationServiceProject.controller;

import com.reservationStore.reservationServiceProject.domain.Partner;
import com.reservationStore.reservationServiceProject.domain.Store;
import com.reservationStore.reservationServiceProject.dto.PartnerInput;
import com.reservationStore.reservationServiceProject.dto.PartnerStoreInput;
import com.reservationStore.reservationServiceProject.repository.PartnerRepository;
import com.reservationStore.reservationServiceProject.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//파트너 점장 로그인, 파트너 점장 회원가입, 파트너 매장등록

@Controller
public class PartnerController {

    @Autowired
    private PartnerRepository partnerRepository;
    private StoreRepository storeRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @GetMapping("/partner/register")
    public String register() {
        return "partner/register";
    }

    @GetMapping("/partner/register_success")
    public String registerSuccess(@ModelAttribute("partnerName") String partnerName) {
        return "partner/register_success";
    }

    @RequestMapping("/partner/storeList")
    public String showStoreList() {
        return "partner/storeList";
    }

    @RequestMapping("/partner/login")
    public String partnerLogin() {
        return "partner/login";
    }

    //파트너 등록
    @PostMapping("/partner/register")
    public String registerSubmit(PartnerInput partnerInput) {

        String encodedPassword = passwordEncoder.encode(partnerInput.getPassword());

        Partner partnerEntity = new Partner();
        partnerEntity.setPartnerId(partnerInput.getPartnerId());
        partnerEntity.setPartnerName(partnerInput.getPartnerName());
        partnerEntity.setPhoneNumber(partnerInput.getPhoneNumber());
        partnerEntity.setPassword(encodedPassword);

        partnerRepository.save(partnerEntity);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                partnerEntity.getPartnerId(), partnerEntity.getPassword()
        );
        //이렇게 했는데도 데이터가 저장이 안되고 /partner/login창으로 넘어감
        //인증문제 -> 이미 인증 안해도 들어갈 수 있다고 허락해줬음
        return "partner/register_success";
    }

    //파트너 회원만 매장 등록 가능 - 인증, 파트너 id 연결확인
    @PostMapping("/partner/store_register")
    public String partnerStoreSubmit(PartnerStoreInput partnerStoreInput,
                                     @AuthenticationPrincipal org.springframework.security.core.userdetails.User userPrincipal) {

        String partnerId = userPrincipal.getUsername();
        Partner partner = partnerRepository.findByPartnerId(partnerId)
                .orElseThrow(() -> new IllegalStateException());

        Store store = new Store();
        store.setPartner(partner);
        store.setStoreName(partnerStoreInput.getStoreName());
        store.setStoreAddress(partnerStoreInput.getStoreAddress());
        store.setStoreDescription(partnerStoreInput.getStoreDescription());

        storeRepository.save(store);

        return "partner/storeList";
    }
}