import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class ReviewRunner {
  public static void main(String[] args) {
    ArrayList<String> f1Reviews = new ArrayList<>();
    ArrayList<String> supermanReviews = new ArrayList<>();

    try (Scanner scanner = new Scanner(new File("f1Reviews.txt"))) {
      while (scanner.hasNextLine()) {
        f1Reviews.add(scanner.nextLine().trim());
      }
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
    }

    try (Scanner scanner2 = new Scanner(new File("supermanReviews.txt"))) {
      while (scanner2.hasNextLine()) {
        supermanReviews.add(scanner2.nextLine().trim());
      }
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
    }

    double f1Score = Review.calculateTotalSentiment(f1Reviews);
    double supermanScore = Review.calculateTotalSentiment(supermanReviews);

    System.out.println("Total sentiment score for F1: " + f1Score);
    System.out.println("Total sentiment score for Superman: " + supermanScore);

    if (f1Score > supermanScore) {
      System.out.println("F1 is better rated.");
    } else if (supermanScore > f1Score) {
      System.out.println("Superman is better rated.");
      else if(f1Score==supermanScore) && (f1Score !=0){
        System.out.println("Both movies are rated the same");
      }
    } 
  }
}