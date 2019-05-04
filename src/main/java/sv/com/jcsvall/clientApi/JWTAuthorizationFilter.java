package sv.com.jcsvall.clientApi;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import sv.com.jcsvall.clientApi.configurations.Constantes;
import sv.com.jcsvall.clientApi.configurations.HandleMyExeptionMessage;

public class JWTAuthorizationFilter extends OncePerRequestFilter{
	
	@Autowired 
	HttpSession session;
	
	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";
	private final String SECRET = "secrectKey";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
			if (existeJWTToken(request, response)) {
				
				Claims claims = validateToken(request);
				partOfToken(claims);
				createUserNameSession(request,claims);
				if (claims.get("authorities") != null) {
					setUpSpringAuthentication(claims);
				} else {
					SecurityContextHolder.clearContext();
				}
			}else {
				SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		
		
	}
	
	private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(HEADER);
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)) {
			return false;
		}
		return true;
	}
	
	private void setUpSpringAuthentication(Claims claims) {
		@SuppressWarnings("unchecked")		
		List<String> authorities = (List<String>) claims.get("authorities");

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(auth);

	}
	
	private Claims validateToken(HttpServletRequest request) throws IOException {
		Claims claims = null;
		try {
			String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
			claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
		} catch (JwtException e) {
			System.err.println(e);
			//ExpiredJwtException
			//throw new WebApplication
			//throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token expirado");
			//FilterErrorResponse errorResponse = new FilterErrorResponse(e);

            
            //response.getWriter().write(convertObjectToJson(errorResponse));
            throw new HandleMyExeptionMessage("ERROR DE TOKEN: "+e.getMessage());
		} catch (Exception e) {
			System.err.println(e);
		}
		return claims;
	}
	
	public void partOfToken(Claims claims) {
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("actual time: " + claims.getIssuedAt());
		System.out.println("Expiration: " + claims.getExpiration());
		
	}
	
	private void createUserNameSession(HttpServletRequest request, Claims claims) {
		HttpSession session = request.getSession();
		if (session.getAttribute(Constantes.USUARIO) == null) {
			session.setAttribute(Constantes.USUARIO, claims.getSubject());
		}
	}
	
	
}
