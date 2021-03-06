package com.tax.sec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration
   extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	@Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	    auth.inMemoryAuthentication().withUser("user").password("1234").roles("USER")
//	          .and().withUser("admin").password("1234").roles("ADMIN","USER");
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username as principal, password as credential, active from users where username=?")
		.authoritiesByUsernameQuery("select username as principal, role as role from user_role where username=?")
		.rolePrefix("ROLE_").passwordEncoder(new Md5PasswordEncoder());
	  }
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.formLogin().loginPage("/login");
	    http.authorizeRequests().antMatchers("/companies", "/taxes").hasRole("USER");
	    http.authorizeRequests().antMatchers("/saveCompany", "/addCompany").hasRole("ADMIN");
	    http.exceptionHandling().accessDeniedPage("/403");
	  }
}