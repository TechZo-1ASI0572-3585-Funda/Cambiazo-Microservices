package com.cambiazo.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "f504d11617821d4d6e371dcffd085af94941a9b8219539e7ea9462b7dcb92248";

    private static Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public static String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public static boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public static boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private static boolean isTokenExpired(String token) {
        final Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
