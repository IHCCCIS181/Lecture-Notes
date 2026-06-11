package org.example.areaofrec;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testValidFormSubmission() throws Exception {
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) //we will do JSON later
                        .param("length", "5")
                        .param("width", "10"))
                .andExpect(status().isOk()) // expecting a successful response
                .andExpect(view().name("result"));
    }


    @Test
    void testInvalidFormSubmission() throws Exception {
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("length", "-5")
                        .param("width", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("form"));
    }
}
