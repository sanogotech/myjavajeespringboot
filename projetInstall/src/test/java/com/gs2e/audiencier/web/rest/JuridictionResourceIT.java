package com.gs2e.audiencier.web.rest;

import com.gs2e.audiencier.AudiencierApp;
import com.gs2e.audiencier.domain.Juridiction;
import com.gs2e.audiencier.repository.JuridictionRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link JuridictionResource} REST controller.
 */
@SpringBootTest(classes = AudiencierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class JuridictionResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_VILLE = "AAAAAAAAAA";
    private static final String UPDATED_VILLE = "BBBBBBBBBB";

    @Autowired
    private JuridictionRepository juridictionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJuridictionMockMvc;

    private Juridiction juridiction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Juridiction createEntity(EntityManager em) {
        Juridiction juridiction = new Juridiction()
            .nom(DEFAULT_NOM)
            .ville(DEFAULT_VILLE);
        return juridiction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Juridiction createUpdatedEntity(EntityManager em) {
        Juridiction juridiction = new Juridiction()
            .nom(UPDATED_NOM)
            .ville(UPDATED_VILLE);
        return juridiction;
    }

    @BeforeEach
    public void initTest() {
        juridiction = createEntity(em);
    }

    @Test
    @Transactional
    public void createJuridiction() throws Exception {
        int databaseSizeBeforeCreate = juridictionRepository.findAll().size();
        // Create the Juridiction
        restJuridictionMockMvc.perform(post("/api/juridictions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(juridiction)))
            .andExpect(status().isCreated());

        // Validate the Juridiction in the database
        List<Juridiction> juridictionList = juridictionRepository.findAll();
        assertThat(juridictionList).hasSize(databaseSizeBeforeCreate + 1);
        Juridiction testJuridiction = juridictionList.get(juridictionList.size() - 1);
        assertThat(testJuridiction.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testJuridiction.getVille()).isEqualTo(DEFAULT_VILLE);
    }

    @Test
    @Transactional
    public void createJuridictionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = juridictionRepository.findAll().size();

        // Create the Juridiction with an existing ID
        juridiction.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJuridictionMockMvc.perform(post("/api/juridictions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(juridiction)))
            .andExpect(status().isBadRequest());

        // Validate the Juridiction in the database
        List<Juridiction> juridictionList = juridictionRepository.findAll();
        assertThat(juridictionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = juridictionRepository.findAll().size();
        // set the field null
        juridiction.setNom(null);

        // Create the Juridiction, which fails.


        restJuridictionMockMvc.perform(post("/api/juridictions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(juridiction)))
            .andExpect(status().isBadRequest());

        List<Juridiction> juridictionList = juridictionRepository.findAll();
        assertThat(juridictionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVilleIsRequired() throws Exception {
        int databaseSizeBeforeTest = juridictionRepository.findAll().size();
        // set the field null
        juridiction.setVille(null);

        // Create the Juridiction, which fails.


        restJuridictionMockMvc.perform(post("/api/juridictions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(juridiction)))
            .andExpect(status().isBadRequest());

        List<Juridiction> juridictionList = juridictionRepository.findAll();
        assertThat(juridictionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllJuridictions() throws Exception {
        // Initialize the database
        juridictionRepository.saveAndFlush(juridiction);

        // Get all the juridictionList
        restJuridictionMockMvc.perform(get("/api/juridictions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(juridiction.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE)));
    }
    
    @Test
    @Transactional
    public void getJuridiction() throws Exception {
        // Initialize the database
        juridictionRepository.saveAndFlush(juridiction);

        // Get the juridiction
        restJuridictionMockMvc.perform(get("/api/juridictions/{id}", juridiction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(juridiction.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.ville").value(DEFAULT_VILLE));
    }
    @Test
    @Transactional
    public void getNonExistingJuridiction() throws Exception {
        // Get the juridiction
        restJuridictionMockMvc.perform(get("/api/juridictions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJuridiction() throws Exception {
        // Initialize the database
        juridictionRepository.saveAndFlush(juridiction);

        int databaseSizeBeforeUpdate = juridictionRepository.findAll().size();

        // Update the juridiction
        Juridiction updatedJuridiction = juridictionRepository.findById(juridiction.getId()).get();
        // Disconnect from session so that the updates on updatedJuridiction are not directly saved in db
        em.detach(updatedJuridiction);
        updatedJuridiction
            .nom(UPDATED_NOM)
            .ville(UPDATED_VILLE);

        restJuridictionMockMvc.perform(put("/api/juridictions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedJuridiction)))
            .andExpect(status().isOk());

        // Validate the Juridiction in the database
        List<Juridiction> juridictionList = juridictionRepository.findAll();
        assertThat(juridictionList).hasSize(databaseSizeBeforeUpdate);
        Juridiction testJuridiction = juridictionList.get(juridictionList.size() - 1);
        assertThat(testJuridiction.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testJuridiction.getVille()).isEqualTo(UPDATED_VILLE);
    }

    @Test
    @Transactional
    public void updateNonExistingJuridiction() throws Exception {
        int databaseSizeBeforeUpdate = juridictionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJuridictionMockMvc.perform(put("/api/juridictions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(juridiction)))
            .andExpect(status().isBadRequest());

        // Validate the Juridiction in the database
        List<Juridiction> juridictionList = juridictionRepository.findAll();
        assertThat(juridictionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteJuridiction() throws Exception {
        // Initialize the database
        juridictionRepository.saveAndFlush(juridiction);

        int databaseSizeBeforeDelete = juridictionRepository.findAll().size();

        // Delete the juridiction
        restJuridictionMockMvc.perform(delete("/api/juridictions/{id}", juridiction.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Juridiction> juridictionList = juridictionRepository.findAll();
        assertThat(juridictionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
