package com.summarizer.enums.closedClassWords;

import java.util.Arrays;

public enum PrepositionEnum {
  ANTE("ante"),
  APOS_WITH_ACCENT("após"),
  ATE_WITH_ACCENT("até"),
  COM("com"),
  CONTRA("contra"),
  DE("de"),
  DESDE("desde"),
  EM("em"),
  ENTRE("entre"),
  PARA("para"),
  PER("per"),
  PERANTE("perante"),
  POR("por"),
  SEM("sem"),
  SOB("sob"),
  SOBRE("sobre"),
  TRAS_WITH_ACCENT("trás");

  private String description;

  PrepositionEnum(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public static boolean isPreposition(String word) {
    return Arrays.stream(PrepositionEnum.values())
        .map(PrepositionEnum::getDescription)
        .anyMatch(preposition -> preposition.contains(word));
  }
}
