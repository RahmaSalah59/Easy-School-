package com.example.demo.Security;

import com.example.demo.Entity.Person;
import com.example.demo.Entity.Roles;
import com.example.demo.repository.PersonRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonAuthentication implements AuthenticationProvider {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        Person user = personRepository.findPersonByEmail(email);
        if(user != null && user.getPersonId()> 0 && passwordEncoder.matches(password,user.getPwd())){
            return  new UsernamePasswordAuthenticationToken
            (user.getName() , null , GetRoles(user.getRoles()));
        }
        else
            throw new BadCredentialsException("Invalid credentials!");
    }

    public List<GrantedAuthority> GetRoles(Roles role){
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        return list;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
