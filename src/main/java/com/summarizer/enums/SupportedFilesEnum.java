package com.summarizer.enums;

import java.util.Arrays;

public enum SupportedFilesEnum {
  PDF("application/pdf");

  private String description;

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
