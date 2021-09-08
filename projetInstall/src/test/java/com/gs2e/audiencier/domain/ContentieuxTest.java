package com.gs2e.audiencier.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gs2e.audiencier.web.rest.TestUtil;

public class ContentieuxTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contentieux.class);
        Contentieux contentieux1 = new Contentieux();
        contentieux1.setId(1L);
        Contentieux contentieux2 = new Contentieux();
        contentieux2.setId(contentieux1.getId());
        assertThat(contentieux1).isEqualTo(contentieux2);
        contentieux2.setId(2L);
        assertThat(contentieux1).isNotEqualTo(contentieux2);
        contentieux1.setId(null);
        assertThat(contentieux1).isNotEqualTo(contentieux2);
    }
}
