import java.util.Scanner;

class Cinema {
    String movie;
    int ticketPrice;
    String[] seats = new String[10];

    Cinema(String movie, int ticketPrice) {
        this.movie = movie;
        this.ticketPrice = ticketPrice;

        for (int i = 0; i < seats.length; i++) {
            seats[i] = "Available";
        }
    }

    void displaySeats() {
        System.out.println("Seat Status:");

        for (int i = 0; i < seats.length; i++) {
            System.out.println("Seat " + (i + 1) + " : " + seats[i]);
        }
    }

    boolean bookSeat(int seatNumber) {

        if (seatNumber < 1 || seatNumber > 10) {
            System.out.println("Invalid seat number!");
            return false;
        }

        if (seats[seatNumber - 1].equals("Booked")) {
            System.out.println("Sorry! This seat is already booked.");
            return false;
        }

        seats[seatNumber - 1] = "Booked";
        return true;
    }
}

public class CinemaSeatBookingSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("================================");
        System.out.println("     CINEMA SEAT BOOKING SYSTEM");
        System.out.println("================================");

        System.out.print("Enter your name: ");
        String userName = sc.nextLine();
        Cinema movie1 = new Cinema("Avengers", 200);
        Cinema movie2 = new Cinema("Avatar", 250);
        Cinema movie3 = new Cinema("Leo", 180);

        System.out.println("Welcome, " + userName + "!");

        System.out.println("Available Movies:");
        System.out.println("1. Avengers - Rs.200");
        System.out.println("2. Avatar   - Rs.250");
        System.out.println("3. Leo      - Rs.180");

        System.out.print("Enter movie choice: ");
        int choice = sc.nextInt();

        Cinema selectedMovie;

        if (choice == 1) {
            selectedMovie = movie1;
        } else if (choice == 2) {
            selectedMovie = movie2;
        } else if (choice == 3) {
            selectedMovie = movie3;
        } else {
            System.out.println("Invalid movie choice!");
            sc.close();
            return;
        }

        System.out.println("You selected: " + selectedMovie.movie);
        selectedMovie.displaySeats();

        System.out.print("Enter number of tickets: ");
        int tickets = sc.nextInt();

        if (tickets < 1 || tickets > 10) {
            System.out.println("Invalid number of tickets!");
            sc.close();
            return;
        }

        int ticketTotal = 0;
        String bookedSeats = "";
        for (int i = 1; i <= tickets; i++) {

            System.out.print("Enter seat number for ticket " + i + ": ");
            int seatNumber = sc.nextInt();

            if (selectedMovie.bookSeat(seatNumber)) {

                ticketTotal = ticketTotal + selectedMovie.ticketPrice;

                bookedSeats = bookedSeats + seatNumber + " ";
            } else {
                System.out.println("Please choose another seat.");
                i--;
            }
        }
        System.out.print("Do you want snacks? (yes/no): ");
        String snackChoice = sc.next();

        int snackCost = 0;
        String snackName = "No Snacks";

        if (snackChoice.equalsIgnoreCase("yes")) {

            System.out.println("Snack Menu:");
            System.out.println("1. Popcorn - Rs.100");
            System.out.println("2. Coke    - Rs.60");
            System.out.println("3. Combo   - Rs.150");

            System.out.print("Enter snack choice: ");
            int snack = sc.nextInt();

            if (snack == 1) {
                snackName = "Popcorn";
                snackCost = 100;
            } else if (snack == 2) {
                snackName = "Coke";
                snackCost = 60;
            } else if (snack == 3) {
                snackName = "Combo";
                snackCost = 150;
            } else {
                System.out.println("Invalid snack choice. No snack added.");
            }
        }
        int totalAmount = ticketTotal + snackCost;
        System.out.println("================================");
        System.out.println("        BOOKING DETAILS");
        System.out.println("================================");

        System.out.println("Customer Name : " + userName);
        System.out.println("Movie         : " + selectedMovie.movie);
        System.out.println("Tickets       : " + tickets);
        System.out.println("Seat Numbers  : " + bookedSeats);
        System.out.println("Ticket Cost   : Rs." + ticketTotal);
        System.out.println("Snacks        : " + snackName);
        System.out.println("Snack Cost    : Rs." + snackCost);
        System.out.println("--------------------------------");
        System.out.println("TOTAL AMOUNT  : Rs." + totalAmount);
        System.out.println("================================");

        System.out.println("Booking successful!");
        System.out.println("Thank you, " + userName + "!");

        sc.close();
    }
}