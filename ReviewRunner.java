import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class ReviewRunner {
  public static void main(String[] args) {
    ArrayList<String> f1Reviews = new ArrayList<>();
    ArrayList<String> supermanReviews = new ArrayList<>();

    // Load F1 reviews
    try (Scanner scanner = new Scanner(new File("f1Reviews.txt"))) {
      while (scanner.hasNextLine()) {
        f1Reviews.add(scanner.nextLine().trim());
      }
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
    }

    // Load Superman reviews
    try (Scanner scanner2 = new Scanner(new File("supermanReviews.txt"))) {
      while (scanner2.hasNextLine()) {
        supermanReviews.add(scanner2.nextLine().trim());
      }
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
    }

    double f1Score = 0.0;
    double supermanScore = 0.0;

    // Analyze F1 reviews
    for (String review : f1Reviews) {
      double score = Review.totalSentimentFromText(review);
      f1Score += score;
    }

    // Analyze Superman reviews
    for (String review : supermanReviews) {
      double score = Review.totalSentimentFromText(review);
      supermanScore += score;
    }

    System.out.println("Total sentiment score for F1: " + f1Score);
    System.out.println("Total sentiment score for Superman: " + supermanScore);

    // Compare and print result
    if (f1Score > supermanScore) {
      System.out.println("F1 is better rated.");
    } else if (supermanScore > f1Score) {
      System.out.println("Superman is better rated.");
    } else {
      System.out.println("Both movies are equally rated.");
    }
  }
}
