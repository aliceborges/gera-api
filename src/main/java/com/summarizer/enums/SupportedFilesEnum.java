package com.summarizer.enums;

import java.util.Arrays;
import java.util.EnumSet;

public enum SupportedFilesEnum {
  PDF("application/pdf");

  private String description;

  public static EnumSet supportedFilesList = EnumSet.of(PDF);

  SupportedFilesEnum(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public static boolean isSupported(String fileExtension) {
    return Arrays.stream(SupportedFilesEnum.values())
        .map(SupportedFilesEnum::getDescription)
        .anyMatch(extension -> extension.contains(fileExtension));
  }
}
