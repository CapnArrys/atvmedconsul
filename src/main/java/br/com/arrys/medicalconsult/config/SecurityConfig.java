package br.com.arrys.medicalconsult.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/usuarios").hasAnyRole("ADMIN", "SECRETARIO")
                        .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasAnyRole("ADMIN", "SECRETARIO")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuarios/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();

    }

    @Autowired
    public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser("Fulano").password(passwordEncoder.encode("12345")).roles("ADMIN")
                .and()
                .withUser("Beltrano").password(passwordEncoder.encode("12345")).roles("SECRETARIO")
                .and()
                .withUser("Sicrano").password(passwordEncoder.encode("12345")).roles("PACIENTE");


    }

}
