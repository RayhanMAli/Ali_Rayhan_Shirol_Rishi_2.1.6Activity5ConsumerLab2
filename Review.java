import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Review {

  private static HashMap<String, Double> sentiment = new HashMap<>();
  private static ArrayList<String> posAdjectives = new ArrayList<>();
  private static ArrayList<String> negAdjectives = new ArrayList<>();

  static {
    // Load sentiment values
    try {
      Scanner input = new Scanner(new File("cleanSentiment.csv"));
      while (input.hasNextLine()) {
        String[] temp = input.nextLine().split(",");
        sentiment.put(temp[0], Double.parseDouble(temp[1]));
      }
      input.close();
    } catch (Exception e) {
      System.out.println("Error reading or parsing cleanSentiment.csv");
    }

    // Load positive adjectives
    try {
      Scanner input = new Scanner(new File("positiveAdjectives.txt"));
      while (input.hasNextLine()) {
        posAdjectives.add(input.nextLine().trim());
      }
      input.close();
    } catch (Exception e) {
      System.out.println("Error reading or parsing positiveAdjectives.txt");
    }

    // Load negative adjectives
    try {
      Scanner input = new Scanner(new File("negativeAdjectives.txt"));
      while (input.hasNextLine()) {
        negAdjectives.add(input.nextLine().trim());
      }
      input.close();
    } catch (Exception e) {
      System.out.println("Error reading or parsing negativeAdjectives.txt");
    }
  }

  public static String textToString(String fileName) {
    String temp = "";
    try {
      Scanner input = new Scanner(new File(fileName));
      while (input.hasNext()) {
        temp += input.next() + " ";
      }
      input.close();
    } catch (Exception e) {
      System.out.println("Unable to locate " + fileName);
    }
    return temp.trim();
  }

  public static double sentimentVal(String word) {
    return sentiment.getOrDefault(word.toLowerCase(), 0.0);
  }

  public static String getPunctuation(String word) {
    String punc = "";
    for (int i = word.length() - 1; i >= 0; i--) {
      if (!Character.isLetterOrDigit(word.charAt(i))) {
        punc += word.charAt(i);
      } else {
        return punc;
      }
    }
    return punc;
  }

  public static String removePunctuation(String word) {
    while (word.length() > 0 && !Character.isAlphabetic(word.charAt(0))) {
      word = word.substring(1);
    }
    while (word.length() > 0 && !Character.isAlphabetic(word.charAt(word.length() - 1))) {
      word = word.substring(0, word.length() - 1);
    }
    return word;
  }

  public static String randomPositiveAdj() {
    int index = (int) (Math.random() * posAdjectives.size());
    return posAdjectives.get(index);
  }

  public static String randomNegativeAdj() {
    int index = (int) (Math.random() * negAdjectives.size());
    return negAdjectives.get(index);
  }

  public static String randomAdjective() {
    return Math.random() < 0.5 ? randomPositiveAdj() : randomNegativeAdj();
  }

  // Original method (used for file-based reviews)
  public static double totalSentiment(String filename) {
    String review = textToString(filename);
    String[] words = review.split(" ");
    double total = 0.0;
    for (String word : words) {
      String cleaned = removePunctuation(word);
      total += sentimentVal(cleaned);
    }
    return total;
  }

  // New method for direct review text
  public static double totalSentimentFromText(String reviewText) {
    String[] words = reviewText.split(" ");
    double total = 0.0;
    for (String word : words) {
      String cleaned = removePunctuation(word);
      total += sentimentVal(cleaned);
    }
    return total;
  }
}
