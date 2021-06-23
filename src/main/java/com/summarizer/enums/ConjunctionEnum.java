package com.summarizer.enums;

import java.util.EnumSet;

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

  public static EnumSet<ConjunctionEnum> conjunctionPtBr =
      EnumSet.of(
          QUE,
          SE,
          E,
          NEM,
          TAMPOUCO,
          TANTO,
          QUANTO,
          MAS,
          POREM_WITH_ACCENT,
          CONTUDO,
          TODAVIA,
          ENTANTO,
          ENTRETANTO,
          OU,
          ORA,
          JA_WITH_ACCENT,
          QUER,
          SEJA,
          LOGO,
          POIS,
          PORTANTO,
          CONSEGUINTE,
          ASSIM,
          ENTAO_WITH_ACCENT,
          DESCARTE,
          DESSARTE,
          PORQUANTO,
          ENQUANTO);

  ConjunctionEnum(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
