package net.nhonam.springboot.config;

import net.nhonam.springboot.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import java.beans.Customizer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //config singleton
    Response singleton = Response.getInstance();
//singleton.setData("Example data");
//singleton.setStatus("Example status");
//singleton.setMessage("Example message");

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        // We don't need CSRF for this example
////        httpSecurity.csrf().disable()
////        httpSecurity.authorizeRequests().antMatchers("/").permitAll();
//                // dont authenticate this particular request
//        httpSecurity.csrf().disable().authorizeRequests().antMatchers( "/login", "/register","/verify").permitAll().
//                // all other requests need to be authenticated
//                        anyRequest().authenticated();
//                // make sure we use stateless session; session won't be used to
//                // store user's state.
//        httpSecurity.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        // Add a filter to validate the tokens with every request
//        httpSecurity.cors();
//        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//    }

 @Override
protected void configure(HttpSecurity httpSecurity) throws Exception {




    httpSecurity.headers().frameOptions().disable().and()
        .csrf().disable()
        .authorizeRequests()
            .antMatchers( "/auth/*").permitAll(); // Cho phép truy cập vào URL "/login" mà không authorization

     httpSecurity.csrf().disable().authorizeRequests().anyRequest().authenticated(); // Yêu cầu token cho các URL khác

    httpSecurity
        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    httpSecurity.cors();
    httpSecurity.addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
}
}
