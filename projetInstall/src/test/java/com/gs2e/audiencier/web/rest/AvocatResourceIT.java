package com.gs2e.audiencier.web.rest;

import com.gs2e.audiencier.AudiencierApp;
import com.gs2e.audiencier.domain.Avocat;
import com.gs2e.audiencier.repository.AvocatRepository;

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
 * Integration tests for the {@link AvocatResource} REST controller.
 */
@SpringBootTest(classes = AudiencierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AvocatResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    @Autowired
    private AvocatRepository avocatRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAvocatMockMvc;

    private Avocat avocat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Avocat createEntity(EntityManager em) {
        Avocat avocat = new Avocat()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .email(DEFAULT_EMAIL)
            .phone(DEFAULT_PHONE);
        return avocat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Avocat createUpdatedEntity(EntityManager em) {
        Avocat avocat = new Avocat()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE);
        return avocat;
    }

    @BeforeEach
    public void initTest() {
        avocat = createEntity(em);
    }

    @Test
    @Transactional
    public void createAvocat() throws Exception {
        int databaseSizeBeforeCreate = avocatRepository.findAll().size();
        // Create the Avocat
        restAvocatMockMvc.perform(post("/api/avocats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avocat)))
            .andExpect(status().isCreated());

        // Validate the Avocat in the database
        List<Avocat> avocatList = avocatRepository.findAll();
        assertThat(avocatList).hasSize(databaseSizeBeforeCreate + 1);
        Avocat testAvocat = avocatList.get(avocatList.size() - 1);
        assertThat(testAvocat.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testAvocat.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testAvocat.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAvocat.getPhone()).isEqualTo(DEFAULT_PHONE);
    }

    @Test
    @Transactional
    public void createAvocatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = avocatRepository.findAll().size();

        // Create the Avocat with an existing ID
        avocat.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAvocatMockMvc.perform(post("/api/avocats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avocat)))
            .andExpect(status().isBadRequest());

        // Validate the Avocat in the database
        List<Avocat> avocatList = avocatRepository.findAll();
        assertThat(avocatList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = avocatRepository.findAll().size();
        // set the field null
        avocat.setNom(null);

        // Create the Avocat, which fails.


        restAvocatMockMvc.perform(post("/api/avocats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avocat)))
            .andExpect(status().isBadRequest());

        List<Avocat> avocatList = avocatRepository.findAll();
        assertThat(avocatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = avocatRepository.findAll().size();
        // set the field null
        avocat.setPrenom(null);

        // Create the Avocat, which fails.


        restAvocatMockMvc.perform(post("/api/avocats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avocat)))
            .andExpect(status().isBadRequest());

        List<Avocat> avocatList = avocatRepository.findAll();
        assertThat(avocatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = avocatRepository.findAll().size();
        // set the field null
        avocat.setEmail(null);

        // Create the Avocat, which fails.


        restAvocatMockMvc.perform(post("/api/avocats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avocat)))
            .andExpect(status().isBadRequest());

        List<Avocat> avocatList = avocatRepository.findAll();
        assertThat(avocatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = avocatRepository.findAll().size();
        // set the field null
        avocat.setPhone(null);

        // Create the Avocat, which fails.


        restAvocatMockMvc.perform(post("/api/avocats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avocat)))
            .andExpect(status().isBadRequest());

        List<Avocat> avocatList = avocatRepository.findAll();
        assertThat(avocatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAvocats() throws Exception {
        // Initialize the database
        avocatRepository.saveAndFlush(avocat);

        // Get all the avocatList
        restAvocatMockMvc.perform(get("/api/avocats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(avocat.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)));
    }
    
    @Test
    @Transactional
    public void getAvocat() throws Exception {
        // Initialize the database
        avocatRepository.saveAndFlush(avocat);

        // Get the avocat
        restAvocatMockMvc.perform(get("/api/avocats/{id}", avocat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(avocat.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE));
    }
    @Test
    @Transactional
    public void getNonExistingAvocat() throws Exception {
        // Get the avocat
        restAvocatMockMvc.perform(get("/api/avocats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAvocat() throws Exception {
        // Initialize the database
        avocatRepository.saveAndFlush(avocat);

        int databaseSizeBeforeUpdate = avocatRepository.findAll().size();

        // Update the avocat
        Avocat updatedAvocat = avocatRepository.findById(avocat.getId()).get();
        // Disconnect from session so that the updates on updatedAvocat are not directly saved in db
        em.detach(updatedAvocat);
        updatedAvocat
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE);

        restAvocatMockMvc.perform(put("/api/avocats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAvocat)))
            .andExpect(status().isOk());

        // Validate the Avocat in the database
        List<Avocat> avocatList = avocatRepository.findAll();
        assertThat(avocatList).hasSize(databaseSizeBeforeUpdate);
        Avocat testAvocat = avocatList.get(avocatList.size() - 1);
        assertThat(testAvocat.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testAvocat.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testAvocat.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAvocat.getPhone()).isEqualTo(UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void updateNonExistingAvocat() throws Exception {
        int databaseSizeBeforeUpdate = avocatRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAvocatMockMvc.perform(put("/api/avocats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avocat)))
            .andExpect(status().isBadRequest());

        // Validate the Avocat in the database
        List<Avocat> avocatList = avocatRepository.findAll();
        assertThat(avocatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAvocat() throws Exception {
        // Initialize the database
        avocatRepository.saveAndFlush(avocat);

        int databaseSizeBeforeDelete = avocatRepository.findAll().size();

        // Delete the avocat
        restAvocatMockMvc.perform(delete("/api/avocats/{id}", avocat.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Avocat> avocatList = avocatRepository.findAll();
        assertThat(avocatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
