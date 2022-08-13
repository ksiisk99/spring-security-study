package com.test.securtiy.auth;

//로그인 진행이 완료되면 시큐리티 세션을 만들어 Security ContextHolder에 저장한다.
//세션이 들어갈 오브젝트 타입은 Authentication객체여야 한다.
//Authentication 안에 User정보가 있어야함
//User오브젝트타입은 UserDetails 타입 객체가 되야함

import com.test.securtiy.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//Security Sessions => Authentication => UserDetails(PrincipalDetails)
@AllArgsConstructor
public class PrincipalDetails implements UserDetails {

    private User user;

    //유저의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority>collect=new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        //현재시간 - 로그인시간 => 지정된 시간을 초과하면 return false
        return true;
    }
}
