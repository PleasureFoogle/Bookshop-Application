package yesgroup.myapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Qualifier("myUserDetailsService")
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/author").hasRole("USER")
                .antMatchers("/insertAuthors").hasRole("USER")
                .antMatchers("/book").hasRole("USER")
                .antMatchers("/insertBooks").hasRole("USER")
                .antMatchers("/bookshop").hasRole("USER")
                .antMatchers("/insertBookshops").hasRole("USER")
                .antMatchers("/clearData").hasRole("USER")
                .antMatchers("/").hasRole("USER")
                .antMatchers("register").permitAll()
                .antMatchers("error").permitAll()
                .antMatchers("/resources/**").permitAll()
                .and().formLogin()
                .loginPage("/login").permitAll()
                .and().logout().permitAll();

        http.csrf().disable();

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
