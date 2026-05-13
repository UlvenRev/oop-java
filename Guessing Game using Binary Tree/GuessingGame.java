import java.io.*;
import java.util.Scanner;

public class GuessingGame {

    private static final String DEFAULT_SAVE_FILE = "game_tree.ser"; // .ser extension instead of .txt, because .ser is
                                                                     // able to store a binary representation of the
                                                                     // data => easier to use for the tree

    private BinaryTree<String> tree; // Since each node holds a String, the tree will have a String type

    private Scanner scanner;

    public GuessingGame() {
        scanner = new Scanner(System.in);
        tree = buildInitialTree();
    }

    private BinaryTree<String> buildInitialTree() {

        // Starting from the BOTTOM of the tree - the leaf nodes, which are the guesses
        // (level 5)
        BinaryTree<String> domesticLeafYes = new BinaryTree<>("Is it a cat?");
        BinaryTree<String> domesticLeafNo = new BinaryTree<>("Is it an elephant?");
        BinaryTree<String> reptileLeafYes = new BinaryTree<>("Is it a crocodile?");
        BinaryTree<String> reptileLeafNo = new BinaryTree<>("Is it a frog?");
        BinaryTree<String> actorLeafYes = new BinaryTree<>("Is it Andrew Garfield?");
        BinaryTree<String> actorLeafNo = new BinaryTree<>("Is it Hans Zimmer?");
        BinaryTree<String> plantLeafYes = new BinaryTree<>("Is it a cactus?");
        BinaryTree<String> plantLeafNo = new BinaryTree<>("Is it a bacteria?");
        BinaryTree<String> beingLeafNo = new BinaryTree<>("Is it a pen?");

        // Moving up one level to the PARENT nodes, giving them their children from
        // above (level 4)
        BinaryTree<String> isDomestic = new BinaryTree<>("Is it a domestic animal?", domesticLeafYes, domesticLeafNo);
        BinaryTree<String> isReptile = new BinaryTree<>("Is it a reptile?", reptileLeafYes, reptileLeafNo);
        BinaryTree<String> isActor = new BinaryTree<>("Is it a fish?", actorLeafYes, actorLeafNo);
        BinaryTree<String> isPlant = new BinaryTree<>("Is it a bird?", plantLeafYes, plantLeafNo);

        // Level 3
        BinaryTree<String> isMammal = new BinaryTree<>("Is it a mammal?", isDomestic, isReptile);
        BinaryTree<String> isPerson = new BinaryTree<>("Is it a person?", isActor, isPlant);

        // Level 2
        BinaryTree<String> isAnimal = new BinaryTree<>("Is it an animal?", isMammal, isPerson);
        // No second branch for a "No" answer for LEVEL 1 because I'm keeping the tree
        // simpler

        // Level 1 - the root node
        BinaryTree<String> root = new BinaryTree<>("Are you thinking of a living being?", isAnimal, beingLeafNo);
        return root;
    }

    public void play() {
        System.out.println("Welcome to the Guessing Game!");
        System.out.println("-----------------------------------");

        boolean keepPlaying = true;
        while (keepPlaying) { // Main loop of the game
            keepPlaying = playOneRound();
        }

        System.out.println("\nFinished playing.");
    }

    private boolean playOneRound() {
        System.out.println("Think of something... Press Enter when ready.");
        scanner.nextLine(); // Scanning an empty line from the user (the Enter)

        // Traversing from the root node
        BinaryNodeInterface<String> currentNode = tree.getRootNode();

        // Tracking both the PARENT and from which SIDE we came (either yes or no)
        // This is needed to replace the node later, in case the guess is not in the
        // tree
        BinaryNodeInterface<String> parentNode = null;
        boolean cameFromLeft = false; // Was the last step a YES (came from the left)?

        while (!currentNode.isLeaf()) { // While we haven't reached any LEAF (a possible answer)
            String question = currentNode.getData(); // Getting the data from he node - the question
            boolean answeredYes = askYesNoQuestion(question); // Getting the answer in the boolean form

            parentNode = currentNode; // Since we haven't reached the leaf node yet, we make the current node a PARENT
            cameFromLeft = answeredYes;
            currentNode = answeredYes ? currentNode.getLeftChild() : currentNode.getRightChild(); // and take the next
                                                                                                  // child based on
                                                                                                  // which way we went
                                                                                                  // (yes - left, no -
                                                                                                  // right)
        }

        // The while loop finished => we're at the LEAF node and this is the game's
        // guess
        String guess = currentNode.getData();
        boolean answeredYes = askYesNoQuestion(guess); // Asking the final question and getting a yes/no answer

        if (!answeredYes) { // If the guess is NOT correct - learn the new answer
            System.out.println("\nI don't know. What is the correct answer?");
            return learnNewAnswer(parentNode, currentNode, cameFromLeft);
        } else {
            System.out.println("Great! I guessed correctly!");
            return showPostGameMenu();
        }
    }

