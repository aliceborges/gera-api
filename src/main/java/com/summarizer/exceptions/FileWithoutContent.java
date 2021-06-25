package com.summarizer.exceptions;

public class FileWithoutContent extends Exception {

  public FileWithoutContent(String message) {
    super(message);
  }

  public FileWithoutContent(String message, Throwable cause) {
    super(message, cause);
  }
}
