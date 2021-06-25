package com.summarizer.service;

import com.summarizer.enums.SupportedLanguagesEnum;
import com.summarizer.enums.closed_class_words.BrasilianPortugueseEnum;
import com.summarizer.enums.closed_class_words.EnglishEnum;
import com.summarizer.exceptions.FileTypeNotSupported;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.summarizer.exceptions.FileWithoutContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SummarizerService {

  private final StringService stringService = new StringService();
  private static final int THRESHOLD = 3;
  @Autowired private FileService fileService;

  public String generateSummary(MultipartFile file, SupportedLanguagesEnum language) throws IOException, FileTypeNotSupported, FileWithoutContent {
    String[] sentences = fileService.convertFileIntoSentences(file, language);
    var timesWordRepeated = new HashMap<String, ArrayList<Integer>>();
    var linkMatrix = new int[sentences.length][sentences.length];
    var bondMatrix = new int[sentences.length][sentences.length];

    makeListWithAllWordsAndQuantityOfSentences(sentences, timesWordRepeated, language);
    makeLinkMatrix(timesWordRepeated, linkMatrix);
    makeBondMatrix(linkMatrix, bondMatrix);

    return formatSummary(bondMatrix, sentences);
  }

  private String formatSummary(int[][] bondMatrix, String[] sentences) {
    var sentencesToMakeSummary = new ArrayList<Integer>();
    var summary = new StringBuilder();

    for (var currentSentence = 0; currentSentence <= bondMatrix.length - 1; currentSentence++) {
      for (var setenceToCompareWith = currentSentence + 1;
          setenceToCompareWith <= bondMatrix.length - 1;
          setenceToCompareWith++) {
        if (bondMatrix[currentSentence][setenceToCompareWith] == 1) {
          if (!sentencesToMakeSummary.contains(currentSentence))
            sentencesToMakeSummary.add(currentSentence);
          if (!sentencesToMakeSummary.contains(setenceToCompareWith))
            sentencesToMakeSummary.add(setenceToCompareWith);
        }
      }
    }

    Collections.sort(sentencesToMakeSummary);

    for (int sentenceIndex : sentencesToMakeSummary) {
      summary.append(sentences[sentenceIndex] + " ");
    }

    return summary.toString();
  }

  private void makeLinkMatrix(
      HashMap<String, ArrayList<Integer>> timesWordRepeated, int[][] linkMatrix) {
    for (Map.Entry<String, ArrayList<Integer>> wordKey : timesWordRepeated.entrySet()) {
      var timesRepeated = wordKey.getValue();
      var sentencesWithRelationBetweenThem = new ArrayList<Integer>();

      for (var currentSentence = 0;
          currentSentence <= timesRepeated.size() - 1;
          currentSentence++) {
        if (timesRepeated.get(currentSentence) > 0)
          sentencesWithRelationBetweenThem.add(currentSentence);
      }

      if (!sentencesWithRelationBetweenThem.isEmpty())
        for (int currentSentenceIndex : sentencesWithRelationBetweenThem) {
          var sentencesToLinkWith =
              sentencesWithRelationBetweenThem.stream()
                  .filter(index -> currentSentenceIndex < index)
                  .collect(Collectors.toCollection(ArrayList::new));
          for (var sentenceToLinkWith : sentencesToLinkWith)
            linkMatrix[currentSentenceIndex][sentenceToLinkWith] =
                linkMatrix[currentSentenceIndex][sentenceToLinkWith] + 1;
        }
    }
  }

  private void makeBondMatrix(int[][] linkMatrix, int[][] bondMatrix) {
    for (var currentSentence = 0; currentSentence <= linkMatrix.length - 1; currentSentence++) {
      for (var setenceToCompareWith = currentSentence + 1;
          setenceToCompareWith <= linkMatrix.length - 1;
          setenceToCompareWith++) {
        if (linkMatrix[currentSentence][setenceToCompareWith] > THRESHOLD)
          bondMatrix[currentSentence][setenceToCompareWith] = 1;
      }
    }
  }

  private void makeListWithAllWordsAndQuantityOfSentences(
      String[] sentences, HashMap<String, ArrayList<Integer>> timesWordRepeated, SupportedLanguagesEnum language) {
    for (String sentence : sentences) {
      sentence = stringService.formatSentence(sentence);
      var words = fileService.textSplitIntoWords(sentence, language);
      Arrays.stream(words)
          .filter(
              word -> !timesWordRepeated.containsKey(word) && verifyIfWordIsNotClossedClass(language, word))
          .forEach(
              word ->
                  timesWordRepeated.put(
                      word, new ArrayList<>(Collections.nCopies(sentences.length, 0))));

      countRepeatedWords(sentences, timesWordRepeated);
    }
  }

  private void countRepeatedWords(
      String[] sentences, HashMap<String, ArrayList<Integer>> timesWordRepeated) {
    for (var atualSentenceKey = 0; atualSentenceKey <= sentences.length - 1; atualSentenceKey++) {
      var atualSentenceString = sentences[atualSentenceKey];
      var sentence = stringService.formatSentence(atualSentenceString);
      var wordsFromSentence = sentence.split(" ");

      for (String word : wordsFromSentence) {
        int finalAtualSentenceKey = atualSentenceKey;
        timesWordRepeated.computeIfPresent(
            word,
            (key, value) -> {
              var timesAtualWordWasRepeated = timesWordRepeated.get(word);
              timesAtualWordWasRepeated.set(
                  finalAtualSentenceKey, timesAtualWordWasRepeated.get(finalAtualSentenceKey) + 1);
              return timesAtualWordWasRepeated;
            });
      }
    }
  }

  private boolean verifyIfWordIsNotClossedClass(SupportedLanguagesEnum language, String word) {
    var isClosedClassWord = false;

    if (language.equals(SupportedLanguagesEnum.BRAZILIAN_PORTUGUESE))
      isClosedClassWord = !BrasilianPortugueseEnum.isBrazilianPortugueseClosedClassWord(word);
    if (language.equals(SupportedLanguagesEnum.ENGLISH))
      isClosedClassWord = !EnglishEnum.isEnglishClosedClassWord(word);

    return isClosedClassWord;
  }
}
