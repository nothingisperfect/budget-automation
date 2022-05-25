package app;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
//@EnableJpaRepositories(basePackages = "app.repos")
//@ComponentScan("app")
public class HomeBudgetApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeBudgetApplication.class, args);
    }
}
