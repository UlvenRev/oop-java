# OOP Java Assignments Description
There are comments in each file to help understand the code!

## Term 1
### Composition, Inheritance, Interfaces and Abstraction

First few assignments allowed to practice core principles of OOP. The goal was to get comfortable with how objects relate to each other:
- Composition - one is **built from** others
- Inheritance - one is a **type of** another
- Interfaces and abstraction - **behaves** a certain way

Interfaces can be seen very often in tasks from Term 2.

### Shopping Cart System

Main task was to practice **classes hierarchy**. Some data for tests is written manually in the main method, some can be entered through the terminal. 


## Term 2
These tasks were more practical and interesting.
### Guessing Game 

This one is a **console game** where you start with the inital tree of questions and answers. If the program is not able to guess your word, give a distinguishing question from the incorrect guess that was given, and the program will be "smarter" the next time you play.

Implemented using Binary Tree structure - easier to traverse the tree of answers and update it.

You can also **save/load** the existing tree of answers. 

### Palindrome Detection

Consists of **4 different methods** for implementing palindrome testing:
1. Reversing the string and checking two of them
2. Symmetric check (going from both ends and checking for identical characters)
3. Stack and Queue approach
4. Reverse the string using recursion and compare with the original

Additional thing: counting primitive operations to compare the 4 methods.

### Phonetic Alphabet

A **console game** where you have two modes:
- Learning mode (choose a letter and receive the word + a hint to remember it)
- Test mode (letters are given in random order, you have to provide the answer)

The structure used is Dictionary - easy to store key-value pairs.
