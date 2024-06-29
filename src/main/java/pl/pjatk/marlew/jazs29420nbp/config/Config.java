package pl.pjatk.marlew.jazs29420nbp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.pjatk.marlew.jazs29420nbp.model.AverageExchange;
import pl.pjatk.marlew.jazs29420nbp.model.QueryNBP;
import pl.pjatk.marlew.jazs29420nbp.model.Rate;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
