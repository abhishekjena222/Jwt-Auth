package com.jwt.jwtAuth.controller;

import com.jwt.jwtAuth.helper.JwtUtil;
import com.jwt.jwtAuth.model.JwtRequest;
import com.jwt.jwtAuth.model.JwtResponse;
import com.jwt.jwtAuth.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import static sun.awt.SunGraphicsCallback.log;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/token")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        try {
            //authenticat
            System.out.println("try");
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));

        }catch (UsernameNotFoundException e){
            System.out.println("catch user not found");
            e.printStackTrace();
            throw new Exception("User not found Bad credentials");
        }catch (BadCredentialsException e){
            System.out.println("catch bad cred");
//            e.printStackTrace();
            throw new Exception("Bad credentials");
        }

        System.out.println("fine");
        //get userdatails
        UserDetails userDetails = this.customUserDetailService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails); //create token
        System.out.println("JWT: "+token);

        //return respons token
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
