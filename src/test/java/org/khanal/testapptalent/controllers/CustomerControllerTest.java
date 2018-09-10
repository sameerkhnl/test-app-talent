package org.khanal.testapptalent.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.khanal.testapptalent.services.CustomerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tenants/{tenant}", "acmesandbox"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void addNewCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/tenants")).andExpect
                (MockMvcResultMatchers.status().isCreated()).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}