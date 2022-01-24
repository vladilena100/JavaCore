//package com.solutions.vasylieva.support;
//
//import com.solutions.vasylieva.model.User;
////import com.solutions.vasylieva.services.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class AuthFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
////    @Autowired
////    CustomUserDetailsService customUserDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        final String login = request.getHeader("login");
//        final String password = request.getHeader("password");
////        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//        User authentication = (User) SecurityContextHolder.getContext().getAuthentication();
//
//        if (login != null && password != null && authentication != null) {
//            final boolean matches = passwordEncoder.matches(password, authentication.getPassword());
//            final boolean equals = login.equals(authentication.getLogin());
//            if (matches && equals) {
//                filterChain.doFilter(request, response);
//            } else {
//                response.sendRedirect("/api/login");
//            }
//        } else {
//            filterChain.doFilter(request, response);
//        }
//    }
//}
