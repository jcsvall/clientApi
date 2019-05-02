package sv.com.jcsvall.clientApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class ClientApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApiApplication.class, args);
	}
	
//	@Bean
//	public FilterRegistrationBean filterRegistrationBean() {
//	    final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//
//	    final CorsConfiguration corsConfiguration = new CorsConfiguration();
//	    corsConfiguration.setAllowCredentials(true);
//	    corsConfiguration.addAllowedOrigin("*");
//	    corsConfiguration.addAllowedHeader("*");
//	    corsConfiguration.addAllowedMethod("*");
//
//	    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//
//	    CorsFilter corsFilter = new CorsFilter(urlBasedCorsConfigurationSource);
//	    FilterRegistrationBean registration = new FilterRegistrationBean(corsFilter);
//	    registration.addUrlPatterns("/*");
//	    registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
//	    return registration;
//	}
	
//	@Bean
//	  public FilterRegistrationBean corsFilter() {
//	    final CorsConfiguration config = new CorsConfiguration();
//	    config.setAllowCredentials(true);
//	    config.addAllowedOrigin("*");
//	    config.addAllowedHeader("*");
//	    config.addAllowedMethod("*");
//	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", config);
//	    final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//	    bean.setOrder(0);
//	    return bean;
//	  }
	

//	@EnableWebSecurity
//	@Configuration
//	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
////			http.csrf().disable()
////			        .authorizeRequests()
////			        .antMatchers(HttpMethod.POST, "/user").permitAll()
////			        .antMatchers(HttpMethod.POST, "/user2").permitAll()
////			        .antMatchers(HttpMethod.POST, "/user3").permitAll()
////			        .antMatchers(HttpMethod.GET, "/hello").permitAll()
////			        .anyRequest().authenticated();
//						
//			
////			http.
////            csrf().disable().
////            sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
////            and().
////            authorizeRequests().
////            antMatchers(HttpMethod.GET, "/rest/v1/session/login").permitAll().
////            antMatchers(HttpMethod.POST, "/rest/v1/session/register").permitAll().
////            antMatchers(HttpMethod.GET, "/rest/v1/session/logout").authenticated().
////            antMatchers(HttpMethod.GET, "/rest/v1/**").hasAuthority("ADMIN").
////            antMatchers(HttpMethod.POST, "/rest/v1/**").hasAuthority("USER").
////            antMatchers(HttpMethod.PATCH, "/rest/v1/**").hasAuthority("USER").
////            antMatchers(HttpMethod.DELETE, "/rest/v1/**").hasAuthority("USER").
////            anyRequest().permitAll();
//			
//		}
//		
//			}

}
