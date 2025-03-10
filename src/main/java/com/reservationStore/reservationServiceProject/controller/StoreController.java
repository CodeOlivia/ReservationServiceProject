package com.reservationStore.reservationServiceProject.controller;


import com.reservationStore.reservationServiceProject.domain.Store;
import com.reservationStore.reservationServiceProject.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    //인증 필요 없이 보이는 매장 리스트
    @GetMapping("/store/list")
    public String showList(Model model) {
        List<Store> storeList = storeRepository.findAll();
        model.addAttribute("storeList", storeList);
        return "store/list";
    }
}
