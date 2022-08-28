package com.test.securtiy.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String secretKey="QWERTYUIOPASDFGHJKLZXCVBNM1234567890XCVBNM1234567890";
    private final Long ACCESS_EXPIRE_TIME=60*1000L;
    private final Long REFRESH_EXPIRE_TIME=2*60*1000L;
    private Key key;

    @PostConstruct
    public void init(){
        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        key=Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String id, String authority) {
        Claims claims = Jwts.claims();
        claims.put("id", id);
        claims.put("authority", authority);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(new Date().getTime() + ACCESS_EXPIRE_TIME))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean validateToken(String token){

        try {
            return !Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .before(new Date());
        }catch(JwtException e){
            System.out.println("유효하지 않은 토큰");
            return false;
        }catch(IllegalArgumentException e){
            System.out.println("NULL 토큰");
            return false;
        }
    }

    public String getAuthority(String token){
        if(validateToken(token)){
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("authority",String.class);
        }return null;
    }


}
