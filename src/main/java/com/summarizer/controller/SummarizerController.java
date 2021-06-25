package com.summarizer.controller;

import com.summarizer.enums.SupportedLanguagesEnum;
import com.summarizer.exceptions.FileTypeNotSupported;
import com.summarizer.exceptions.FileWithoutContent;
import com.summarizer.service.SummarizerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "Envio do texto para geração do resumo")
@RestController
public class SummarizerController {

  @Autowired private SummarizerService summarizerService;

  @ApiOperation(
      value =
          "Recebe um arquivo com o conteúdo do texto, e retorna uma string com o resumo gerado.",
      notes =
          "Você deve enviar um arquivo PDF para gerar o resumo. Caso envie em outros modelos, o arquivo não será aceito. Caso seja de um livro, por favor, envie para resumir um capítulo por vez.")
  @PostMapping("/resumo")
  @ResponseStatus(HttpStatus.OK)
  public String generateSummary(@RequestPart MultipartFile file, @RequestParam SupportedLanguagesEnum language)
          throws IOException, FileTypeNotSupported, FileWithoutContent {
    return summarizerService.generateSummary(file, language);
  }
}
