package com.summarizer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SummarizerController {

  @PostMapping("/resumo-literario")
  public ResponseEntity<String> teste(@RequestPart("file") MultipartFile file) {
    return new ResponseEntity<>("Good Job", HttpStatus.ACCEPTED);
  }
}
