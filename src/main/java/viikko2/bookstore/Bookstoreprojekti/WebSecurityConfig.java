package viikko2.bookstore.Bookstoreprojekti;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//b. Add Spring Security configuration class which will secure all URLs (requires
	//authentication in all URLs) 

@Configuration 
@EnableWebSecurity
//aktivoi apring aecurity web service tuen
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
@Override
//WebSecurityConfig class contains a method
//configure(HttpSecurity)that defines which URL
//paths are secured and the path for login form
protected void configure(HttpSecurity http) throws Exception{
	http
	.authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out
    .and()
	.authorizeRequests().antMatchers("/delete/{id},/edit/{id}").hasRole("ADMIN")
     .anyRequest().authenticated()
 	.and()
 .formLogin()
     .loginPage("/login")
     .defaultSuccessUrl("/booklist")
     .permitAll()
     .and()
 .logout()
     .permitAll();	
	
}

@Bean
@Override
public UserDetailsService userDetailsService() {
	
	List<UserDetails> users = new ArrayList();
	
	UserDetails user = User.withDefaultPasswordEncoder()
			.username("user")
			.password("password")
			.roles("USER")
			.build();
	
	users.add(user);
	
	
	user = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("password")
            .roles("USER", "ADMIN")
            .build();
	
	users.add(user);
	
	return new InMemoryUserDetailsManager(users);
			
}
}
