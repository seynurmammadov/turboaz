package az.code.myauto;

import az.code.myauto.repositories.CustomRepo.CustomRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class MyautoApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(MyautoApplication.class, args);
    }
}
