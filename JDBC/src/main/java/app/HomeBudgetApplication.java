package app;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class HomeBudgetApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeBudgetApplication.class, args);
    }
}
