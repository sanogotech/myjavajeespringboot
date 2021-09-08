package com.gs2e.audiencier.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gs2e.audiencier.web.rest.TestUtil;

public class JuridictionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Juridiction.class);
        Juridiction juridiction1 = new Juridiction();
        juridiction1.setId(1L);
        Juridiction juridiction2 = new Juridiction();
        juridiction2.setId(juridiction1.getId());
        assertThat(juridiction1).isEqualTo(juridiction2);
        juridiction2.setId(2L);
        assertThat(juridiction1).isNotEqualTo(juridiction2);
        juridiction1.setId(null);
        assertThat(juridiction1).isNotEqualTo(juridiction2);
    }
}
