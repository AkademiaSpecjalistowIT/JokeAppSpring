package pl.akademiaspecjalistowit.jokeappspring.configuration;

import java.net.http.HttpClient;
import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;

@Configuration
public class Config {

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }

    @Bean
    public Random random(){
        return new Random();
    }
}
