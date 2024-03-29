package mendes44.github.com.cliente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilterFilterRegistrationBean(){
        String apiURL = "http://localhost:4200";

       CorsConfiguration configuration = new CorsConfiguration();
       configuration.addAllowedOrigin(apiURL);
       configuration.addAllowedHeader("*");
       configuration.addAllowedMethod("*");

       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter (source);
    }
}
