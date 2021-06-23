package com.summarizer.enums;

import java.util.EnumSet;

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

  public static EnumSet<PrepositionEnum> prepositionPtBr =
      EnumSet.of(
          ANTE,
          APOS_WITH_ACCENT,
          ATE_WITH_ACCENT,
          COM,
          CONTRA,
          DE,
          DESDE,
          EM,
          ENTRE,
          PARA,
          PER,
          PERANTE,
          POR,
          SEM,
          SOB,
          SOBRE,
          TRAS_WITH_ACCENT);

  PrepositionEnum(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
