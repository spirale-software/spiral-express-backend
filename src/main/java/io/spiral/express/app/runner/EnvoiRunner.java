package io.spiral.express.app.runner;

import io.spiral.express.app.repository.EnvoiAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Profile("dev")
public class EnvoiRunner implements ApplicationRunner {

    @Autowired
    private EnvoiAppRepository envoiAppRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        envoiAppRepository.findAll().forEach(item -> System.out.println(item.getRapportQuai()));
    }

}
