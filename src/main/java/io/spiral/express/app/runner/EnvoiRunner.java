package io.spiral.express.app.runner;

import io.spiral.express.app.domain.Envoi;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Profile("dev")
public class EnvoiRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    List<Envoi> getEnvois() {

        return Arrays.asList();
    }
}
