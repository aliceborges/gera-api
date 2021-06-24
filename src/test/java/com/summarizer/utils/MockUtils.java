package com.summarizer.utils;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class MockUtils {
  public MultipartFile getDocxFile() {
    return new MockMultipartFile(
        "data",
        "teste.docx",
        MediaType.TEXT_PLAIN_VALUE,
        "Arquivo teste".getBytes(StandardCharsets.UTF_8));
  }
}
