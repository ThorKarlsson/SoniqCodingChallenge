package com.soniq.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CleanCapacityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testOptimizeEndpoint() throws Exception {
        mockMvc.perform(post("/optimize")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"rooms\": [35, 21, 17, 28]," +
                        "\"junior\": 6," +
                        "\"senior\": 10" +
                        "}"))
                .andExpect(matchAll(
                     status().isOk(),
                     content().json("[{\"senior\":3,\"junior\":1},{\"senior\":1,\"junior\":2},{\"senior\":2,\"junior\":0},{\"senior\":1,\"junior\":3}]")));
    }
}
