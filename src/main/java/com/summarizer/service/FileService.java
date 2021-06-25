package com.summarizer.service;

import com.summarizer.enums.SupportedFilesEnum;
import com.summarizer.enums.SupportedLanguagesEnum;
import com.summarizer.exceptions.FileTypeNotSupported;
import java.io.*;
import java.text.BreakIterator;
import java.util.*;

import com.summarizer.exceptions.FileWithoutContent;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

  public String[] convertFileIntoSentences(MultipartFile file, SupportedLanguagesEnum language)
          throws IOException, FileTypeNotSupported, FileWithoutContent {
    var fileExtension = file.getContentType();
    var textFromFile = "";

    if (!SupportedFilesEnum.isSupported(fileExtension))
      throw new FileTypeNotSupported("O tipo de arquivo enviado não é suportado: " + fileExtension);
    if(fileExtension == null || fileExtension.isEmpty() || fileExtension.isBlank())
      throw new FileWithoutContent("O arquivo enviado não possui conteúdo.");

    if (fileExtension.equals(SupportedFilesEnum.PDF.getDescription()))
      textFromFile = getTextFromPDF(file);

    return textSplitIntoSentences(textFromFile, language);
  }

  private Locale getLocale(SupportedLanguagesEnum language) {
    return new Locale(language.getLanguage(), language.getCountry());
  }

  private String[] textSplitIntoSentences(String text, SupportedLanguagesEnum language) {
    var iterator = BreakIterator.getSentenceInstance(getLocale(language));
    return stringSplitByBreakIterator(text, iterator);
  }

  public String[] textSplitIntoWords(String text, SupportedLanguagesEnum language) {
    var iterator = BreakIterator.getWordInstance(getLocale(language));
    return stringSplitByBreakIterator(text, iterator);
  }

  private String[] stringSplitByBreakIterator(String text, BreakIterator iterator) {
    iterator.setText(text);
    var start = iterator.first();
    var textIntoWords = new ArrayList<>();

    for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
      var sentenceToAdd = text.substring(start, end).trim();
      if (!sentenceToAdd.isEmpty() && !sentenceToAdd.isBlank()) textIntoWords.add(sentenceToAdd);
    }

    return textIntoWords.toArray(new String[textIntoWords.size()]);
  }

  private String getTextFromPDF(MultipartFile multipartFile) throws IOException {
    var pdfDocument = new PDFTextStripper();
    var document = PDDocument.load(convertMultipartFileIntoFile(multipartFile));
    var textInPdf = new StringBuilder();
    var pdfContent = new StringBuilder();

    if (!document.isEncrypted()) {
      textInPdf.append(pdfDocument.getText(document));
      var lines = textInPdf.toString().split("\\\\r\\\\n\\\\r\\\\n");
      for (String line : lines) pdfContent.append(line);
    }

    document.close();
    return pdfContent.toString().replace("\n", " ");
  }

  private File convertMultipartFileIntoFile(MultipartFile multipartFile) throws IOException {
    var file = new File("src/main/resources/" + multipartFile.getName() + ".tmp");
    try (OutputStream os = new FileOutputStream(file)) {
      os.write(multipartFile.getBytes());
    }

    return file;
  }
}
