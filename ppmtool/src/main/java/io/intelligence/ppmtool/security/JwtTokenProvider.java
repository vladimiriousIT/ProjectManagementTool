package io.intelligence.ppmtool.security;

import io.intelligence.ppmtool.domain.User;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.intelligence.ppmtool.security.SecurityConstraints.SECRET_KEY;
import static io.intelligence.ppmtool.security.SecurityConstraints.TOKEN_EXPIRATION_TIME;

@Component
public class JwtTokenProvider {
  //Generate the Token
  public String generateToken(Authentication authentication){
    User user = (User) authentication.getPrincipal();

    Date now = new Date(System.currentTimeMillis());
    Date expiryDate = new Date(now.getTime() + TOKEN_EXPIRATION_TIME);
    Map<String,Object> claims = new HashMap<>();

    claims.put("id", Long.toString(user.getId()));
    claims.put("username", user.getUsername());
    claims.put("fullname", user.getUsername());

    String userId = Long.toString(user.getId());
    return Jwts.builder()
      .setSubject(userId)
      .setClaims(claims)
      .setIssuedAt(now)
      .setExpiration(expiryDate)
      .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
      .compact();
  }
  //Validate the Token
  public boolean validateToken(String token){
    try{
      Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
      return true;
    } catch (SignatureException ex){
      System.out.println("Invalid JWT Signature!");
    } catch (MalformedJwtException ex){
      System.out.println("Invalid JWT Token!");
    } catch (ExpiredJwtException ex){
      System.out.println("Expired JWT Token!");
    } catch (UnsupportedJwtException ex){
      System.out.println("Unsupported JWT Token!");
    } catch (IllegalArgumentException ex){
      System.out.println("JWT claims string is empty!");
    }
    return false;
  }
  //Get user Id from  Token
  public Long getUserIdFromJWT(String token){
    Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    String id = (String) claims.get("id");
    return Long.parseLong(id);
  }
}
