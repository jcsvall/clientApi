package sv.com.jcsvall.clientApi.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import sv.com.jcsvall.clientApi.JWTAuthorizationFilter;
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable()
    .authorizeRequests()
    .antMatchers(HttpMethod.POST, "/user").permitAll()
    .antMatchers(HttpMethod.POST, "/user2").permitAll()
    .antMatchers(HttpMethod.POST, "/user3").permitAll()
    .antMatchers(HttpMethod.POST, "/create").permitAll()
    .antMatchers(HttpMethod.GET, "/hello").hasRole("ADMIN")
    .anyRequest().authenticated()
    .and()
    .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	
	//.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
