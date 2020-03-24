package com.itechart.container.spring.users.security;

import com.itechart.container.spring.users.exception.IncorrectJwtAuthenticationException;
import com.itechart.container.spring.users.generated.model.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

import static com.itechart.container.spring.users.entity.enums.UserEntityRole.fromAuthority;
import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static java.util.Base64.getEncoder;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Data
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${app.jwt.secret.key}")
    private String secretKey;

    private static final String ROLE_KEY = "role";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String EXPIRED_OR_INVALID_JWT_TOKEN = "Expired or invalid JWT token";

    private static final int TOKEN_EXPIRATION_TIME = 3_600_000;
    private static final int REFRESH_TOKEN_EXPIRATION_TIME = 86_400_000;

    @PostConstruct
    protected void init() {
        secretKey = getEncoder().encodeToString(secretKey.getBytes());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public TokenResponse refreshTokens(final String refreshToken) {
        var authentication = getAuthentication(refreshToken);
        if (authentication == null) {
            throw new IllegalStateException();
        }

        return createToken(authentication);
    }

    public TokenResponse createToken(final Authentication authentication) {
        var now = new Date();
        var tokenValidity = new Date(now.getTime() + TOKEN_EXPIRATION_TIME);
        var refreshTokenValidity = new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION_TIME);

        var accessToken = genToken(authentication, now, tokenValidity);
        var refreshToken = genToken(authentication, now, refreshTokenValidity);

        var tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessTokenExpirationDate(tokenValidity.toInstant());
        tokenResponse.setRefreshTokenExpirationDate(refreshTokenValidity.toInstant());
        return tokenResponse;
    }

    UsernamePasswordAuthenticationToken getAuthentication(final String token) {
        var claims = getClaims(token);
        var username = getUsername(token);

        var userDetails = new UserDetailsImpl();
        userDetails.setRole(fromAuthority(claims.get(ROLE_KEY).toString()));
        userDetails.setId(UUID.fromString(username));
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    String resolveToken(final HttpServletRequest request) {
        var bearerToken = request.getHeader(AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    boolean validateToken(final String token) {
        var claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

    private String genToken(final Authentication authentication, final Date now, final Date validity) {
        var userDetails = (UserDetailsImpl) authentication.getPrincipal();
        var claims = Jwts.claims();
        claims.put(ROLE_KEY, ((UserDetailsImpl) authentication.getPrincipal()).getRole());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(HS256, secretKey)
                .compact();
    }

    private String getUsername(final String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch (JwtException | IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            throw new IncorrectJwtAuthenticationException(EXPIRED_OR_INVALID_JWT_TOKEN);
        }
    }

    private Claims getClaims(final String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            throw new IncorrectJwtAuthenticationException(EXPIRED_OR_INVALID_JWT_TOKEN);
        }
    }

}
