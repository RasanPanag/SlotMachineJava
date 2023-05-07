import java.util.Random;
import java.util.Scanner;

public class SlotMachine {

    public static void main(String[] args) {
        // Set up the slot machine
        String[] symbols = {"Cherry", "Lemon", "Orange", "Plum", "Bell", "Bar"};
        int[] payout = {2, 3, 4, 5, 10, 50};
        int credits = 10;
        int bet;

        // Initialize random number generator
        Random random = new Random();

        // Set up input scanner
        Scanner scanner = new Scanner(System.in);

        // Main game loop
        while (credits > 0) {
            // Get the player's bet
            System.out.println("You have " + credits + " credits. Place your bet (1-5): ");
            bet = scanner.nextInt();

            if (bet < 1 || bet > 5) {
                System.out.println("Invalid bet. Please enter a bet between 1 and 5.");
                continue;
            }

            // Spin the reels
            String[] reels = new String[3];
            for (int i = 0; i < 3; i++) {
                int symbolIndex = random.nextInt(6);
                reels[i] = symbols[symbolIndex];
                System.out.print(reels[i] + " ");
            }
            System.out.println();

            // Calculate the payout
            int payoutMultiplier = 0;
            if (reels[0].equals(reels[1]) && reels[1].equals(reels[2])) {
                payoutMultiplier = payout[indexOf(symbols, reels[0])];
            } else if (reels[0].equals("Cherry") && reels[1].equals("Cherry")) {
                payoutMultiplier = 3;
            } else if (reels[0].equals("Cherry")) {
                payoutMultiplier = 2;
            }

            int payoutAmount = payoutMultiplier * bet;
            if (payoutAmount > 0) {
                System.out.println("You won " + payoutAmount + " credits!");
            } else {
                System.out.println("Sorry, you didn't win anything this time.");
            }

            // Update the credits
            credits += payoutAmount - bet;
        }

        System.out.println("Game over. You ran out of credits.");
    }

    private static int indexOf(String[] array, String element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

}
