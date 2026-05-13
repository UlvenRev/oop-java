import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Phonetic_Alphabet {
    private Scanner inputScanner = new Scanner(System.in);
    private String userInput;
    private Dictionary<String, String[]> alphabet = new Hashtable<>(); // A String array for the VALUE of the KEY to
                                                                       // store
    // both the word and the hint in one place

    private Random rand = new Random();
    String availableLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Phonetic_Alphabet() {

        fill_dict(alphabet); // Filling the dictionary with letter: word, hint structure

        while (true) {
            System.out.print("\nChoose either a Learn Mode or Test Mode (l/t) (e to Exit): ");
            userInput = inputScanner.nextLine();
            if (userInput.toLowerCase().equals("l")) {
                learn_mode();
                // Don't break the while loop - if you exit the learn mode, you may want to go
                // straight to the test mode and not exit the program completely
            } else if (userInput.toLowerCase().equals("t")) {
                test_mode();
            } else if (userInput.toLowerCase().equals("e")) {
                System.out.println("\nAccess to the NATO database is closed.");
                break;
            } else {
                System.out.println("\nInvalid operation. Try again.\n");
            }
        }

        inputScanner.close();
    }

    public void learn_mode() {
        System.out.println("\nWelcome to Learn Mode! Here you can learn each word of the phonetic alphabet.");

        while (true) {
            System.out.println("Choose a letter (A-Z) or exit (EXIT):");
            userInput = inputScanner.nextLine().toUpperCase();
            if (userInput.length() == 1) { // If it's a letter:
                char letter = userInput.charAt(0); // Tranforming String to a character

                if (availableLetters.indexOf(letter) != -1) { // The letter entered IS in our dictionary
                    System.out.println("Letter: " + letter);
                    String[] values = alphabet.get(userInput); // Using userInput and not letter, because
                    // the key must be a STRING
                    System.out.println("Word: " + values[0]);
                    System.out.println("Hint: " + values[1] + "\n");
                } else {
                    System.out.println("\nInvalid letter/symbol. Try again.\n");
                }
            } else if (userInput.equals("EXIT")) {
                System.out.println("\nExiting the Learning Mode...\n");
                break;
            } else {
                System.out.println("\nInvalid, input must be one single letter or the EXIT command. Try again.");
            }
        }
    }

    public void test_mode() {
        System.out.println(
                "\nWelcome to Test Mode! Here you can test your knowledge. Input the correct word for the letter given below, or type EXIT to finish the test earlier.\n");

        int letterIndex; // Random number is recorder in here
        int[] usedLetterIndices = new int[26]; // To store the letters we've used already
        Arrays.fill(usedLetterIndices, -1); // Each value to -1 to mark them as unused
        String targetLetter; // The letter we extract from the dictionary
        String targetValue; // The word we extract from the dictionary
        int score = 0;

        int i = 0; // To keep track of elements in usedLetterIndices
        while (i != 26) { // While we haven't used all 26 letters (0-25)
            letterIndex = rand.nextInt(26);

            if (usedLetterIndices[letterIndex] == -1) {
                usedLetterIndices[letterIndex] = 0; // Mark as used
            } else {
                continue; // Repeat the loop to choose another random integer
            }

            targetLetter = availableLetters.charAt(letterIndex) + ""; // + "" to convert the character to String
            System.out.print(targetLetter + ": ");

            userInput = inputScanner.nextLine().toUpperCase();
            targetValue = alphabet.get(targetLetter)[0];

            if (userInput.toLowerCase().equals("exit")) {
                break;
            }

            if (userInput.toLowerCase().equals(targetValue.toLowerCase())) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Wrong. Correct answer: " + targetValue + "\n");
            }

            i++;
        }

        System.out.println("Test completed! Final score: " + score + "/26");
    }

    public void fill_dict(Dictionary<String, String[]> d) {
        d.put("A", new String[] { "Alpha", "The classic, like the Alpha wolf in a pack." });
        d.put("B", new String[] { "Bravo", "The classic team callsign in all movies - Bravo-6, you copy?" });
        d.put("C", new String[] { "Charlie", "A popular dog name?" });
        d.put("D", new String[] { "Delta", "Elite Delta Force." });
        d.put("E", new String[] { "Echo", "Overwatch character Echo!" });
        d.put("F", new String[] { "Foxtrot", "Ukrainian tech shop Foxtrot!" });
        d.put("G", new String[] { "Golf", "Never knew what's fun in it, you have to walk a lot." });
        d.put("H", new String[] { "Hotel", "H looks like a double" });
        d.put("I", new String[] { "India", "One of the dimensions in Spider-Man: Across the Spider-verse!" });
        d.put("J", new String[] { "Juliett", "Basic, like in Romeo and Juliett (you'll also encounter Romeo)." });
        d.put("K", new String[] { "Kilo", "Call of Duty team with the same callsign \"Kilo\"." });
        d.put("L", new String[] { "Lima", "Like a Lemon." });
        d.put("M", new String[] { "Mike", "Main character of Suits!" });
        d.put("N", new String[] { "November", "The month of leaf fall in Ukraine!" });
        d.put("O", new String[] { "Oscar", "Ukrainian cinema company!" });
        d.put("P", new String[] { "Papa", "Papa John's pizza." });
        d.put("Q", new String[] { "Quebec", "The French-speaking province of Canada." });
        d.put("R", new String[] { "Romeo", "Basic, and before him goes Juliett." });
        d.put("S", new String[] { "Sierra", "Mountain ranges like the Sierra Nevada." });
        d.put("T", new String[] { "Tango", "It takes two to Tango." });
        d.put("U", new String[] { "Uniform", "What soldiers and schoolkids wear every single day." });
        d.put("V", new String[] { "Victor", "League of Legends character Viktor!" });
        d.put("W", new String[] { "Whiskey", "The W looks like a whiskey glass." });
        d.put("X", new String[] { "Xray", "Done on broken bones most of the time." });
        d.put("Y", new String[] { "Yankee", "Always hear this one in moovies - New York Yankees baseball team." });
        d.put("Z", new String[] { "Zulu", "The Zulu nation of South Africa." });
    }

    public static void main(String[] args) {
        new Phonetic_Alphabet();
    }
}