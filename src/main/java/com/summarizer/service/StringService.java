package com.summarizer.service;

import java.util.Locale;

public class StringService {

  public String formatSentence(String sentence) {
    return sentence.replaceAll("[^a-zà-úA-ZÀ-Ú0-9]", " ").toLowerCase(Locale.ROOT);
  }
}
