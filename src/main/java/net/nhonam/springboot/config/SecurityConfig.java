package net.nhonam.springboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/api/v1/register").permitAll()
// <<<<<<< HEAD
// =======
// //                .requestMatchers("/api/v1/user").authenticated()
// >>>>>>> 0332110d43092d82d61b6a6ed4e978da28a4edc1
                .requestMatchers("/api/v1/user").permitAll()
                .and().httpBasic();
        return httpSecurity.build();
    }
}
