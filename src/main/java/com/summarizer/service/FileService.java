package com.summarizer.service;

import com.summarizer.enums.SupportedFiles;
import com.summarizer.exceptions.FileTypeNotSupported;
import java.io.*;
import java.text.BreakIterator;
import java.util.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

  private static Locale textLocale = new Locale("pt", "BR");
  private SupportedFiles supportedFiles;

  public String[] convertFileIntoSentences(MultipartFile file)
      throws IOException, FileTypeNotSupported {
    var fileExtension = file.getContentType();
    var textFromFile = "";

    if (!SupportedFiles.isSupported(fileExtension))
      throw new FileTypeNotSupported("O tipo de arquivo enviado não é suportado: " + fileExtension);

    if (fileExtension.equals(SupportedFiles.PDF.getDescription()))
      textFromFile = getTextFromPDF(file);

    return textSplitIntoSentences(textFromFile);
  }

  public String[] textSplitIntoSentences(String text) {
    var iterator = BreakIterator.getSentenceInstance(textLocale);
    iterator.setText(text);
    var start = iterator.first();
    var textIntoSentences = new ArrayList<>();

    for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
      var sentenceToAdd = text.substring(start, end).trim();
      if (!sentenceToAdd.isEmpty() && !sentenceToAdd.isBlank())
        textIntoSentences.add(sentenceToAdd);
    }

    return textIntoSentences.toArray(new String[textIntoSentences.size()]);
  }

  private String getTextFromPDF(MultipartFile multipartFile) throws IOException {
    var pdfDocument = new PDFTextStripper();
    var document = PDDocument.load(convertMultipartFileIntoFile(multipartFile));
    var textInPdf = "";
    var pdfContent = "";

    if (!document.isEncrypted()) {
      textInPdf = pdfDocument.getText(document);
      var lines = textInPdf.split("\\\\r\\\\n\\\\r\\\\n");
      for (String line : lines) pdfContent += line;
    }

    return pdfContent.replaceAll("\n", " ");
  }

  private File convertMultipartFileIntoFile(MultipartFile multipartFile) throws IOException {
    var file = new File("src/main/resources/" + multipartFile.getName() + ".tmp");
    try (OutputStream os = new FileOutputStream(file)) {
      os.write(multipartFile.getBytes());
    }

    return file;
  }
}