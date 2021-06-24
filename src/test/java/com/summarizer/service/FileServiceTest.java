package com.summarizer.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.summarizer.exceptions.FileTypeNotSupported;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

@RunWith(MockitoJUnitRunner.class)
public class FileServiceTest {

  @InjectMocks FileService fileService;

  @Test(expected = FileTypeNotSupported.class)
  public void should_return_file_not_supported() throws FileTypeNotSupported, IOException {
    var multipartFile =
        new MockMultipartFile(
            "data",
            "teste.docx",
            MediaType.TEXT_PLAIN_VALUE,
            "Arquivo teste".getBytes(StandardCharsets.UTF_8));

    fileService.convertFileIntoSentences(multipartFile);
  }

  @Test
  public void should_return_sentences_of_file() throws FileTypeNotSupported, IOException {
    var multipartFile =
        new MockMultipartFile(
            "teste.pdf",
            "teste.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            new FileInputStream("src/test/java/resources/teste.pdf"));
    String[] expectedResult = new String[5];
    expectedResult[0] = "Arquivo teste.";
    expectedResult[1] = "Teste.";
    expectedResult[2] = "O Dr. para testar!";
    expectedResult[3] = "- Mas você não disse que isso era uma conversa?";
    expectedResult[4] = "- Isto é um diálogo!";

    String[] result = fileService.convertFileIntoSentences(multipartFile);

    assertThat(result).isEqualTo(expectedResult);
  }
}