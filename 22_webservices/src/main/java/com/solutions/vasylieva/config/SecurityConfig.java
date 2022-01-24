//package com.solutions.vasylieva.config;
//
////import com.solutions.vasylieva.support.AuthFilter;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.AuthenticationConverter;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.csrf.CsrfFilter;
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.filter.OncePerRequestFilter;
////import com.solutions.vasylieva.support.CustomAccessDeniedHandler;
//
//@EnableWebSecurity
//@EnableGlobalMethodSecurity
//@AllArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private UserDetailsService userDetailsService;
//
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(characterEncodingFilter(), CsrfFilter.class);
////        http.addFilterBefore(authenticationFilter(), CsrfFilter.class);
//
//        http.csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/login").permitAll()
//                .antMatchers("/api/registration").anonymous()
//                .antMatchers("/api/users").authenticated()
//                .antMatchers("/api/users/**").hasAuthority("ADMIN")
//                .anyRequest().authenticated();
////                .and()
////                .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .loginProcessingUrl("/login")
////                .usernameParameter("login")
////                .passwordParameter("password")
////                .successHandler(loginSuccessHandler())
////                .and()
////                .logout()
////                .logoutUrl("/logout").permitAll()
////                .deleteCookies("JSESSIONID");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(12);
//    }
//
////    @Bean
////    public AuthenticationSuccessHandler loginSuccessHandler() {
////        return new LoginSuccessHandler();
////    }
//
////    @Bean
////    public OncePerRequestFilter authenticationFilter() {
////        return new AuthFilter();
////    }
//
//    @Bean
//    public CharacterEncodingFilter characterEncodingFilter() {
//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding("UTF-8");
//        filter.setForceEncoding(true);
//        return filter;
//    }
////
////    @Bean
////    public AccessDeniedHandler accessDeniedHandler() {
////        return new CustomAccessDeniedHandler();
////    }
//}
