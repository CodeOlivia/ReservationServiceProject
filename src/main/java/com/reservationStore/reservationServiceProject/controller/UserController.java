package com.reservationStore.reservationServiceProject.controller;


import com.reservationStore.reservationServiceProject.domain.User;
import com.reservationStore.reservationServiceProject.dto.UserInput;
import com.reservationStore.reservationServiceProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//이용자 로그인, 이용자 회원가입, 이용자 매장 예약

@Controller
public class UserController {

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/user/register")
    public String register() {
        return "user/register";
    }

    @GetMapping("/user/login")
    public String userLogin() {
        return "/";
    }

    //이용자 등록
    @PostMapping("/user/register")
    public String userRegisterSubmit(UserInput userInput) {

        String encodedPassword = passwordEncoder.encode(userInput.getPassword());

        User userEntity = new User();
        userEntity.setUserName(userInput.getUserName());
        userEntity.setUserEmail(userInput.getUserEmail());
        userEntity.setPassword(encodedPassword);

        userRepository.save(userEntity);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userEntity.getUserId(), userEntity.getPassword()
        );
        return "user/register_success";
    }
}

