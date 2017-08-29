package com.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.controller.StatisticController;
import com.project.dto.ResultsDTO;
import com.project.dto.TransactionDTO;
import com.project.model.StatisticModel;
import com.project.service.TransactionService;
import com.project.service.impl.TransactionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by abdullah.alnoman on 29.08.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = N26Application.class)
@WebAppConfiguration
public class StatisticControllerTest {



    @Autowired
    TransactionService service;


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final int WAIT_FOR_SCHEDULING = 2000;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenValidTransaction_whenSendPostRequest_thenSaveResource() throws Exception {

        TransactionDTO transactionDTO =  new TransactionDTO();
        transactionDTO.setAmount(12.3);
        transactionDTO.setTimestamp(System.currentTimeMillis());

        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(transactionDTO))
        ).andExpect(status().isCreated());
    }

    @Test
    public void givenValidTransactionTime_whenSendPostRequest_thenDiscardResource() throws Exception {

        TransactionDTO transactionDTO =  new TransactionDTO();
        transactionDTO.setAmount(12.3);
        transactionDTO.setTimestamp(System.currentTimeMillis()-600000);

        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(transactionDTO))
        ).andExpect(status().isNoContent());
    }




    @Test
    public void givenValidTransactionTime_whenSendGetRequest_thenReturnResult() throws Exception {
        TransactionDTO transactionDTO =  new TransactionDTO();
        transactionDTO.setAmount(12.3);
        transactionDTO.setTimestamp(System.currentTimeMillis());

        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(transactionDTO))
        ).andExpect(status().isCreated());
        Thread.sleep(WAIT_FOR_SCHEDULING);
        mockMvc.perform(get("/statistics")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("max", is(12.3)))
                .andExpect(jsonPath("min", is(12.3)))
                .andExpect(jsonPath("avg", is(12.3)))
                .andExpect(jsonPath("sum", is(12.3)))
                .andExpect(jsonPath("count", is(1)));
    }





    @Test
    public void givenTimeExpires_whenSendGetRequest_thenTransactionDeleted() throws Exception {
        TransactionDTO transactionDTO =  new TransactionDTO();
        transactionDTO.setAmount(12.3);
        transactionDTO.setTimestamp(System.currentTimeMillis()-59000);

        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(transactionDTO))
        ).andExpect(status().isCreated());
        Thread.sleep(WAIT_FOR_SCHEDULING*2);
        mockMvc.perform(get("/statistics")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("max", is(0.0)))
                .andExpect(jsonPath("min", is(0.0)))
                .andExpect(jsonPath("avg", is(0)))
                .andExpect(jsonPath("sum", is(0)))
                .andExpect(jsonPath("count", is(0)));
    }

}
