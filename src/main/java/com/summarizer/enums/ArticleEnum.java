package com.summarizer.enums;

import java.util.EnumSet;

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

  public static EnumSet<ArticleEnum> articlesPtBr = EnumSet.of(O, A, OS, AS, UM, UMA, UNS, UMAS);

  ArticleEnum(String description) {
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }
}
