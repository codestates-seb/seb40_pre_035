package stackoverflow.global.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import stackoverflow.global.security.auth.handler.AccountAccessDeniedHandler;
import stackoverflow.global.security.auth.handler.AccountAuthenticationEntryPoint;
import stackoverflow.global.security.auth.jwt.JwtTokenizer;
import stackoverflow.global.security.auth.filter.JwtVerificationFilter;
import stackoverflow.global.security.auth.handler.AccountAuthenticationFailureHandler;
import stackoverflow.global.security.auth.handler.AccountAuthenticationSuccessHandler;
import stackoverflow.global.security.auth.filter.JwtAuthenticationFilter;
import stackoverflow.global.security.auth.utils.CustomAuthorityUtils;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.*;

@Configuration
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;


    public SecurityConfiguration(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()  //h2 웹콘솔을 정상적으로 사용할 수 있도록 설정
                .and()
                .csrf().disable()           //개발 환경 이후 설정해야할
                .cors(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 생성을 하지 않도록 설정
                .and()
                .formLogin().disable()      //JSON 포맷으로 Username과 Password를 전송하는 방식을 사용할 것이므로 비활성화
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new AccountAuthenticationEntryPoint())
                .accessDeniedHandler(new AccountAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new AccountAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new AccountAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }

}
