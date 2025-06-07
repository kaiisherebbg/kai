
import java.util.Scanner;

public class BasicHangman {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // List of words to guess
        String[] words = {
            "apple", "java", "ball", "cat", "dog", "fish", "tree", "book", "car", "sun",
            "python", "hangman", "computer", "keyboard", "internet", "monitor", "software",
            "database", "variable", "function", "algorithm", "programming", "development",
            "infrastructure", "optimization", "architecture", "cybersecurity",
            "microprocessor", "implementation", "virtualization",  "oral", "vedam"
        };

        // ASCII art stages of hangman for each wrong guess count
        String[] hangmanStages = {
            """
              +---+
              |   |
                  |
                  |
                  |
                  |
            =========
            """,
            """
              +---+
              |   |
              O   |
                  |
                  |
                  |
            =========
            """,
            """
              +---+
              |   |
              O   |
              |   |
                  |
                  |
            =========
            """,
            """
              +---+
              |   |
              O   |
             /|   |
                  |
                  |
            =========
            """,
            """
              +---+
              |   |
              O   |
             /|\\  |
                  |
                  |
            =========
            """,
            """
              +---+
              |   |
              O   |
             /|\\  |
             /    |
                  |
            =========
            """,
            """
              +---+
              |   |
              O   |
             /|\\  |
             / \\  |
                  |
            =========
            """
        };

        String playAgain;

        do {
            // Select a random word
            int randomIndex = (int)(Math.random() * words.length);
            String word = words[randomIndex];

            int maxAttempts = hangmanStages.length - 1; // 6 wrong attempts allowed
            int attempts = 0;
            String guessedLetters = "";
            char[] maskedWord = new char[word.length()];

            // Initialize masked word with underscores
            for (int i = 0; i < maskedWord.length; i++) {
                maskedWord[i] = '_';
            }

            boolean wordGuessed = false;

            while (attempts < maxAttempts && !wordGuessed) {
                System.out.println(hangmanStages[attempts]);
                System.out.println("Word: " + String.valueOf(maskedWord));
                System.out.print("Guess a letter: ");
                char guess = sc.next().toLowerCase().charAt(0);

                // Check if already guessed
                if (guessedLetters.indexOf(guess) != -1) {
                    System.out.println("You already guessed that letter. Try again.");
                    continue;
                }

                guessedLetters += guess;

                boolean found = false;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        maskedWord[i] = guess;
                        found = true;
                    }
                }

                if (!found) {
                    attempts++;
                    System.out.println("Wrong guess! Attempts left: " + (maxAttempts - attempts));
                }

                if (String.valueOf(maskedWord).equals(word)) {
                    wordGuessed = true;
                }
            }

            System.out.println(hangmanStages[attempts]); // final stage

            if (wordGuessed) {
                System.out.println("\nCongratulations! You guessed the word: " + word);
            } else {
                System.out.println("\nGame Over! The word was: " + word);
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            playAgain = sc.next().toLowerCase();

        } while (playAgain.equals("yes"));

        System.out.println("Thanks for playing!");
        sc.close();
    }
}
