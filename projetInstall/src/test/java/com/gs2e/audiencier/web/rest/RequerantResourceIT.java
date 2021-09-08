package com.gs2e.audiencier.web.rest;

import com.gs2e.audiencier.AudiencierApp;
import com.gs2e.audiencier.domain.Requerant;
import com.gs2e.audiencier.repository.RequerantRepository;

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
 * Integration tests for the {@link RequerantResource} REST controller.
 */
@SpringBootTest(classes = AudiencierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RequerantResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_CNI = "AAAAAAAAAA";
    private static final String UPDATED_NUM_CNI = "BBBBBBBBBB";

    @Autowired
    private RequerantRepository requerantRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequerantMockMvc;

    private Requerant requerant;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Requerant createEntity(EntityManager em) {
        Requerant requerant = new Requerant()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .numCNI(DEFAULT_NUM_CNI);
        return requerant;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Requerant createUpdatedEntity(EntityManager em) {
        Requerant requerant = new Requerant()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .numCNI(UPDATED_NUM_CNI);
        return requerant;
    }

    @BeforeEach
    public void initTest() {
        requerant = createEntity(em);
    }

    @Test
    @Transactional
    public void createRequerant() throws Exception {
        int databaseSizeBeforeCreate = requerantRepository.findAll().size();
        // Create the Requerant
        restRequerantMockMvc.perform(post("/api/requerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requerant)))
            .andExpect(status().isCreated());

        // Validate the Requerant in the database
        List<Requerant> requerantList = requerantRepository.findAll();
        assertThat(requerantList).hasSize(databaseSizeBeforeCreate + 1);
        Requerant testRequerant = requerantList.get(requerantList.size() - 1);
        assertThat(testRequerant.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testRequerant.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testRequerant.getNumCNI()).isEqualTo(DEFAULT_NUM_CNI);
    }

    @Test
    @Transactional
    public void createRequerantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = requerantRepository.findAll().size();

        // Create the Requerant with an existing ID
        requerant.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequerantMockMvc.perform(post("/api/requerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requerant)))
            .andExpect(status().isBadRequest());

        // Validate the Requerant in the database
        List<Requerant> requerantList = requerantRepository.findAll();
        assertThat(requerantList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = requerantRepository.findAll().size();
        // set the field null
        requerant.setNom(null);

        // Create the Requerant, which fails.


        restRequerantMockMvc.perform(post("/api/requerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requerant)))
            .andExpect(status().isBadRequest());

        List<Requerant> requerantList = requerantRepository.findAll();
        assertThat(requerantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = requerantRepository.findAll().size();
        // set the field null
        requerant.setPrenom(null);

        // Create the Requerant, which fails.


        restRequerantMockMvc.perform(post("/api/requerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requerant)))
            .andExpect(status().isBadRequest());

        List<Requerant> requerantList = requerantRepository.findAll();
        assertThat(requerantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumCNIIsRequired() throws Exception {
        int databaseSizeBeforeTest = requerantRepository.findAll().size();
        // set the field null
        requerant.setNumCNI(null);

        // Create the Requerant, which fails.


        restRequerantMockMvc.perform(post("/api/requerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requerant)))
            .andExpect(status().isBadRequest());

        List<Requerant> requerantList = requerantRepository.findAll();
        assertThat(requerantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRequerants() throws Exception {
        // Initialize the database
        requerantRepository.saveAndFlush(requerant);

        // Get all the requerantList
        restRequerantMockMvc.perform(get("/api/requerants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(requerant.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].numCNI").value(hasItem(DEFAULT_NUM_CNI)));
    }
    
    @Test
    @Transactional
    public void getRequerant() throws Exception {
        // Initialize the database
        requerantRepository.saveAndFlush(requerant);

        // Get the requerant
        restRequerantMockMvc.perform(get("/api/requerants/{id}", requerant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(requerant.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.numCNI").value(DEFAULT_NUM_CNI));
    }
    @Test
    @Transactional
    public void getNonExistingRequerant() throws Exception {
        // Get the requerant
        restRequerantMockMvc.perform(get("/api/requerants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequerant() throws Exception {
        // Initialize the database
        requerantRepository.saveAndFlush(requerant);

        int databaseSizeBeforeUpdate = requerantRepository.findAll().size();

        // Update the requerant
        Requerant updatedRequerant = requerantRepository.findById(requerant.getId()).get();
        // Disconnect from session so that the updates on updatedRequerant are not directly saved in db
        em.detach(updatedRequerant);
        updatedRequerant
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .numCNI(UPDATED_NUM_CNI);

        restRequerantMockMvc.perform(put("/api/requerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRequerant)))
            .andExpect(status().isOk());

        // Validate the Requerant in the database
        List<Requerant> requerantList = requerantRepository.findAll();
        assertThat(requerantList).hasSize(databaseSizeBeforeUpdate);
        Requerant testRequerant = requerantList.get(requerantList.size() - 1);
        assertThat(testRequerant.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testRequerant.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testRequerant.getNumCNI()).isEqualTo(UPDATED_NUM_CNI);
    }

    @Test
    @Transactional
    public void updateNonExistingRequerant() throws Exception {
        int databaseSizeBeforeUpdate = requerantRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequerantMockMvc.perform(put("/api/requerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requerant)))
            .andExpect(status().isBadRequest());

        // Validate the Requerant in the database
        List<Requerant> requerantList = requerantRepository.findAll();
        assertThat(requerantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRequerant() throws Exception {
        // Initialize the database
        requerantRepository.saveAndFlush(requerant);

        int databaseSizeBeforeDelete = requerantRepository.findAll().size();

        // Delete the requerant
        restRequerantMockMvc.perform(delete("/api/requerants/{id}", requerant.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Requerant> requerantList = requerantRepository.findAll();
        assertThat(requerantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
