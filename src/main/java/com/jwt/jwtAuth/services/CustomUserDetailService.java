package com.jwt.jwtAuth.services;

import com.jwt.jwtAuth.model.JwtRequest;
import com.jwt.jwtAuth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
//    @Autowired
//    private JwtRequest jwtRequest;
    @Bean
    public JwtRequest jwtRequest(){
        JwtRequest jwtRequest = null;
        return null;
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

//        if (userName.equals("Abhi")){
//            return new User("Abhi","Abhi1234",new ArrayList<>());
//        }
        if (userName!=null) {
            JwtRequest jwtRequest = userRepo.findByUsernameLike(userName);
            System.out.println("jwtRequest: "+jwtRequest);
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
            authorities.addAll(Collections.singleton(new SimpleGrantedAuthority(jwtRequest.getRole())));

            return new User(jwtRequest.getUsername(), jwtRequest.getPassword(),authorities);

        } else {
            System.out.println("User not found! ");
            throw new UsernameNotFoundException("User not found! ");
        }


    }
    
}
