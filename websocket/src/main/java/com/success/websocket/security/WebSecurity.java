package com.success.websocket.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.success.websocket.utils.Constants;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    /*
     * Allow websocket connections to go thru here.. will be validated in websocket security during inbound channel...
     */
    http.authorizeRequests()
        .antMatchers(Constants.ALLOWED_URLS_LIST.toArray(new String[0]))
        .permitAll()
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/p/mylogin")
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    //        .and()
    //        .addFilterBefore(getAuthFilterBean(), UsernamePasswordAuthenticationFilter.class);
    // enable auth filters for non-websocket related requests (for rest controllers if any)
    //    http.csrf().disable(); //for non-origin requests
//    http.headers().frameOptions().disable(); // this is needed for h2
  }

  @Bean
  AuthFilter getAuthFilterBean() {
    return new AuthFilter();
  }
}
