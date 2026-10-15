import java.util.Scanner;

public class CinemaSeatBookingSystem {

    static Scanner sc = new Scanner(System.in);
    static String[] movies = {"Avengers", "Avatar", "Inception"};
    static int[] prices = {200, 180, 150};
    static boolean[][] seats = new boolean[5][5];

    // CO-3: Method to display available movies
    static void displayMovies() {
        System.out.println("\n--- AVAILABLE MOVIES ---");
        for (int i = 0; i < movies.length; i++) {
            System.out.println((i + 1) + ". " + movies[i]
                    + " - Rs." + prices[i]);
        }
    }

    // CO-3: Method to display seats
    static void displaySeats() {
        System.out.println("\n--- SEAT LAYOUT ---");
        System.out.println("O = Available, X = Booked");

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] ? "X " : "O ");
            }
            System.out.println();
        }
    }

    // CO-3: Method to count available seats
    static int countAvailableSeats() {
        int count = 0;

        for (boolean[] row : seats) {
            for (boolean seat : row) {
                if (!seat) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {

        // CO-1: Primitive and reference data types
        int choice, seatRow, seatCol, tickets;
        double total;
        String customerName;
        char snackChoice;
        boolean booked;

        // CO-6: StringBuffer
        StringBuffer bookingSummary = new StringBuffer();

        System.out.println("================================");
        System.out.println("   CINEMA SEAT BOOKING SYSTEM");
        System.out.println("================================");

        try {
            // CO-1: Input using Scanner
            System.out.print("Enter customer name: ");
            customerName = sc.nextLine().trim();

            if (customerName.isEmpty()) {
                throw new IllegalArgumentException(
                        "Customer name cannot be empty.");
            }

            int running = 1;

            // CO-2: while loop
            while (running == 1) {

                displayMovies();

                System.out.print("\nSelect movie (1-3): ");
                choice = Integer.parseInt(sc.nextLine());

                // CO-2: if-else-if ladder
                if (choice >= 1 && choice <= movies.length) {
                    System.out.println("Selected: " + movies[choice - 1]);
                } else {
                    throw new IllegalArgumentException(
                            "Invalid movie selection.");
                }

                displaySeats();

                System.out.print("Enter seat row (1-5): ");
                seatRow = Integer.parseInt(sc.nextLine());

                System.out.print("Enter seat column (1-5): ");
                seatCol = Integer.parseInt(sc.nextLine());

                // CO-2: Nested if
                if (seatRow >= 1 && seatRow <= 5) {
                    if (seatCol >= 1 && seatCol <= 5) {

                        if (!seats[seatRow - 1][seatCol - 1]) {
                            seats[seatRow - 1][seatCol - 1] = true;
                            booked = true;
                        } else {
                            booked = false;
                        }

                    } else {
                        throw new IllegalArgumentException(
                                "Invalid seat column.");
                    }
                } else {
                    throw new IllegalArgumentException(
                            "Invalid seat row.");
                }

                if (!booked) {
                    System.out.println("Seat already booked!");
                    System.out.print("Try again? (1-Yes, 0-No): ");
                    running = Integer.parseInt(sc.nextLine());
                    continue;
                }

                // CO-1: Operators and calculations
                tickets = 1;
                total = prices[choice - 1] * tickets;

                System.out.print("Would you like snacks? (Y/N): ");
                snackChoice = sc.nextLine().trim()
                        .toUpperCase().charAt(0);

                // CO-2: switch statement
                switch (snackChoice) {
                    case 'Y':
                        System.out.println("1. Popcorn - Rs.100");
                        System.out.println("2. Cool Drink - Rs.50");
                        System.out.print("Choose snack (1-2): ");

                        int snack = Integer.parseInt(sc.nextLine());

                        switch (snack) {
                            case 1:
                                total += 100;
                                bookingSummary.append(
                                        "Snack: Popcorn\n");
                                break;

                            case 2:
                                total += 50;
                                bookingSummary.append(
                                        "Snack: Cool Drink\n");
                                break;

                            default:
                                System.out.println(
                                        "Invalid snack choice. No snack added.");
                        }
                        break;

                    case 'N':
                        bookingSummary.append("Snack: None\n");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. No snack added.");
                        bookingSummary.append("Snack: None\n");
                }

                // CO-4: String methods
                String movieName = movies[choice - 1];
                System.out.println("\n--- BOOKING CONFIRMATION ---");
                System.out.println("Customer: " + customerName);
                System.out.println("Movie: " + movieName.toUpperCase());
                System.out.println("Seat: Row " + seatRow
                        + ", Column " + seatCol);
                System.out.println("Ticket Price: Rs."
                        + prices[choice - 1]);
                System.out.println("Total Amount: Rs." + total);

                // CO-6: StringBuffer methods
                bookingSummary.append("Customer: ")
                        .append(customerName)
                        .append("\nMovie: ")
                        .append(movieName)
                        .append("\nSeat: Row ")
                        .append(seatRow)
                        .append(", Column ")
                        .append(seatCol)
                        .append("\nTotal: Rs.")
                        .append(total)
                        .append("\n--------------------\n");

                System.out.print("\nBook another ticket? (1-Yes, 0-No): ");
                running = Integer.parseInt(sc.nextLine());

                // CO-2: if statement
                if (running != 1) {
                    break;
                }
            }

        } catch (NumberFormatException e) {
            // CO-5: Exception handling
            System.out.println("Error: Please enter numbers correctly.");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());

        } finally {
            // CO-5: Finally block
            System.out.println("\nThank you for using our system!");
            System.out.println("Available seats: " + countAvailableSeats());

            // CO-6: Display StringBuffer content
            System.out.println("\n--- BOOKING SUMMARY ---");
            System.out.println(bookingSummary);

            sc.close();
        }
    }
}

