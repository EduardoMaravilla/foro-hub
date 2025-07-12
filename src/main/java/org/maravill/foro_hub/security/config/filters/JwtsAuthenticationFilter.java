package org.maravill.foro_hub.security.config.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.security.models.User;
import org.maravill.foro_hub.security.service.IJwtService;
import org.maravill.foro_hub.security.service.IUserService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class JwtsAuthenticationFilter extends OncePerRequestFilter {

    private final IUserService userService;
    private final IJwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwt = jwtService.extractJwtTokenFromRequest(request);
        if (jwt == null || jwt.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!jwtService.validateJwtToken(jwt)){
            filterChain.doFilter(request,response);
            return;
        }
        String username = jwtService.extractUsernameFromJwt(jwt);
        User user = userService.findUserByUsername(username);
        Collection<? extends GrantedAuthority> authorities = userService.findAuthoritiesByUser(user);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user,null,authorities);
        authenticationToken.setDetails(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
