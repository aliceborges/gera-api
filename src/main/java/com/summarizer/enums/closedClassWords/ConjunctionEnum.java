package com.summarizer.enums.closedClassWords;

import java.util.Arrays;

public enum ConjunctionEnum {
  QUE("que"),
  SE("se"),
  E("e"),
  NEM("nem"),
  TAMPOUCO("tampouco"),
  TANTO("tanto"),
  QUANTO("quanto"),
  MAS("mas"),
  POREM_WITH_ACCENT("porém"),
  CONTUDO("contudo"),
  TODAVIA("todavia"),
  ENTANTO("entanto"),
  ENTRETANTO("entretanto"),
  OU("ou"),
  ORA("ora"),
  JA_WITH_ACCENT("já"),
  QUER("quer"),
  SEJA("seja"),
  LOGO("logo"),
  POIS("pois"),
  PORTANTO("portanto"),
  CONSEGUINTE("conseguinte"),
  ASSIM("assim"),
  ENTAO_WITH_ACCENT("então"),
  DESCARTE("descarte"),
  DESSARTE("dessarte"),
  PORQUANTO("porquanto"),
  ENQUANTO("enquanto");

  private String description;

  ConjunctionEnum(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public static boolean isConjunction(String word) {
    return Arrays.stream(ConjunctionEnum.values())
        .map(ConjunctionEnum::getDescription)
        .anyMatch(conjunction -> conjunction.contains(word));
  }
}
