import java.util.function.Predicate; // For the runTest() method

public class PalindromeDetection {

    // Part D - Counting Primitive Operations
    public long opCount = 0;

    public PalindromeDetection() {
        // boolean method_1 = Iterative_Reverse("dad");
        // System.out.println(method_1);

        // boolean method_2 = Symmetric_Character("hello");
        // System.out.println(method_2);

        // boolean method_3 = Stack_Queue("dad");
        // System.out.println(method_3);

        // boolean method_4 = Recursive_Reverse("dad");
        // System.out.println(method_4);

        // String binary = Decimal_to_Binary("3");
        // System.out.println(binary);

        // Part C - Experimental Testing - 1 000 000 numbers
        runTest("ITERATIVE REVERSE", this::Iterative_Reverse, 1000000, true); // :: means we're POINTING to a method and
                                                                              // NOT
        // CALLING it right away. This allows us to PASS
        // it as Predicate into runTest() method and
        // call it only when we need it
        runTest("SYMMETRIC CHARACTER", this::Symmetric_Character, 1000000, true);
        runTest("STACK AND QUEUE", this::Stack_Queue, 1000000, true);
        runTest("RECURSIVE REVERSE", this::Recursive_Reverse, 1000000, true);

        // Part E - Complexity Analysis
        int[] intervals = { 50000, 100000, 150000, 200000, 250000, 300000, 350000,
                400000, 450000, 500000, 550000,
                600000, 650000, 700000, 750000, 800000, 850000, 900000, 950000, 1000000 };

        System.out.println("\n\nUpper Bound, Iterative, Symmetric, Stack and Queue, Recursive");
        for (int upperbound : intervals) {
            runTest("ITERATIVE REVERSE", this::Iterative_Reverse, upperbound, false);
            long op1 = opCount;
            runTest("SYMMETRIC CHARACTER", this::Symmetric_Character, upperbound, false);
            long op2 = opCount;
            runTest("STACK AND QUEUE", this::Stack_Queue, upperbound, false);
            long op3 = opCount;
            runTest("RECURSIVE REVERSE", this::Recursive_Reverse, upperbound, false);
            long op4 = opCount;
            System.out.println(upperbound + ", " + op1 + ", " + op2 + ", " + op3 + ", " +
                    op4);
        }
    }

    // Part A - Four Palindrome Detection Methods

    // Method 1
    public boolean Iterative_Reverse(String str) {
        String reversedStr = "";
        int n = str.length();
        opCount++; // "Weight" of 1 for initialization

        for (int i = n - 1; i >= 0; i--) { // Backwards loop to go from end to start of the string
            opCount++; // Loop condition check
            opCount += reversedStr.length(); // .charAt() requires COPYING ALL the characters to a new string => as much
                                             // as we have at the moment, which is reversedString.length()
            reversedStr += str.charAt(i);
        }
        opCount++; // Final count of the loop

        opCount += n; // .equals() operation compares every character => n steps
        return str.equals(reversedStr); // == compares refs in memory, .equals() compares the actual content of the
                                        // strings
    }

    // Method 2
    public boolean Symmetric_Character(String str) {
        // Two pointers for the string
        int right = str.length() - 1; // end
        int left = 0; // start
        opCount += 2;

        while (left < right) { // Each pointer moves to the center of the string, so keep incrementing them
                               // until they MEET in the middle and when they do - finish
            opCount += 2; // While condition check and the if statement
            if (str.charAt(left) != str.charAt(right)) { // If found a mismatch
                return false;
            }

            left++;
            right--;
            opCount += 2;
        }
        opCount++; // Final time for when the while loop fails

        return true;
    }

