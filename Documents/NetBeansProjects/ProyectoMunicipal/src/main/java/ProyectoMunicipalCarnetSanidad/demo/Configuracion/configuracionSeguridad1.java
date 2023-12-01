//package ProyectoMunicipalCarnetSanidad.demo.Configuracion;
//
//import org.apache.catalina.User;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.stereotype.Component;
//
//@EnableWebSecurity
//@Configuration
//@Component
//public class configuracionSeguridad1 {
//
//    @Bean
//    public UserDetailsService users() {
//
//        System.out.println("UserDetailsService AAAAAAAAAAAAAAAa");
//        UserDetails user = org.springframework.security.core.userdetails.User.builder()
//                .username("Diego")
//                .password("mierda")
//                .roles("USER")
//                .build();
//        UserDetails admin = org.springframework.security.core.userdetails.User.builder()
//                .username("admin")
//                .password("password")
//                .roles("USER", "ADMIN")
//                .build();
//        System.out.println("User Details - Diego: " + user);
//        System.out.println("User Details - Admin: " + admin);
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        System.out.println("Se hizo un security filterchain");
//        httpSecurity
//                .csrf(csrfCustomizado -> csrfCustomizado.disable())
//                .cors(corsCustomizado -> corsCustomizado.disable())
//                .authorizeHttpRequests(
//                        (solicitudes) -> {
//                            solicitudes.requestMatchers("/aut/entrar", "/municipal/hola  ", "/aut/hola").permitAll();
//                            solicitudes.anyRequest().authenticated();
//
////                    autorizados.requestMatchers("/usuarios/generate-token", "/usuarios/guardar", "/usuarios/saludar").permitAll()
////                            .requestMatchers(HttpMethod.OPTIONS).permitAll().anyRequest().authenticated();
//                        })
//                .exceptionHandling(ex -> ex.authenticationEntryPoint((sol, rep, aut) -> rep.sendError(rep.SC_UNAUTHORIZED, "Inautorizado")))
//                .formLogin(
//                        (form)
//                        -> form.loginPage("http://localhost:4200/login")
//                                .successHandler((sol, res, aut) -> res.setStatus(200))
//                                .usernameParameter("usuario")
//                                .passwordParameter("contra")
//                                .permitAll())
//                .logout(log -> log.permitAll());;
//
//        return httpSecurity.build();
//    }
//
//}
