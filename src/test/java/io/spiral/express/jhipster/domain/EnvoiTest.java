package io.spiral.express.jhipster.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.spiral.express.jhipster.web.rest.TestUtil;

public class EnvoiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Envoi.class);
        Envoi envoi1 = new Envoi();
        envoi1.setId(1L);
        Envoi envoi2 = new Envoi();
        envoi2.setId(envoi1.getId());
        assertThat(envoi1).isEqualTo(envoi2);
        envoi2.setId(2L);
        assertThat(envoi1).isNotEqualTo(envoi2);
        envoi1.setId(null);
        assertThat(envoi1).isNotEqualTo(envoi2);
    }
}
