package com.sri.GoEcho.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;
     @Autowired
     private MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username=null,JWT_Token=null;
        String authHeader = request.getHeader("Authorization");

        if(authHeader!=null && authHeader.startsWith("Bearer "))
         {
            JWT_Token=authHeader.substring(7);
            username=jwtService.extractUserName(JWT_Token);
         }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {


            UserDetails userDetails=myUserDetailsService.loadUserByUsername(username);
            if(jwtService.validateToken(JWT_Token,userDetails))
            {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }
        filterChain.doFilter(request,response);

    }
}
