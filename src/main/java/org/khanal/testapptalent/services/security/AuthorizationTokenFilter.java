package org.khanal.testapptalent.services.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class AuthorizationTokenFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String tokenHeader;
    private final String requiredTokenValue;

    public AuthorizationTokenFilter(@Value("${auth.token.header}") String tokenHeader, @Value("${auth.token.value}")
            String requiredTokenValue) {
        this.tokenHeader = tokenHeader;
        this.requiredTokenValue = requiredTokenValue;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        logger.debug("processing authentication for " + httpServletRequest.getRequestURL());
        final String secret = httpServletRequest.getHeader(tokenHeader);
        if (secret != null && secret.equals(requiredTokenValue)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid authentication token");
            //logger.error("Not authenticated activity");
        }
    }
}
