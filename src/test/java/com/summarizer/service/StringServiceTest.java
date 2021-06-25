package com.summarizer.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StringServiceTest {

  @InjectMocks StringService stringService;

  @Test
  public void should_format_string() {
    var sentenceToBeFormatted = "- Ola, Sr.! Tudo bem com voce?";
    var expectedSentence = "  ola  sr   tudo bem com voce ";

    var formattedSentence = stringService.formatSentence(sentenceToBeFormatted);

    assertThat(formattedSentence).isEqualTo(expectedSentence);
  }
}
