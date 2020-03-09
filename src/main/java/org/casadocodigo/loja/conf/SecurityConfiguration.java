package org.casadocodigo.loja.conf;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	    .antMatchers("/resources/**").permitAll() //permite
	    .antMatchers("/carrinho/**").permitAll()
	    .antMatchers("/pagamento/**").permitAll()
	    .antMatchers("/produtos/form").hasRole("ADMIN") //nao permite, somente quem for admin
	    .antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN") 
	    .antMatchers(HttpMethod.GET, "/produtos").hasRole("ADMIN")
	    .antMatchers("/produtos/**").permitAll()
	    .antMatchers("/").permitAll()
	    .anyRequest().authenticated() //qualquer outra requisicao autenticar
	    .and().formLogin(); //enviar form login
	}
	
}
