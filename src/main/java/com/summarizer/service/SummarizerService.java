package com.summarizer.service;

import com.summarizer.exceptions.FileTypeNotSupported;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SummarizerService {

  @Autowired private FileService fileService;

  public String generateSummary(MultipartFile file) throws IOException, FileTypeNotSupported {
    String[] sentences = fileService.convertFileIntoSentences(file);

    return "teste";
  }
}
