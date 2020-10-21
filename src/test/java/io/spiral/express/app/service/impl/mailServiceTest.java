package io.spiral.express.app.service.impl;

import io.spiral.express.jhipster.SpiralExpressApp;
import io.spiral.express.jhipster.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest(classes = SpiralExpressApp.class)
public class mailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private ApplicationContext ctx;

    @Test
    void test() {

        JavaMailSender bean = ctx.getBean(JavaMailSender.class);
        System.out.println(bean.getClass());
        mailService.sendEmail("lapigerard@yahoo.fr", "testSubject", "testContent", false, false);
    }
}
