package com.manga.securityservice.config;

import com.manga.securityservice.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new RefererRedirectionAuthenticationSuccessHandler("/yourdefaultsuccessurl");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        DefaultRedirectStrategy authenticationSuccessHandlerRedirectStrategy = new DefaultRedirectStrategy();
        authenticationSuccessHandlerRedirectStrategy.setContextRelative(true);

        SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        authenticationSuccessHandler.setDefaultTargetUrl("/");
        authenticationSuccessHandler.setAlwaysUseDefaultTargetUrl(true);
        authenticationSuccessHandler.setRedirectStrategy(authenticationSuccessHandlerRedirectStrategy);
        //*/


        http.anonymous().and()
                .authorizeRequests()
                .antMatchers("/", "/public/**", "/oauth/authorize", "/authorize", "/oauth/login", "/login", "/oauth/login2", "/login2").permitAll()
                .and()
                .formLogin()
                .successHandler(new RefererRedirectionAuthenticationSuccessHandler("/"))
                .permitAll()
                .and()
                .logout()
                .permitAll().and().anonymous();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}