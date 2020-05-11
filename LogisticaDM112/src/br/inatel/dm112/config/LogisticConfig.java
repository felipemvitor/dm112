package br.inatel.dm112.config;

import br.inatel.dm112.services.LogisticServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("br.inatel.dm112")
public class LogisticConfig {
    @Bean
    public LogisticServices logisticService() {
        return new LogisticServices();
    }
}
