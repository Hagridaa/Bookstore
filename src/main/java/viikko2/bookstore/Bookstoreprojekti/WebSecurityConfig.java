package viikko2.bookstore.Bookstoreprojekti;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import viikko2.bookstore.Bookstoreprojekti.web.UserDetailServiceImpl;

//b. Add Spring Security configuration class which will secure all URLs (requires
	//authentication in all URLs) 

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
//aktivoi apring aecurity web service tuen
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	 @Autowired
	 private UserDetailServiceImpl userDetailsService;
	   

//WebSecurityConfig class contains a method
//configure(HttpSecurity)that defines which URL
//paths are secured and the path for login form
@Override
protected void configure(HttpSecurity http) throws Exception{
	http
	.authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out
    .and()
	//.authorizeRequests().antMatchers("/delete/{id}","/edit/{id}").hasAuthority("ADMIN")
     //.anyRequest().authenticated()
 	//.and()
 .formLogin()
     //.loginPage("/login")
     .defaultSuccessUrl("/booklist")
     .permitAll()
     .and()
 .logout()
     .permitAll();	
	
}

@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

}
}
