package com.test.securtiy.filter;

import com.test.securtiy.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthorityFilter {
    private final JwtTokenProvider jwtTokenProvider;

    public boolean isAuthorize(String token, HttpServletResponse response) throws IOException {
        System.out.println("===AUTHORIZE FILTER===");
        String authority=jwtTokenProvider.getAuthority(token);

        if(authority!=null && authority.equals("ADMIN")){
            return true;
        }
        return false;
    }

    public boolean isAuthorize2(HttpServletRequest request){
        String token=request.getHeader("token");

        String authority=jwtTokenProvider.getAuthority(token);
        System.out.println("AUTHORITY: "+authority);

        if(authority.equals("ADMIN")){
            return true;
        }
        return false;
    }
}
