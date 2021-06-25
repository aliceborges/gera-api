package com.summarizer.exceptions;

public class FileNotEnoughContent extends Exception {

  public FileNotEnoughContent(String message) {
    super(message);
  }

  public FileNotEnoughContent(String message, Throwable cause) {
    super(message, cause);
  }
}
