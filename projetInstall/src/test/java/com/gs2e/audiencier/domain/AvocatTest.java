package com.gs2e.audiencier.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gs2e.audiencier.web.rest.TestUtil;

public class AvocatTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Avocat.class);
        Avocat avocat1 = new Avocat();
        avocat1.setId(1L);
        Avocat avocat2 = new Avocat();
        avocat2.setId(avocat1.getId());
        assertThat(avocat1).isEqualTo(avocat2);
        avocat2.setId(2L);
        assertThat(avocat1).isNotEqualTo(avocat2);
        avocat1.setId(null);
        assertThat(avocat1).isNotEqualTo(avocat2);
    }
}
