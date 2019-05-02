package sv.com.jcsvall.clientApi.configurations;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.TextCodec;

public class JwtFilter extends GenericFilterBean {
	private String secret = "secrectKey";

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("Authorization");

		if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		} else {
			
			if (authHeader == null) {
				chain.doFilter(req, res);
			}

			if (!authHeader.startsWith("Bearer ")) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}

			final String token = authHeader.substring(7);

			try {
				final Claims claims = Jwts.parser().setSigningKey(TextCodec.BASE64.encode(secret)).parseClaimsJws(token)
						.getBody();
				request.setAttribute("claims", claims);
			} catch (final JwtException e) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}

			chain.doFilter(req, res);
		}

	}

}
