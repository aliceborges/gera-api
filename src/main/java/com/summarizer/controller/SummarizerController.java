package com.summarizer.controller;

import com.summarizer.exceptions.FileTypeNotSupported;
import com.summarizer.service.SummarizerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
  public String generateSummary(@RequestPart("file") MultipartFile file)
      throws IOException, FileTypeNotSupported {
    return summarizerService.generateSummary(file);
  }
}
