------------------------------------------------------------------------
import java.util.Scanner;

public class BasicHangman {
    public static void main(String[] args) {
        // Word to guess (hardcoded)
        String word = "java";
        int maxAttempts = 6;
        int attempts = 0;

        // Create a masked word (e.g., "_ _ _ _")
        char[] maskedWord = new char[word.length()];
        for (int i = 0; i < maskedWord.length; i++) {
            maskedWord[i] = '_';
        }

        // Store guessed letters
        String guessedLetters = "";

        Scanner sc = new Scanner(System.in);
        System.out.println("🎮 Welcome to Hangman!");

        while (attempts < maxAttempts) {
            // Display current state of word
            System.out.print("Word: ");
            for (char c : maskedWord) {
                System.out.print(c + " ");
            }
            System.out.println();

            // Ask for input
            System.out.print("Guess a letter: ");
            char guess = sc.next().toLowerCase().charAt(0);

            // Check if letter was already guessed
            if (guessedLetters.indexOf(guess) != -1) {
                System.out.println("⚠️ You already guessed that letter.\n");
                continue;
            }

            guessedLetters += guess;

            // Check if guess is in the word
            boolean found = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    maskedWord[i] = guess;
                    found = true;
                }
            }

            if (found) {
                System.out.println("✅ Good guess!\n");
            } else {
                attempts++;
                System.out.println("❌ Wrong guess! Attempts left: " + (maxAttempts - attempts) + "\n");
            }

            // Check if the word is fully guessed
            if (String.valueOf(maskedWord).equals(word)) {
                System.out.println("🎉 You guessed the word: " + word);
                break;
            }
        }

        if (attempts == maxAttempts) {
            System.out.println("💀 You lost! The word was: " + word);
        }

        sc.close();
    }
}

