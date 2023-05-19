package kosariev.cs22m.dev.labs.customers;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan()
@EnableJpaRepositories
public class CustomersApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomersApplication.class, args);
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("cs22m-dev-labs")
                    .description("Practice labs")
                    .version("v0.5")
                )
                .externalDocs(new ExternalDocumentation()
                    .description("Practice labs requirements")
                    .url("https://classroom.google.com/u/0/c/NTYyNjM5MTY2NTA1/a/NTk4Mzc0NTUwNTA1/details")
                );
    }
}
