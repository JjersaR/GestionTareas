package com.microservice.student.config.filter;

import java.io.IOException;
import java.util.Collection;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.microservice.student.util.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtTokenValidator extends OncePerRequestFilter {

  private JwtUtils jwtUtils;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (jwtToken != null) {
      jwtToken = jwtToken.substring(7);

      DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

      String username = jwtUtils.extractUsername(decodedJWT);
      String authoritiesString = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();

      Collection<? extends GrantedAuthority> authorities = AuthorityUtils
          .commaSeparatedStringToAuthorityList(authoritiesString);

      SecurityContext context = SecurityContextHolder.getContext();

      Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

      context.setAuthentication(authentication);

      SecurityContextHolder.setContext(context);
    }
    filterChain.doFilter(request, response);
  }
}
