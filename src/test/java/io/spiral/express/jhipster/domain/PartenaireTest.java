package io.spiral.express.jhipster.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.spiral.express.jhipster.web.rest.TestUtil;

public class PartenaireTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Partenaire.class);
        Partenaire partenaire1 = new Partenaire();
        partenaire1.setId(1L);
        Partenaire partenaire2 = new Partenaire();
        partenaire2.setId(partenaire1.getId());
        assertThat(partenaire1).isEqualTo(partenaire2);
        partenaire2.setId(2L);
        assertThat(partenaire1).isNotEqualTo(partenaire2);
        partenaire1.setId(null);
        assertThat(partenaire1).isNotEqualTo(partenaire2);
    }
}
