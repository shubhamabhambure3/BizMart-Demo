package bizmart.backend.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY = "bizmart-secret-key-for-jwt-authentication-demo-project";

	private Key getSigningKey() {
		SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

		return key;
	}

	public String generateToken(String email,String role) {
		return Jwts.builder()
				.subject(email)
				.claim("role", role)
				.issuedAt(new Date())
				.expiration(
						new Date(
								System.currentTimeMillis() 
								+ 1000 * 60 * 60))
				.signWith(getSigningKey())
				.compact();
	}

	public String extractUsername(String token) {

		return Jwts.parser().verifyWith((javax.crypto.SecretKey) getSigningKey()).build().parseSignedClaims(token)
				.getPayload().getSubject();
	}
	
	public String extractRole(String token) {

	    return Jwts.parser()
	            .verifyWith(
	                    (javax.crypto.SecretKey)
	                            getSigningKey())
	            .build()
	            .parseSignedClaims(token)
	            .getPayload()
	            .get("role", String.class);
	}

	public boolean isTokenValid(String token, String email) {

		String username = extractUsername(token);

		return username.equals(email);
	}
}
