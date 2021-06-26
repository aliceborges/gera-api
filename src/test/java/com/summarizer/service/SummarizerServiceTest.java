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
        "Em depoimento à CPI da Covid nesta quinta-feira (24), o epidemiologista Pedro Hallal disse que cerca de 400 mil mortes pela doença no país poderiam ter sido evitadas caso medidas de controle, como o distanciamento social e a celeridade na vacinação, tivessem sido implementadas no país. Só a demora na compra de vacinas, segundo o pesquisador, levou a pelo menos 95,5 mil mortes que poderiam ter sido evitadas. \"Nós fizemos uma análise que estimou que especificamente o atraso na compra das vacinas da Pfizer e da CoronaVac resultou em 95,5 mil mortes\", afirmou. Hallal relatou que há estudos apontando que a demora pode ter acarretado em 145 mil mortes. \"Outros pesquisadores, usando um método, com toda tranquilidade pra dizer isso, inclusive mais robusto do que o nosso, porque eles analisaram os dados não especificamente dessas vacinas, mas o ritmo da campanha de vacinação que teria sido, caso tivéssemos adquirido, e eles estimaram 145 mil mortes especificamente pela falta de aquisição de vacinas tempestivamente pelo governo federal\", completou. Hallal fez comparações sobre os números de mortes no Brasil e no resto do mundo – segundo o epidemiologista, o país, que representa 2,7% da população mundial, registra 13% das mortes por Covid do planeta. “Trinta e três por centro das mortes por Covid no planeta Terra ontem aconteceram num país que tem 2,7% da população mundial. Ele listou o que o país fez de errado e que resultou em muitas das mortes: \"O Brasil fez promoção de tratamentos ineficazes\"; \"O Brasil teve pouca testagem, rastreamento de contatos e isolamento, que é o bê-á-bá do enfrentamento de qualquer doença infecciosa\"; \"A ausência de liderança do Ministério da Saúde, de um comitê de crise\"; \"O desestímulo ao uso de máscaras\"; \"A falta de uma comunicação unificada, estimulando a população a usar máscara, a se proteger, a evitar aglomerações\". 'Postura anticiência' O epidemiologista ressaltou ainda que o alto índice de mortes no país não se deve ao tamanho da população brasileira, ao nível de desenvolvimento ou à pirâmide etária. Ele apresentou um gráfico com uma relação entre os eleitores do presidente Jair Bolsonaro no segundo turno das eleições de 2018 e as mortes por coronavírus a cada 100 mil habitantes. Segundo o pesquisador, em cidades nas quais Bolsonaro teve menos de 10% dos votos, por exemplo, foram registradas 70 mortes por 100 mil habitantes. Nas regiões em que o presidente recebeu mais de 90% dos votos, as mortes chegam a 313 a cada 100 mil habitantes. “A gente observa uma relação extremamente linear entre o percentual de votos obtidos no segundo turno das últimas eleições presidenciais e, especificamente, a morte por Covid”, disse. “Cada pessoa pode fazer uma interpretação desse resultado. Cada pessoa pode fazer a interpretação que achar mais adequada”, respondeu Hallal. ";

    var summary = summarizerService.generateSummary(multipartFile, LANGUAGE);

    assertThat(summary).isEqualTo(expectedSummary);
  }
}
