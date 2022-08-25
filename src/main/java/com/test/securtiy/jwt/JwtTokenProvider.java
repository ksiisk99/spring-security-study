package com.test.securtiy.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String secretKey="QWERTYUIOPASDFGHJKLZXCVBNM1234567890";
    private final Long ACCESS_EXPIRE_TIME=60*1000L;
    private final Long REFRESH_EXPIRE_TIME=2*60*1000L;
    private Key key;

    public String createToken(String id, String authority) {
        Claims claims = Jwts.claims();
        claims.put("id", id);
        claims.put("authority", authority);
        key= Keys.secretKeyFor(SignatureAlgorithm.HS256);
        System.out.println("KEY: "+key);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(new Date().getTime() + ACCESS_EXPIRE_TIME))
                .signWith(key).compact();
    }
    public boolean validateToken(String token){


        return true;
    }
}
