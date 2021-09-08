package com.gs2e.audiencier.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gs2e.audiencier.web.rest.TestUtil;

public class AudienceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Audience.class);
        Audience audience1 = new Audience();
        audience1.setId(1L);
        Audience audience2 = new Audience();
        audience2.setId(audience1.getId());
        assertThat(audience1).isEqualTo(audience2);
        audience2.setId(2L);
        assertThat(audience1).isNotEqualTo(audience2);
        audience1.setId(null);
        assertThat(audience1).isNotEqualTo(audience2);
    }
}
