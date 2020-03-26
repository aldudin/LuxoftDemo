package com.luxoft.demo.demo;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

//import org.skyscreamer.jsonassert.JSONAssert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationIntegrationTests {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
  
    // @Test
    // public void testGetItemByIdShouldReturnExpectedItem() throws Exception {
    //     HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    //     ResponseEntity<String> response = restTemplate.exchange(
    //       createURLWithPort("/item/1"), HttpMethod.GET, entity, String.class);
    //     String actualBody = response.getBody();
    //     String expectedBody = "{\"id\":1,\"description\":\"Apple\"}";
    //     JSONAssert.assertEquals(expectedBody, actualBody, false);
    // }     

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetItemByIdWithInvalidIDShouldPass() throws Exception {
        this.mockMvc.perform(get("/item/1")).andDo(print()).andExpect(status().isOk())
          .andExpect(content().string(containsString("Apple")));
    }

    @Test
    public void testGetItemByIdWithInvalidIDShouldThrowException() throws Exception {
        this.mockMvc.perform(get("/item/0")).andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetItemByIdWithEmptyIDShouldThrowException()  throws Exception{

        try {
            this.mockMvc.perform(get("/item/ ")).andDo(print()).andExpect(status().is4xxClientError());   
        } catch (Exception e) {

        }

    }

    // private String createURLWithPort(String uri) {
    //     return "http://localhost:8080" + uri;
    // }
}