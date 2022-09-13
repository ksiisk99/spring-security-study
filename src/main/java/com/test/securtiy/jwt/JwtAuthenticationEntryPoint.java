package com.test.securtiy.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exception=(String)request.getAttribute("exception");
        System.out.println("EXCEPTION?: "+exception);
        if(exception!=null){
            if(exception.equals("AuthorizeError")){
                //권한 리다이렉트
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }else{
                //인증 리다이렉트 이미 JwtAUthenticationFilter에서 처리해서 효과는 없을거임
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }
}