    private boolean askYesNoQuestion(String question) {
        while (true) { // An infinite loop to keep asking the user for either "yes" or "no" and not
                       // accepting any other input
            System.out.print(question + " (yes/no or y/n): ");
            String answer = scanner.nextLine().trim().toLowerCase(); // .trim() removes any whitespaces from the start
                                                                     // and end
            if (answer.equals("yes") || answer.equals("y"))
                return true; // Return breaks the while loop and we come back to the main logic from where
                             // this method was called
            if (answer.equals("no") || answer.equals("n"))
                return false;

            System.out.println("Please enter \"yes\" (y) or \"no\" (n).\n");
        }
    }

    public boolean showPostGameMenu() {
        while (true) { // So that we can ask for the valid input each time it's not 1-5
            System.out.println("\nWould you like to:");
            System.out.println("  1. Play a round");
            System.out.println("  2. Save the tree to file");
            System.out.println("  3. Load a tree from file");
            System.out.println("  4. Print the current tree");
            System.out.println("  5. Quit");
            System.out.print("Your choice (1-5): ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    play();
                    return true;
                case "2":
                    saveTree();
                    break;
                case "3":
                    loadTree();
                    break;
                case "4":
                    printTree();
                    break;
                case "5":
                    return false;
                default:
                    System.out.println("Please enter a number between 1 and 5.");
            }
        }
    }

    private boolean learnNewAnswer(
            BinaryNodeInterface<String> parentNode,
            BinaryNodeInterface<String> leafNode,
            boolean leafWasLeft) {

        String oldGuess = leafNode.getData(); // Saving the leaf that was a WRONG GUESS to keep in the tree

        // Asking for the correct answer
        String newAnswer = "";
        do {
            newAnswer = scanner.nextLine().trim();
        } while (newAnswer.isEmpty()); // So that we don't accept an empty answer

        // Asking for a distinguishing question
        System.out.println(
                "\nWhat is a yes/no question that tells \"" + newAnswer + "\" apart from \"" + oldGuess + "\"?");
        System.out.print("Question: ");

        String newQuestion = "";
        do {
            newQuestion = scanner.nextLine().trim();
        } while (newQuestion.isEmpty()); // So that we don't accept an empty question

        // Getting the corrent answer to THIS NEW question for the INCORRECT guess from
        // the game
        System.out.println("\nFor your question: \"" + newQuestion + "\"");
        boolean newAnswerIsYes = askYesNoQuestion("Right answer for \"" + oldGuess + "\" is (yes/no): ");

        // Building two new LEAF nodes for the new question which was given
        BinaryNode<String> newAnswerLeaf = new BinaryNode<>("Is it a(n) " + newAnswer + "?"); // Making a question with
                                                                                              // the new correct guess
        BinaryNode<String> oldGuessLeaf = new BinaryNode<>(oldGuess);

        // Depending on what the right answer is for the OLD guess: Yes => old guess
        // goes on the LEFT, otherwise (for No) it goes on the RIGHT
        BinaryNode<String> newQuestionNode;
        if (newAnswerIsYes) { // TRUE => Yes => OLD goes on the LEFT
            newQuestionNode = new BinaryNode<>(newQuestion, oldGuessLeaf, newAnswerLeaf);
        } else { // OLD goes on the RIGHT
            newQuestionNode = new BinaryNode<>(newQuestion, newAnswerLeaf, oldGuessLeaf);
        }

        if (parentNode == null) { // No parent for this root node means it's the only node in the tree
            tree.setRootNode(newQuestionNode);
        } else if (leafWasLeft) {
            parentNode.setLeftChild(newQuestionNode);
        } else {
            parentNode.setRightChild(newQuestionNode);
        }

        System.out.println("\nA new entry was now added to the tree!");

        return showPostGameMenu();
    }

