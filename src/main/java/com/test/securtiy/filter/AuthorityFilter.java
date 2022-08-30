package com.test.securtiy.filter;

import com.test.securtiy.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class AuthorityFilter {
    private final JwtTokenProvider jwtTokenProvider;

    public boolean isAuthorize(String token){
        System.out.println("AUTHORIZE FILTER");
        String authority=jwtTokenProvider.getAuthority(token);
        System.out.println(authority);

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
