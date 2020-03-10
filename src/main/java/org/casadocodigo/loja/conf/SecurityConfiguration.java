package org.casadocodigo.loja.conf;

import org.casadocodigo.loja.daos.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	
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
	
	@Override //configuracao para o spring poder usar a classe usuariodao
	// o método userDetailsService espera receber um objeto que implemente uma interface com este mesmo nome. Faremos então classe UsuarioDAO implementar a interface e adicionar à classe os método que precisam ser implementados.
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDAO)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
