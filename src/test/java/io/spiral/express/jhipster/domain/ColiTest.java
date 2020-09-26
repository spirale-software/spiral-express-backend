package io.spiral.express.jhipster.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.spiral.express.jhipster.web.rest.TestUtil;

public class ColiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Coli.class);
        Coli coli1 = new Coli();
        coli1.setId(1L);
        Coli coli2 = new Coli();
        coli2.setId(coli1.getId());
        assertThat(coli1).isEqualTo(coli2);
        coli2.setId(2L);
        assertThat(coli1).isNotEqualTo(coli2);
        coli1.setId(null);
        assertThat(coli1).isNotEqualTo(coli2);
    }
}
