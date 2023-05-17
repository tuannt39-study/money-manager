package vn.techmaster.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests()
            .requestMatchers(PathRequest.toH2Console()).permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .and()
            .csrf().ignoringRequestMatchers(PathRequest.toH2Console())
            .and()
            .headers().frameOptions().sameOrigin()
            .and()
            .build();
    }

}
