package pdp.uz.appnewssites2.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import pdp.uz.appnewssites2.entity.Role;


import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {
    private static final long expireTime = 1000*360*101;
    private static final String secretSuz = "maxfiysuzHechKimBilmasin";

    public String generateToken(String username, Role role){
        Date expireDate = new Date(System.currentTimeMillis()+expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles", role.toString())
                .signWith(SignatureAlgorithm.HS256, secretSuz)
                .compact();
        return token;
    }

    public String getEmailFromToken(String token){
        try {
            String tokens = Jwts
                    .parser()
                    .setSigningKey(secretSuz)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return tokens;
        }catch (Exception e){
            return null;
        }
    }
}