    // Method 3
    public boolean Stack_Queue(String str) {
        int n = str.length();
        ArrayStack stack = new ArrayStack(n);
        ArrayQueue queue = new ArrayQueue(n);
        opCount += 3;

        char[] charArray = str.toCharArray(); // Converting to character array for easier iterating
        opCount += n; // Iterate over each character to append them into the charArray

        for (char ch : charArray) {
            stack.push(ch);
            queue.enqueue(ch);
            opCount += 3; // Loop iteration and 2 methods
        }

        for (int i = 0; i < n; i++) { // For each element in both arrays
            // stack.pop() returns the TOP element => from the END of the string (reversed
            // order)
            // queue.dequeue() returns the BOTTOM element => from the START of the string
            // (normal order)
            opCount += 4; // Iteration, .pop(), .dequeue(), comparison
            if (stack.pop() != queue.dequeue())
                return false;
        }
        opCount++; // Final failed loop condition check

        return true;
    }

    // Method 4

    public boolean Recursive_Reverse(String str) {
        String reversed = reverse(str);

        opCount += str.length(); // For .equals() operation
        return str.equals(reversed);
    }

    public String reverse(String str) {
        opCount++; // For the if statement
        if (str.isEmpty()) // Base case - once we reach the end of the string, keep going back out of all
                           // the recursive calls that were made
            return str;

        opCount += str.length() - 1; // .substring(1) copies (n-1) characters into a new string
        opCount++; // charAt(0)
        return reverse(str.substring(1)) + str.charAt(0); // Take all the string FROM the SECOND character we have, take
                                                          // the FIRST character and add it to the end
    }

    // Part B - Decimal-to-Binary Conversion

    public String Decimal_to_Binary(String str) {
        String binary = "";

        int decimal = Integer.parseInt(str);
        String nextDigit;
        while (decimal != 0) {
            nextDigit = (decimal % 2 == 0) ? "0" : "1";
            binary = nextDigit + binary; // Appending the next digit to the START of the binary string - this is used to
                                         // avoid manually REVERSING it after we've assembled it backwards
            decimal /= 2;
        }

        return binary;
    }

    // Part C - Experimental Testing

    // Predicate<T> interface is a built-in "envelope" designed to hold any method
    // that takes one input of type T and returns a BOOLEAN
    // Predicate<String> means we accept only the methods which take in a STRING and
    // return TRUE or FALSE
    public void runTest(String methodName, Predicate<String> palindromeAlgorithm, int upperbound, boolean partC) {
        opCount = 0;

        int decimalPalsCount = 0;
        int binaryPalsCount = 0;
        int commonPalindromes = 0;

        String decimal, binary;
        boolean decimalPal, binaryPal;

        long startTime = System.nanoTime();
        for (int i = 0; i <= upperbound; i++) {
            decimal = String.valueOf(i); // Making a string out of int because we need to pass a string to our methods
            binary = Decimal_to_Binary(decimal);

            // Predicate has a syntax of "boolean test(T t)" => calling .test() envokes any
            // logic placed inside the predicate, i.e. the method we pass in
            decimalPal = palindromeAlgorithm.test(decimal);
            binaryPal = palindromeAlgorithm.test(binary);
            if (decimalPal)
                decimalPalsCount++;

            if (binaryPal)
                binaryPalsCount++;

            if (decimalPal && binaryPal)
                commonPalindromes++;

        }
        long endTime = System.nanoTime();
        long timeTaken = (endTime - startTime) / 1000000; // Divide by 1000000 to convert from ns to ms

        // Made for convenience - if it's not Part C I'm testing, but Part E, don't
        // print the following prints:
        if (partC) {
            System.out.println("Testing: " + methodName + "\n");
            System.out.println("Decimal palindromes: " + decimalPalsCount + "\nBinary palindromes: " + binaryPalsCount
                    + "\nBoth: " + commonPalindromes);
            System.out.println("Time taken to execute: " + timeTaken + " millisecond(s)");
            System.out.println("Total primitive operations: " + opCount + "\n");
        }
    }

    public static void main(String[] args) {
        new PalindromeDetection();
    }
}