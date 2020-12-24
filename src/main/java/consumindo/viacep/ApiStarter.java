package consumindo.viacep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients //habilita o Feign na aplicação
@SpringBootApplication
public class ApiStarter {
    public static void main(String[] args) {
        SpringApplication.run(ApiStarter.class);
    }
}