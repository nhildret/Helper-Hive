// package com.hive.capstone.security;

// import com.hive.capstone.users.CustomUserDetailsService;
// import jakarta.servlet.DispatcherType;
// import org.springframework.context.annotation.Bean;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import
// org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
// import
// org.springframework.security.web.authentication.AuthenticationSuccessHandler;
// import
// org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

// @Autowired
// private CustomUserDetailsService userDetailsService;

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {
// HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
// requestCache.setMatchingRequestParameterName(null);
// http
// .csrf(AbstractHttpConfigurer::disable)
// .authorizeHttpRequests((authorize) -> authorize
// .dispatcherTypeMatchers(DispatcherType.FORWARD,
// DispatcherType.ERROR).permitAll()
// .requestMatchers("/signup", "/css/**", "/js/**", "/images/**").permitAll()
// .requestMatchers("/volunteer/**")
// .hasAnyAuthority("Volunteer", "Admin", "OrganizationRepresentative")
// .requestMatchers("/org-rep/**").hasAnyAuthority("Admin",
// "OrganizationRepresentative")
// .requestMatchers("/admin/**").hasAuthority("Admin")
// .anyRequest().authenticated())
// .formLogin((form) -> form
// .loginPage("/login")
// .successForwardUrl("/dashboard")
// .failureUrl("/login?error=true")
// .permitAll())
// .logout((logout) -> logout
// .logoutUrl("/logout")
// .logoutSuccessUrl("/login?logout")
// .permitAll())
// .exceptionHandling((x) -> x.accessDeniedPage("/403"))
// .requestCache((cache) -> cache.requestCache(requestCache));

// return http.build();
// }

// protected void configure(AuthenticationManagerBuilder auth) throws Exception
// {
// auth.userDetailsService(userDetailsService)
// .passwordEncoder(passwordEncoder());
// }

// @Bean
// public PasswordEncoder passwordEncoder() {
// return new BCryptPasswordEncoder();
// }

// @Bean
// public AuthenticationSuccessHandler successHandler() {
// SimpleUrlAuthenticationSuccessHandler handler = new
// SimpleUrlAuthenticationSuccessHandler();
// handler.setUseReferer(true);
// return handler;
// }
// }