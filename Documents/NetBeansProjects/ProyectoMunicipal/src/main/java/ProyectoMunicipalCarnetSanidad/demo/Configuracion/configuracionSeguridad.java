package ProyectoMunicipalCarnetSanidad.demo.Configuracion;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
@Component
public class configuracionSeguridad {

    @Bean
    public UserDetailsService userDetailsService() {
        
        UserDetails user = User
                .builder()
                .username("gaa")
                .password("{noop}mierda")
                .roles("ADMIN")
                .build();
        
        UserDetails user2 = User
                .builder()
                .username("Diego")
                .password("{noop}Quezada")
                .roles("USER")
                .build();
       
        return new InMemoryUserDetailsManager(user,user2);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            System.out.println("Se hizo un security filterchain");
            httpSecurity
                    .csrf(csrfCustomizado -> csrfCustomizado.disable())
                    
                    .authorizeHttpRequests(
                            (solicitudes) -> solicitudes

                                    .requestMatchers("/aut/entrar", "aut/login","aut/hola")
                                    .permitAll()
                                    .requestMatchers(HttpMethod.OPTIONS)
                                    .permitAll()
                                    .anyRequest()
                                    .authenticated()

                    )
                    
                    .formLogin(
                            (form) -> form
                                    .loginPage("/aut/login") 
                                    .loginProcessingUrl("/aut/login")
                                    .failureHandler((sol,res,aut)->res.sendError(res.SC_BAD_REQUEST,"Credenciales invalidas"))
                                    .successHandler((sol, res, aut) -> res.setStatus(res.SC_OK))
                                    .permitAll())
                    .logout(log -> log.permitAll());


            return httpSecurity.build();
    }

}
