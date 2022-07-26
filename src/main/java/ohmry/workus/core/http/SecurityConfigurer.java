package ohmry.workus.core.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security에 대한 정책을 정의하는 클래스
 * @version 0.1
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigurer {
    @Bean
    public SecurityFilterChain httpConfigure(HttpSecurity http) throws Exception {
        http.formLogin().disable();
        http.csrf().ignoringAntMatchers("/console/**/**").disable();
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webConfiture() {
        return (web) -> web.ignoring().antMatchers("/static/**");
    }
}
