//package com.sparx.railway.ticketapp.backend.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("user123"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin123"))
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
//    @Bean
//    protected PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(
//                        request -> request.requestMatchers("/login").permitAll()
//                                .requestMatchers("/**").authenticated()
//                )
//                .formLogin(Customizer.withDefaults())
//                .logout(config -> config
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login"))
//                .build();
//    }
//
//}
//package com.sparx.railway.ticketapp.backend.security;
//
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("user123"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin123"))
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
//    @Bean
//    protected PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(
//                        request -> request.requestMatchers("/login").permitAll()
//                                .requestMatchers("/**").authenticated()
//                )
//                .formLogin(Customizer.withDefaults())
//                .logout(config -> config
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login"))
//                .build();
//    }
//
//}
