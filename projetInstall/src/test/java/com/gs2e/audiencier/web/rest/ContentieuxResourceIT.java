package com.gs2e.audiencier.web.rest;

import com.gs2e.audiencier.AudiencierApp;
import com.gs2e.audiencier.domain.Contentieux;
import com.gs2e.audiencier.repository.ContentieuxRepository;
import com.gs2e.audiencier.service.ContentieuxService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.gs2e.audiencier.domain.enumeration.Entite;
/**
 * Integration tests for the {@link ContentieuxResource} REST controller.
 */
@SpringBootTest(classes = AudiencierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ContentieuxResourceIT {

    private static final Entite DEFAULT_ENTITE = Entite.CIE;
    private static final Entite UPDATED_ENTITE = Entite.SODECI;

    private static final String DEFAULT_REF_CONTENTIEUX = "AAAAAAAAAA";
    private static final String UPDATED_REF_CONTENTIEUX = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_PREMIERE_AUDIENCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_PREMIERE_AUDIENCE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ContentieuxRepository contentieuxRepository;

    @Autowired
    private ContentieuxService contentieuxService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContentieuxMockMvc;

    private Contentieux contentieux;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contentieux createEntity(EntityManager em) {
        Contentieux contentieux = new Contentieux()
            .entite(DEFAULT_ENTITE)
            .refContentieux(DEFAULT_REF_CONTENTIEUX)
            .datePremiereAudience(DEFAULT_DATE_PREMIERE_AUDIENCE);
        return contentieux;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contentieux createUpdatedEntity(EntityManager em) {
        Contentieux contentieux = new Contentieux()
            .entite(UPDATED_ENTITE)
            .refContentieux(UPDATED_REF_CONTENTIEUX)
            .datePremiereAudience(UPDATED_DATE_PREMIERE_AUDIENCE);
        return contentieux;
    }

    @BeforeEach
    public void initTest() {
        contentieux = createEntity(em);
    }

    @Test
    @Transactional
    public void createContentieux() throws Exception {
        int databaseSizeBeforeCreate = contentieuxRepository.findAll().size();
        // Create the Contentieux
        restContentieuxMockMvc.perform(post("/api/contentieuxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contentieux)))
            .andExpect(status().isCreated());

        // Validate the Contentieux in the database
        List<Contentieux> contentieuxList = contentieuxRepository.findAll();
        assertThat(contentieuxList).hasSize(databaseSizeBeforeCreate + 1);
        Contentieux testContentieux = contentieuxList.get(contentieuxList.size() - 1);
        assertThat(testContentieux.getEntite()).isEqualTo(DEFAULT_ENTITE);
        assertThat(testContentieux.getRefContentieux()).isEqualTo(DEFAULT_REF_CONTENTIEUX);
        assertThat(testContentieux.getDatePremiereAudience()).isEqualTo(DEFAULT_DATE_PREMIERE_AUDIENCE);
    }

    @Test
    @Transactional
    public void createContentieuxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contentieuxRepository.findAll().size();

        // Create the Contentieux with an existing ID
        contentieux.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContentieuxMockMvc.perform(post("/api/contentieuxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contentieux)))
            .andExpect(status().isBadRequest());

        // Validate the Contentieux in the database
        List<Contentieux> contentieuxList = contentieuxRepository.findAll();
        assertThat(contentieuxList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDatePremiereAudienceIsRequired() throws Exception {
        int databaseSizeBeforeTest = contentieuxRepository.findAll().size();
        // set the field null
        contentieux.setDatePremiereAudience(null);

        // Create the Contentieux, which fails.


        restContentieuxMockMvc.perform(post("/api/contentieuxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contentieux)))
            .andExpect(status().isBadRequest());

        List<Contentieux> contentieuxList = contentieuxRepository.findAll();
        assertThat(contentieuxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllContentieuxes() throws Exception {
        // Initialize the database
        contentieuxRepository.saveAndFlush(contentieux);

        // Get all the contentieuxList
        restContentieuxMockMvc.perform(get("/api/contentieuxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contentieux.getId().intValue())))
            .andExpect(jsonPath("$.[*].entite").value(hasItem(DEFAULT_ENTITE.toString())))
            .andExpect(jsonPath("$.[*].refContentieux").value(hasItem(DEFAULT_REF_CONTENTIEUX)))
            .andExpect(jsonPath("$.[*].datePremiereAudience").value(hasItem(DEFAULT_DATE_PREMIERE_AUDIENCE.toString())));
    }
    
    @Test
    @Transactional
    public void getContentieux() throws Exception {
        // Initialize the database
        contentieuxRepository.saveAndFlush(contentieux);

        // Get the contentieux
        restContentieuxMockMvc.perform(get("/api/contentieuxes/{id}", contentieux.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contentieux.getId().intValue()))
            .andExpect(jsonPath("$.entite").value(DEFAULT_ENTITE.toString()))
            .andExpect(jsonPath("$.refContentieux").value(DEFAULT_REF_CONTENTIEUX))
            .andExpect(jsonPath("$.datePremiereAudience").value(DEFAULT_DATE_PREMIERE_AUDIENCE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingContentieux() throws Exception {
        // Get the contentieux
        restContentieuxMockMvc.perform(get("/api/contentieuxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContentieux() throws Exception {
        // Initialize the database
        contentieuxService.save(contentieux);

        int databaseSizeBeforeUpdate = contentieuxRepository.findAll().size();

        // Update the contentieux
        Contentieux updatedContentieux = contentieuxRepository.findById(contentieux.getId()).get();
        // Disconnect from session so that the updates on updatedContentieux are not directly saved in db
        em.detach(updatedContentieux);
        updatedContentieux
            .entite(UPDATED_ENTITE)
            .refContentieux(UPDATED_REF_CONTENTIEUX)
            .datePremiereAudience(UPDATED_DATE_PREMIERE_AUDIENCE);

        restContentieuxMockMvc.perform(put("/api/contentieuxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedContentieux)))
            .andExpect(status().isOk());

        // Validate the Contentieux in the database
        List<Contentieux> contentieuxList = contentieuxRepository.findAll();
        assertThat(contentieuxList).hasSize(databaseSizeBeforeUpdate);
        Contentieux testContentieux = contentieuxList.get(contentieuxList.size() - 1);
        assertThat(testContentieux.getEntite()).isEqualTo(UPDATED_ENTITE);
        assertThat(testContentieux.getRefContentieux()).isEqualTo(UPDATED_REF_CONTENTIEUX);
        assertThat(testContentieux.getDatePremiereAudience()).isEqualTo(UPDATED_DATE_PREMIERE_AUDIENCE);
    }

    @Test
    @Transactional
    public void updateNonExistingContentieux() throws Exception {
        int databaseSizeBeforeUpdate = contentieuxRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContentieuxMockMvc.perform(put("/api/contentieuxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contentieux)))
            .andExpect(status().isBadRequest());

        // Validate the Contentieux in the database
        List<Contentieux> contentieuxList = contentieuxRepository.findAll();
        assertThat(contentieuxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContentieux() throws Exception {
        // Initialize the database
        contentieuxService.save(contentieux);

        int databaseSizeBeforeDelete = contentieuxRepository.findAll().size();

        // Delete the contentieux
        restContentieuxMockMvc.perform(delete("/api/contentieuxes/{id}", contentieux.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Contentieux> contentieuxList = contentieuxRepository.findAll();
        assertThat(contentieuxList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
