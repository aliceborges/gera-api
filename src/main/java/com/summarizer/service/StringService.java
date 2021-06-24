package com.summarizer.service;

import java.util.Locale;

public class StringService {

  public String formatSentence(String sentence) {
    return sentence.replaceAll("[.,()!?@^:-]", " ").toLowerCase(Locale.ROOT);
  }
}
