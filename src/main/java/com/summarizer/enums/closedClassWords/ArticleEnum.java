package com.summarizer.enums.closedClassWords;

import java.util.Arrays;

public enum ArticleEnum {
  O("o"),
  A("a"),
  OS("os"),
  AS("as"),
  UM("um"),
  UMA("uma"),
  UNS("uns"),
  UMAS("umas");

  private String description;

  ArticleEnum(String description) {
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }

  public static boolean isArticle(String word) {
    return Arrays.stream(ArticleEnum.values())
        .map(ArticleEnum::getDescription)
        .anyMatch(article -> article.contains(word));
  }
}
