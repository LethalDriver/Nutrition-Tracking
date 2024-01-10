package org.mwdziak;
import org.mwdziak.domain.NutritionalGoals;
import org.mwdziak.domain.User;
import org.mwdziak.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
@EntityScan(basePackages = {"org.mwdziak.domain"})
@SpringBootApplication
public class UsersDaysApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersDaysApplication.class, args);
    }

}