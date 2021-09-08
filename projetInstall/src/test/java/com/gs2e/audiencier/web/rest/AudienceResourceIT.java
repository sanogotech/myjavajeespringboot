package com.gs2e.audiencier.web.rest;

import com.gs2e.audiencier.AudiencierApp;
import com.gs2e.audiencier.domain.Audience;
import com.gs2e.audiencier.repository.AudienceRepository;
import com.gs2e.audiencier.service.AudienceService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.gs2e.audiencier.domain.enumeration.Mois;
/**
 * Integration tests for the {@link AudienceResource} REST controller.
 */
@SpringBootTest(classes = AudiencierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AudienceResourceIT {

    private static final Mois DEFAULT_MOIS = Mois.Janvier;
    private static final Mois UPDATED_MOIS = Mois.Fevrier;

    private static final String DEFAULT_STADE = "AAAAAAAAAA";
    private static final String UPDATED_STADE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_AUDIENCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_AUDIENCE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private AudienceRepository audienceRepository;

    @Autowired
    private AudienceService audienceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAudienceMockMvc;

    private Audience audience;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Audience createEntity(EntityManager em) {
        Audience audience = new Audience()
            .mois(DEFAULT_MOIS)
            .stade(DEFAULT_STADE)
            .dateAudience(DEFAULT_DATE_AUDIENCE);
        return audience;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Audience createUpdatedEntity(EntityManager em) {
        Audience audience = new Audience()
            .mois(UPDATED_MOIS)
            .stade(UPDATED_STADE)
            .dateAudience(UPDATED_DATE_AUDIENCE);
        return audience;
    }

    @BeforeEach
    public void initTest() {
        audience = createEntity(em);
    }

    @Test
    @Transactional
    public void createAudience() throws Exception {
        int databaseSizeBeforeCreate = audienceRepository.findAll().size();
        // Create the Audience
        restAudienceMockMvc.perform(post("/api/audiences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(audience)))
            .andExpect(status().isCreated());

        // Validate the Audience in the database
        List<Audience> audienceList = audienceRepository.findAll();
        assertThat(audienceList).hasSize(databaseSizeBeforeCreate + 1);
        Audience testAudience = audienceList.get(audienceList.size() - 1);
        assertThat(testAudience.getMois()).isEqualTo(DEFAULT_MOIS);
        assertThat(testAudience.getStade()).isEqualTo(DEFAULT_STADE);
        assertThat(testAudience.getDateAudience()).isEqualTo(DEFAULT_DATE_AUDIENCE);
    }

    @Test
    @Transactional
    public void createAudienceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = audienceRepository.findAll().size();

        // Create the Audience with an existing ID
        audience.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAudienceMockMvc.perform(post("/api/audiences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(audience)))
            .andExpect(status().isBadRequest());

        // Validate the Audience in the database
        List<Audience> audienceList = audienceRepository.findAll();
        assertThat(audienceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMoisIsRequired() throws Exception {
        int databaseSizeBeforeTest = audienceRepository.findAll().size();
        // set the field null
        audience.setMois(null);

        // Create the Audience, which fails.


        restAudienceMockMvc.perform(post("/api/audiences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(audience)))
            .andExpect(status().isBadRequest());

        List<Audience> audienceList = audienceRepository.findAll();
        assertThat(audienceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateAudienceIsRequired() throws Exception {
        int databaseSizeBeforeTest = audienceRepository.findAll().size();
        // set the field null
        audience.setDateAudience(null);

        // Create the Audience, which fails.


        restAudienceMockMvc.perform(post("/api/audiences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(audience)))
            .andExpect(status().isBadRequest());

        List<Audience> audienceList = audienceRepository.findAll();
        assertThat(audienceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAudiences() throws Exception {
        // Initialize the database
        audienceRepository.saveAndFlush(audience);

        // Get all the audienceList
        restAudienceMockMvc.perform(get("/api/audiences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(audience.getId().intValue())))
            .andExpect(jsonPath("$.[*].mois").value(hasItem(DEFAULT_MOIS.toString())))
            .andExpect(jsonPath("$.[*].stade").value(hasItem(DEFAULT_STADE.toString())))
            .andExpect(jsonPath("$.[*].dateAudience").value(hasItem(DEFAULT_DATE_AUDIENCE.toString())));
    }
    
    @Test
    @Transactional
    public void getAudience() throws Exception {
        // Initialize the database
        audienceRepository.saveAndFlush(audience);

        // Get the audience
        restAudienceMockMvc.perform(get("/api/audiences/{id}", audience.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(audience.getId().intValue()))
            .andExpect(jsonPath("$.mois").value(DEFAULT_MOIS.toString()))
            .andExpect(jsonPath("$.stade").value(DEFAULT_STADE.toString()))
            .andExpect(jsonPath("$.dateAudience").value(DEFAULT_DATE_AUDIENCE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAudience() throws Exception {
        // Get the audience
        restAudienceMockMvc.perform(get("/api/audiences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAudience() throws Exception {
        // Initialize the database
        audienceService.save(audience);

        int databaseSizeBeforeUpdate = audienceRepository.findAll().size();

        // Update the audience
        Audience updatedAudience = audienceRepository.findById(audience.getId()).get();
        // Disconnect from session so that the updates on updatedAudience are not directly saved in db
        em.detach(updatedAudience);
        updatedAudience
            .mois(UPDATED_MOIS)
            .stade(UPDATED_STADE)
            .dateAudience(UPDATED_DATE_AUDIENCE);

        restAudienceMockMvc.perform(put("/api/audiences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAudience)))
            .andExpect(status().isOk());

        // Validate the Audience in the database
        List<Audience> audienceList = audienceRepository.findAll();
        assertThat(audienceList).hasSize(databaseSizeBeforeUpdate);
        Audience testAudience = audienceList.get(audienceList.size() - 1);
        assertThat(testAudience.getMois()).isEqualTo(UPDATED_MOIS);
        assertThat(testAudience.getStade()).isEqualTo(UPDATED_STADE);
        assertThat(testAudience.getDateAudience()).isEqualTo(UPDATED_DATE_AUDIENCE);
    }

    @Test
    @Transactional
    public void updateNonExistingAudience() throws Exception {
        int databaseSizeBeforeUpdate = audienceRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAudienceMockMvc.perform(put("/api/audiences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(audience)))
            .andExpect(status().isBadRequest());

        // Validate the Audience in the database
        List<Audience> audienceList = audienceRepository.findAll();
        assertThat(audienceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAudience() throws Exception {
        // Initialize the database
        audienceService.save(audience);

        int databaseSizeBeforeDelete = audienceRepository.findAll().size();

        // Delete the audience
        restAudienceMockMvc.perform(delete("/api/audiences/{id}", audience.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Audience> audienceList = audienceRepository.findAll();
        assertThat(audienceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
