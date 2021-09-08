package com.gs2e.audiencier.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gs2e.audiencier.web.rest.TestUtil;

public class RequerantTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Requerant.class);
        Requerant requerant1 = new Requerant();
        requerant1.setId(1L);
        Requerant requerant2 = new Requerant();
        requerant2.setId(requerant1.getId());
        assertThat(requerant1).isEqualTo(requerant2);
        requerant2.setId(2L);
        assertThat(requerant1).isNotEqualTo(requerant2);
        requerant1.setId(null);
        assertThat(requerant1).isNotEqualTo(requerant2);
    }
}
