package rs.importer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class RsImporter {

    public static void main(String[] args) {
        SpringApplication.run(RsImporter.class, args);
    }
}
