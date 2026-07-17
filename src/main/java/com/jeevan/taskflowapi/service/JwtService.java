package com.jeevan.taskflowapi.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;



@Service
public class JwtService {


    @Value("${JWT_SECRET}")
    private String SECRET_KEY;


    public String generateToken(String email){


        Map<String,Object> claims =
                new HashMap<>();


        return Jwts.builder()

                .claims()
                .add(claims)

                .subject(email)

                .issuedAt(
                        new Date(System.currentTimeMillis())
                )

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60 * 24
                        )
                )

                .and()

                .signWith(getKey())

                .compact();

    }



    private SecretKey getKey(){

        byte[] keyBytes =
                SECRET_KEY.getBytes();

        return Keys.hmacShaKeyFor(keyBytes);

    }



    public String extractUserName(String token){

        return extractClaim(
                token,
                Claims::getSubject
        );

    }



    public boolean validateToken(
            String token,
            UserDetails userDetails
    ){

        String username =
                extractUserName(token);


        return username.equals(
                userDetails.getUsername()
        )
                &&
                !isTokenExpired(token);

    }




    private boolean isTokenExpired(String token){

        return extractClaim(
                token,
                Claims::getExpiration
        )
                .before(new Date());

    }




    private <T> T extractClaim(
            String token,
            Function<Claims,T> resolver
    ){

        Claims claims =
                extractAllClaims(token);


        return resolver.apply(claims);

    }




    private Claims extractAllClaims(String token){

        return Jwts.parser()

                .verifyWith(getKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();

    }


}