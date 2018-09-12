package org.jhipster.web.rest;

import org.jhipster.JhipsterProjectApp;

import org.jhipster.domain.Traffic;
import org.jhipster.repository.TrafficRepository;
import org.jhipster.service.TrafficService;
import org.jhipster.service.dto.TrafficDTO;
import org.jhipster.service.mapper.TrafficMapper;
import org.jhipster.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static org.jhipster.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TrafficResource REST controller.
 *
 * @see TrafficResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterProjectApp.class)
public class TrafficResourceIntTest {

    private static final String DEFAULT_TRAFFIC_DATE = "AAAAAAAAAA";
    private static final String UPDATED_TRAFFIC_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    @Autowired
    private TrafficRepository trafficRepository;


    @Autowired
    private TrafficMapper trafficMapper;
    

    @Autowired
    private TrafficService trafficService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTrafficMockMvc;

    private Traffic traffic;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TrafficResource trafficResource = new TrafficResource(trafficService);
        this.restTrafficMockMvc = MockMvcBuilders.standaloneSetup(trafficResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Traffic createEntity(EntityManager em) {
        Traffic traffic = new Traffic()
            .trafficDate(DEFAULT_TRAFFIC_DATE)
            .user(DEFAULT_USER)
            .userId(DEFAULT_USER_ID);
        return traffic;
    }

    @Before
    public void initTest() {
        traffic = createEntity(em);
    }

    @Test
    @Transactional
    public void createTraffic() throws Exception {
        int databaseSizeBeforeCreate = trafficRepository.findAll().size();

        // Create the Traffic
        TrafficDTO trafficDTO = trafficMapper.toDto(traffic);
        restTrafficMockMvc.perform(post("/api/traffic")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trafficDTO)))
            .andExpect(status().isCreated());

        // Validate the Traffic in the database
        List<Traffic> trafficList = trafficRepository.findAll();
        assertThat(trafficList).hasSize(databaseSizeBeforeCreate + 1);
        Traffic testTraffic = trafficList.get(trafficList.size() - 1);
        assertThat(testTraffic.getTrafficDate()).isEqualTo(DEFAULT_TRAFFIC_DATE);
        assertThat(testTraffic.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testTraffic.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void createTrafficWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trafficRepository.findAll().size();

        // Create the Traffic with an existing ID
        traffic.setId(1L);
        TrafficDTO trafficDTO = trafficMapper.toDto(traffic);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrafficMockMvc.perform(post("/api/traffic")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trafficDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Traffic in the database
        List<Traffic> trafficList = trafficRepository.findAll();
        assertThat(trafficList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTraffic() throws Exception {
        // Initialize the database
        trafficRepository.saveAndFlush(traffic);

        // Get all the trafficList
        restTrafficMockMvc.perform(get("/api/traffic?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(traffic.getId().intValue())))
            .andExpect(jsonPath("$.[*].trafficDate").value(hasItem(DEFAULT_TRAFFIC_DATE.toString())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())));
    }
    

    @Test
    @Transactional
    public void getTraffic() throws Exception {
        // Initialize the database
        trafficRepository.saveAndFlush(traffic);

        // Get the traffic
        restTrafficMockMvc.perform(get("/api/traffic/{id}", traffic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(traffic.getId().intValue()))
            .andExpect(jsonPath("$.trafficDate").value(DEFAULT_TRAFFIC_DATE.toString()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTraffic() throws Exception {
        // Get the traffic
        restTrafficMockMvc.perform(get("/api/traffic/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTraffic() throws Exception {
        // Initialize the database
        trafficRepository.saveAndFlush(traffic);

        int databaseSizeBeforeUpdate = trafficRepository.findAll().size();

        // Update the traffic
        Traffic updatedTraffic = trafficRepository.findById(traffic.getId()).get();
        // Disconnect from session so that the updates on updatedTraffic are not directly saved in db
        em.detach(updatedTraffic);
        updatedTraffic
            .trafficDate(UPDATED_TRAFFIC_DATE)
            .user(UPDATED_USER)
            .userId(UPDATED_USER_ID);
        TrafficDTO trafficDTO = trafficMapper.toDto(updatedTraffic);

        restTrafficMockMvc.perform(put("/api/traffic")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trafficDTO)))
            .andExpect(status().isOk());

        // Validate the Traffic in the database
        List<Traffic> trafficList = trafficRepository.findAll();
        assertThat(trafficList).hasSize(databaseSizeBeforeUpdate);
        Traffic testTraffic = trafficList.get(trafficList.size() - 1);
        assertThat(testTraffic.getTrafficDate()).isEqualTo(UPDATED_TRAFFIC_DATE);
        assertThat(testTraffic.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testTraffic.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingTraffic() throws Exception {
        int databaseSizeBeforeUpdate = trafficRepository.findAll().size();

        // Create the Traffic
        TrafficDTO trafficDTO = trafficMapper.toDto(traffic);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTrafficMockMvc.perform(put("/api/traffic")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trafficDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Traffic in the database
        List<Traffic> trafficList = trafficRepository.findAll();
        assertThat(trafficList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTraffic() throws Exception {
        // Initialize the database
        trafficRepository.saveAndFlush(traffic);

        int databaseSizeBeforeDelete = trafficRepository.findAll().size();

        // Get the traffic
        restTrafficMockMvc.perform(delete("/api/traffic/{id}", traffic.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Traffic> trafficList = trafficRepository.findAll();
        assertThat(trafficList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Traffic.class);
        Traffic traffic1 = new Traffic();
        traffic1.setId(1L);
        Traffic traffic2 = new Traffic();
        traffic2.setId(traffic1.getId());
        assertThat(traffic1).isEqualTo(traffic2);
        traffic2.setId(2L);
        assertThat(traffic1).isNotEqualTo(traffic2);
        traffic1.setId(null);
        assertThat(traffic1).isNotEqualTo(traffic2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrafficDTO.class);
        TrafficDTO trafficDTO1 = new TrafficDTO();
        trafficDTO1.setId(1L);
        TrafficDTO trafficDTO2 = new TrafficDTO();
        assertThat(trafficDTO1).isNotEqualTo(trafficDTO2);
        trafficDTO2.setId(trafficDTO1.getId());
        assertThat(trafficDTO1).isEqualTo(trafficDTO2);
        trafficDTO2.setId(2L);
        assertThat(trafficDTO1).isNotEqualTo(trafficDTO2);
        trafficDTO1.setId(null);
        assertThat(trafficDTO1).isNotEqualTo(trafficDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(trafficMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(trafficMapper.fromId(null)).isNull();
    }
}
