package com.reservationStore.reservationServiceProject.Security;


import com.reservationStore.reservationServiceProject.service.CustomPartnerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

    //순환 의존성 문제 발생
    @Autowired
    @Lazy
    private final CustomPartnerLoginService customPartnerLoginService;

    @Autowired
    public SecurityConfig(CustomPartnerLoginService customPartnerLoginService) {
        this.customPartnerLoginService = customPartnerLoginService;
    }

    //로그인, 로그아웃, 인증자만 화면에 들어갈 수 있게
    // partner/register 인증 필요 없는 걸로 했는데도 왜 login창으로 연결?

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/partner/login", "/user/login", "/store/list",
                                "/user/register", "/partner/register", "/partner/register_success",
                                "/user/register_success").permitAll()
                        .requestMatchers("/**").authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/partner/login")
                        .loginProcessingUrl("/partner/login")
                        .defaultSuccessUrl("/partner/register_success", true)
                        .permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/user/login")
                        .loginProcessingUrl("/user/login")
                        .defaultSuccessUrl("/user/register_success", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder
                = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(customPartnerLoginService)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }

    @Bean
    @Lazy
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}