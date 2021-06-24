package com.summarizer.enums;

import java.util.Arrays;
import java.util.EnumSet;

public enum SupportedFiles {
  PDF("application/pdf");

  private String description;

  public static EnumSet supportedFilesList = EnumSet.of(PDF);

  SupportedFiles(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public static boolean isSupported(String fileExtension) {
    return Arrays.stream(SupportedFiles.values())
        .map(SupportedFiles::getDescription)
        .anyMatch(extension -> extension.contains(fileExtension));
  }
}
