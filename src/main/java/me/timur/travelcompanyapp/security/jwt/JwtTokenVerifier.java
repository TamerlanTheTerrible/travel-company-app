package me.timur.travelcompanyapp.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Temurbek Ismoilov on 28/01/22.
 */

@Component
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final String secretKey = "securesecuresecuresecuresecuresecure";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.replace("Bearer ", "");

        try {
            String username = getUsername(token);

            Set<SimpleGrantedAuthority> grantedAuthorities = getAuthorities(token);

            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }

        filterChain.doFilter(request, response);
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public Set<SimpleGrantedAuthority> getAuthorities(String token) {
        var authorities = (List<Map<String, String>>) getClaims(token).get("authorities");
        return  authorities.stream()
                .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                .collect(Collectors.toSet());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
    }

}
