package com.summarizer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class SummarizerControllerTest {

  @Autowired private WebApplicationContext webApplicationContext;
  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void should_send_file() throws Exception {
    var multipartFile =
        new MockMultipartFile(
            "teste.pdf",
            "teste.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            new FileInputStream("src/test/java/resources/teste_noticia.pdf"));

    mockMvc
        .perform(fileUpload("/resumo").file(multipartFile))
        .andExpect(status().isOk())
        .andExpect(content().string(""));
  }
}
