package com.summarizer.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.summarizer.enums.SupportedLanguagesEnum;
import com.summarizer.exceptions.FileNotEnoughContent;
import com.summarizer.exceptions.FileTypeNotSupported;
import com.summarizer.exceptions.FileWithoutContent;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

@RunWith(MockitoJUnitRunner.class)
public class SummarizerServiceTest {

  @Spy FileService fileService;
  @InjectMocks SummarizerService summarizerService;
  private static SupportedLanguagesEnum LANGUAGE = SupportedLanguagesEnum.BRAZILIAN_PORTUGUESE;

  @Test(expected = FileTypeNotSupported.class)
  public void should_not_generate_test_unsupported_file()
      throws IOException, FileTypeNotSupported, FileWithoutContent, FileNotEnoughContent {
    var multipartFile =
        new MockMultipartFile(
            "test.pdf",
            "test.pdf",
            MediaType.TEXT_PLAIN_VALUE,
            new FileInputStream("src/test/java/resources/test_news.docx"));

    summarizerService.generateSummary(multipartFile, LANGUAGE);
  }

  @Test(expected = FileNotEnoughContent.class)
  public void should_not_generate_test_file_not_enough_content()
      throws IOException, FileTypeNotSupported, FileWithoutContent, FileNotEnoughContent {
    var multipartFile =
        new MockMultipartFile(
            "test.pdf",
            "test.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            new FileInputStream("src/test/java/resources/test_file_not_enough_content.pdf"));

    summarizerService.generateSummary(multipartFile, LANGUAGE);
  }

  @Test
  public void should_generate_summary()
      throws IOException, FileTypeNotSupported, FileWithoutContent, FileNotEnoughContent {
    var multipartFile =
        new MockMultipartFile(
            "test.pdf",
            "test.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            new FileInputStream("src/test/java/resources/test_news.pdf"));
    var expectedSummary =
        "Em depoimento ?? CPI da Covid nesta quinta-feira (24), o epidemiologista Pedro Hallal disse que cerca de 400 mil mortes pela doen??a no pa??s poderiam ter sido evitadas caso medidas de controle, como o distanciamento social e a celeridade na vacina????o, tivessem sido implementadas no pa??s. S?? a demora na compra de vacinas, segundo o pesquisador, levou a pelo menos 95,5 mil mortes que poderiam ter sido evitadas. \"N??s fizemos uma an??lise que estimou que especificamente o atraso na compra das vacinas da Pfizer e da CoronaVac resultou em 95,5 mil mortes\", afirmou. Hallal relatou que h?? estudos apontando que a demora pode ter acarretado em 145 mil mortes. \"Outros pesquisadores, usando um m??todo, com toda tranquilidade pra dizer isso, inclusive mais robusto do que o nosso, porque eles analisaram os dados n??o especificamente dessas vacinas, mas o ritmo da campanha de vacina????o que teria sido, caso tiv??ssemos adquirido, e eles estimaram 145 mil mortes especificamente pela falta de aquisi????o de vacinas tempestivamente pelo governo federal\", completou. Hallal fez compara????es sobre os n??meros de mortes no Brasil e no resto do mundo ??? segundo o epidemiologista, o pa??s, que representa 2,7% da popula????o mundial, registra 13% das mortes por Covid do planeta. ???Trinta e tr??s por centro das mortes por Covid no planeta Terra ontem aconteceram num pa??s que tem 2,7% da popula????o mundial. Ele listou o que o pa??s fez de errado e que resultou em muitas das mortes: \"O Brasil fez promo????o de tratamentos ineficazes\"; \"O Brasil teve pouca testagem, rastreamento de contatos e isolamento, que ?? o b??-??-b?? do enfrentamento de qualquer doen??a infecciosa\"; \"A aus??ncia de lideran??a do Minist??rio da Sa??de, de um comit?? de crise\"; \"O desest??mulo ao uso de m??scaras\"; \"A falta de uma comunica????o unificada, estimulando a popula????o a usar m??scara, a se proteger, a evitar aglomera????es\". 'Postura antici??ncia' O epidemiologista ressaltou ainda que o alto ??ndice de mortes no pa??s n??o se deve ao tamanho da popula????o brasileira, ao n??vel de desenvolvimento ou ?? pir??mide et??ria. Ele apresentou um gr??fico com uma rela????o entre os eleitores do presidente Jair Bolsonaro no segundo turno das elei????es de 2018 e as mortes por coronav??rus a cada 100 mil habitantes. Segundo o pesquisador, em cidades nas quais Bolsonaro teve menos de 10% dos votos, por exemplo, foram registradas 70 mortes por 100 mil habitantes. Nas regi??es em que o presidente recebeu mais de 90% dos votos, as mortes chegam a 313 a cada 100 mil habitantes. ???A gente observa uma rela????o extremamente linear entre o percentual de votos obtidos no segundo turno das ??ltimas elei????es presidenciais e, especificamente, a morte por Covid???, disse. ???Cada pessoa pode fazer uma interpreta????o desse resultado. Cada pessoa pode fazer a interpreta????o que achar mais adequada???, respondeu Hallal. ";

    var summary = summarizerService.generateSummary(multipartFile, LANGUAGE);

    assertThat(summary).isEqualTo(expectedSummary);
  }
}
