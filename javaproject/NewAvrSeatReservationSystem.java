import java.util.Scanner;

public class NewAvrSeatReservationSystem {

	  public static void main(String[] args) {
	        Scanner input = new Scanner(System.in);
	        
	        // 5 rows (A-E) and 8 columns (1-8)
	        char[][] seats = {
	            {'O', 'O', 'X', 'X', 'O', 'O', 'O', 'O'},
	            {'X', 'X', 'X', 'O', 'O', 'O', 'X', 'O'},
	            {'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
	            {'X', 'O', 'O', 'X', 'X', 'X', 'O', 'O'},
	            {'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O'}
	        };
	        
	        boolean[] hasReservedSeat = {false};
	        int[] reservedRow = {-1};
	        int[] reservedColumn = {-1};

	        int menuChoice;

	        // Do-while loop for the main menu system
	        do {
	            System.out.println("===== AVR SEAT RESERVATION =====");
	            System.out.println("1. Reserve Seat");
	            System.out.println("2. Cancel Reservation");
	            System.out.println("3. Display Seat Map");
	            System.out.println("4. Exit");
	            System.out.print("Enter choice: ");
	            
	            menuChoice = input.nextInt();

	            // Switch statement to handle user menu choice
	            switch (menuChoice) {
	                case 1:	                   
	                    ReserveSeat(hasReservedSeat, seats, input, reservedRow, reservedColumn);
	                    break;

	                case 2:	                   
	                    CancelReservation(hasReservedSeat, seats, input, reservedRow, reservedColumn);
	                    break;

	                case 3:
	                    DisplaySeatMap(seats);
	                    DisplayOccupancy(seats);
	                    break;

	                case 4:
	                    System.out.println("Thank you for using the system.");
	                    break;

	                default:
	                    System.out.println("Invalid choice.\n");
	                    break;
	            }

	        } while (menuChoice != 4);

	        input.close();
	    } 
	  
	//Use of parameters since we are using local variables in main and not global variables
	  public static void ReserveSeat(boolean[] hasReservedSeat, char[][] seats, Scanner input, int[] reservedRow, int[] reservedColumn) {   
	  
		  if (hasReservedSeat[0]) {
            System.out.println("You have already reserved a seat.\n");
        } else {
            DisplaySeatMap(seats);

            int[] position = GetSeatSelection(input, seats);

            if (position != null) {
                int row = position[0];
                int column = position[1];

                if (seats[row][column] == 'X') {
                    System.out.println("Seat is already taken!\n");
                } 
                else {
                    seats[row][column] = 'X';

	                    hasReservedSeat[0] = true;
	                    reservedRow[0] = row;
	                    reservedColumn[0] = column;

                    System.out.println("Seat reserved successfully!\n");
               }
	        }                                
        }   
   }    
	  //the int[] here means it must return an integer and 1d array on top of that, this function is used to get rowIndex and columnIndex
	  public static int[] GetSeatSelection(Scanner input, char[][] seats) {

	        System.out.print("Enter Row (A-E): ");
	        char row = Character.toUpperCase(input.next().charAt(0)); //.toUpperCase: Turns into UpperCase from a to A, .charAt(0): Takes the first character

	        int rowIndex = row - 'A';

	        if (rowIndex < 0 || rowIndex >= seats.length) {
	            System.out.println("Invalid letter input.\n");
	            return null;
	        }

	        System.out.print("Enter Column (1-8): ");

	        while (!input.hasNextInt()) {
	            System.out.print("Invalid input. Enter Column (1-8): ");
	            input.next();
	        }

	        int columnIndex = input.nextInt() - 1; // -1 so that it fits the array since array starts with 0

	        if (columnIndex < 0 || columnIndex >= seats[0].length) {
	            System.out.println("Invalid seat selection.\n");
	            return null;
	        }

	        return new int[] {rowIndex, columnIndex}; //returns 1d array
	    }
	  
	  public static void CancelReservation(boolean[] hasReservedSeat, char[][] seats, Scanner input, int[] reservedRow, int[] reservedColumn){
		  if (!hasReservedSeat[0]) {
            System.out.println("You have no reservation.\n");
        } else {
	            seats[reservedRow[0]][reservedColumn[0]] = 'O';

	            char rowLetter = (char) ('A' + reservedRow[0]); //A is being treated as 0 here (essentially A is 65 or smthg but this is 0)
	            int columnNumber = reservedColumn[0] + 1;  //Since array is 0-based indexing, +1 for showing correct output 

            System.out.println( "Reservation for seat " + rowLetter + columnNumber + " has been cancelled.\n");

	            hasReservedSeat[0] = false; //Reset Initial Values
	            reservedRow[0] = -1; //-1 since 0 is valid; 0 being A or 1
	            reservedColumn[0] = -1; //-1 since 0 is valid; 0 being 1
        }
	  }
	  
	  public static void DisplaySeatMap(char[][] seats) {
	        System.out.println("\n===== AVR SEAT MAP =====");
	        System.out.print("  ");
	        for (int col = 1; col <= 8; col++) {
	            System.out.print(col + " ");
	        }
	        System.out.println();

	        for (int i = 0; i < seats.length; i++) {  // for loop
	            char rowLabel = (char) ('A' + i);
	            System.out.print(rowLabel + " ");
	            for (int j = 0; j < seats[i].length; j++) { // for nested loop
	                System.out.print(seats[i][j] + " ");
	            }
	            System.out.println();
				//Yess
	        }
	        System.out.println();
	    }
	  
	  public static void DisplayOccupancy(char[][] seats) 
	   {

	        int totalSeats = seats.length * seats[0].length;
	        int occupiedSeats = 0;

	        for (int i = 0; i < seats.length; i++) {
	            for (int j = 0; j < seats[i].length; j++) {
	                if (seats[i][j] == 'X') {
	                    occupiedSeats++;
	                }
	            }
	        }

	        int availableSeats = totalSeats - occupiedSeats;
	        double occupancyRate = (occupiedSeats / (double) totalSeats) * 100;

	        System.out.printf("Available: %d of %d seats%n", availableSeats, totalSeats);

	        System.out.printf("Occupancy: %.2f%%%n%n",occupancyRate);
	    }
		
}  