    private void saveTree() {
        System.out.print("Enter filename to save to (press Enter for \"" + DEFAULT_SAVE_FILE + "\"): ");
        String filename = scanner.nextLine().trim();
        if (filename.isEmpty())
            filename = DEFAULT_SAVE_FILE; // In case the user doesn't provide a file name, it will default to
                                          // game_tree.ser and rewrite whatever's in there

        // ObjectOutputStream is what converst the Java object into a stream of bytes
        // FileOutputStream saves the data to an actual physical file
        // try-catch is needed to close the FileOutputStream pipe automatically when we
        // saved the tree into the file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tree);
            System.out.println("Tree saved successfully to \"" + filename + "\".");
        } catch (IOException e) {
            System.out.println("Error saving tree: " + e.getMessage());
        }
    }

    private void loadTree() {
        // Same logic as for saving the tree
        System.out.print("Enter filename to load from (press Enter for \"" + DEFAULT_SAVE_FILE + "\"): ");
        String filename = scanner.nextLine().trim();
        if (filename.isEmpty())
            filename = DEFAULT_SAVE_FILE;

        File file = new File(filename); // Creating this object to check for file's existance and length

        if (!file.exists()) {
            System.out.println("NF: File not found. Keeping the current tree.");
            return; // Exit the function
        }

        if (file.length() == 0) {
            System.out.println("EMPTY: This file is empty. Keeping the current tree.");
            return;
        }

        // Passing the two checks above, it's safe to pull data from the file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            // Pulling the data from the file with .readObject() and then casting it into a
            // BinaryTree object
            tree = (BinaryTree<String>) ois.readObject(); // This the tree variable we decalred globally
            System.out.println("Tree loaded successfully from \"" + filename + "\".");
        } catch (IOException | ClassNotFoundException e) { // ClassNotFoundException is needed for casting, otherwise
                                                           // gives an error
            System.out.println("Error loading tree: " + e.getMessage());
        }
    }

    public void printTree() {
        System.out.println("\nCurrent Guessing Tree ---------------------------");
        if (tree.isEmpty()) {
            System.out.println("(nothing in the tree yet)");
        } else {
            printNode(tree.getRootNode(), 0, "ROOT"); // Starting from the root, so passing its label (ROOT) and depth
                                                      // of 0
        }
        System.out.println("---------------------------------------------------\n");
    }

    private void printNode(BinaryNodeInterface<String> node, int depth, String label) {
        if (node == null)
            return; // Stopping the current recursive branch we're on if we reach the final node on
                    // it

        String gap = "    ".repeat(depth); // .repeat() tells how many times to "multiply" the empty space by, since we
                                           // can't use * with strings
        String type = node.isLeaf() ? "GUESS" : "QUESTION"; // Also displaying which one is a guess and which one is a
                                                            // question

        // Formatting the string:
        // %s is for the gap (the whitespaces)
        // %-6s: "-" aligns the text on the left, "6" says to reverse at least 6
        // characters for the text
        // %s prints the type (GUESS or QUESTION)
        // %s prints the actual question from teh node
        // %n jumps to the next line
        System.out.printf("%s%-6s %s: %s%n", gap, label, type, node.getData());

        if (!node.isLeaf()) { // If this current node we're on is NOT the last one (not the leaf)
            printNode(node.getLeftChild(), depth + 1, "[YES]"); // then go down ONE LEVEL DEEPER on both right and left
                                                                // sides
            printNode(node.getRightChild(), depth + 1, "[NO] ");
        }
    }

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();
        game.printTree(); // Printing the initial tree to make sure the structure is correct
        game.showPostGameMenu(); // Starting the infinite loop for the game
    }
}
