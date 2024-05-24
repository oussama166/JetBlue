package com.jetblue.jetblue_server.Configuration.Assets;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    //    ==================== SECRET kEY ====================//
    private final String SECRET = "amV0Ymx1ZV9zZXJ2ZXI3cEp6djNwOGRwS2dFOTlSNXNVMTNqaipuVUpCUVFqd2RRN0JMUkM1Q1BWJlRoV3JwUG05clV3dWtwVkdLa2dPTCZSWGszSDBQNzlKVVFDM0xLQTFFdlc1NmpvITNGUkwqRTFtUVM4RlhKcUstdDlMREdsVEdGTTdaZ09kWCFveDJ4akFmdXRjIURkTT9uaXNabUh3UnZrVktPMTVPOHRUTWp4WXBobHQ/cE1TTjUyYTRzMzFjMUNwNXJNbUY2SFJINzVBa1VGRA==";
    //    ==================== SECRET kEY ====================//

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<String, Object>(), userDetails);
    }


    public boolean isTokenValid(String token , UserDetails userDetails) {
        final String userName = extractClaim(token, Claims::getSubject);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        //        GET USERNAME (email)
        //        SET DATE OF ISSUE CURRENT DATE
        //        SET EXPIRATION DATE CURRENT DATE + 1 H

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3_600_000L))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
