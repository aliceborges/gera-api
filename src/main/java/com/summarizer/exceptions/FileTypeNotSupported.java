package com.summarizer.exceptions;

public class FileTypeNotSupported extends Exception {

  public FileTypeNotSupported(String message) {
    super(message);
  }

  public FileTypeNotSupported(String message, Throwable cause) {
    super(message, cause);
  }
}
