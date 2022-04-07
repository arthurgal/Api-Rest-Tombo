package br.com.arthurgaldino.apirest.Config.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    //configrações de autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }
    //configurações de autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/equipamentos").permitAll()
                .antMatchers(HttpMethod.GET, "/equipamentos/tombo").permitAll()
                .antMatchers(HttpMethod.GET, "/equipamentos/pdf").permitAll();
    }
    /*//configurações de recursos estaticos(js, imagens, etc.)
    @Override
    public void configure(WebSecurity web) throws Exception {

    }*/
}
