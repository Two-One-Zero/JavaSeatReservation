package avrSeatReservationPackage;
import java.util.Scanner;

public class AvrSeatReservation {
	//finalfinal
	public static int menuChoice;
	public static char rowChoice;
	public static int columnChoice;
	public static boolean hasReservedSeat = false;
	
	public static int reservedRowIndex;
	public static int reservedColumnIndex;
	
	public static Scanner  input = new Scanner (System.in);
	public static char [][] seats = {{'O', 'O', 'X', 'X', 'O', 'O','O','O'},
		                             {'X', 'X', 'X', 'O', 'O', 'O', 'X','O'},
		                             {'O', 'O', 'O', 'O', 'X', 'O','O','O'},
		                             {'X', 'O', 'O', 'X', 'X', 'X','O','O'},
		                             {'O', 'O', 'X', 'O', 'O', 'X','O','O'}};
	                                                 
	 public static void main(String[] args)
	    {
		 
		Menu();

			
		 	
	    }

	 public static void Menu()
	 {
		 
		 do {
	            
		 System.out.println("==== AVR SEAT RESERVATION ====");
		 System.out.println("1. Reserve Seat");
		 System.out.println("2. Cancel Reservation");
		 System.out.println("3. Display Seat Map");
		 System.out.println("4. Exit");
		 
		 
		
		 System.out.print("Enter Choice: ");
		 
		
		 
		 menuChoice = input.nextInt();
		
		 
		 switch (menuChoice)
		 {
		     case 1:
		    	 ReserveSeat();
		    	 break;
		     case 2:
		    	 CancelReservation();	    	 
		    	 break;
		     case 3:
		    	 DisplaySeatMap();
		    	 break;
		     case 4:
		    	 System.out.println("Thank you for using the system.");
		    	 input.close();
		    	 break;
		    	 
		     default:
		    	 System.out.print("Invalid input. Enter number (1-4): ");
		    	 break;	 
		 }
		 
	 } while (menuChoice != 4);
		 
		 input.close();
	 }
	 
	 public static void ReserveSeat()
	 {
		 
		 if (hasReservedSeat)
			{
				System.out.println("You have already reserved a seat.");
				return;
			}
		 
		 System.out.println("==== AVR SEAT MAP ====");
		 
		 char row = 'A';
		 
		
		 
		 System.out.printf("%-4s", "");

		 for (int num = 1; num < seats[0].length + 1; num ++)
		 {
			 System.out.printf("%-2d", num);
		 
		 }
		 System.out.println();

		 

		 for (int i = 0; i < seats.length; i++)
		 {
			 System.out.printf("%-4c", row);
		 
			 for (int j = 0; j < seats[i].length; j++)
			 {
				 System.out.printf("%-2c", seats[i][j]);
			 }

			 System.out.println();
			 row++;
		 }
		 
		 
		 
		 System.out.println();
		 
		 int[] seatPos = GetSeatSelectionHelper();

			if (seatPos == null)
			{
				return;
			}
		 
		 int rowIndex = rowChoice - 'A';
		 int columnIndex = columnChoice - 1 ;

		 if (seats[rowIndex][columnIndex] == 'O')
		       {
			    	seats[rowIndex][columnIndex] = 'X';
			    	hasReservedSeat = true;
			    	
			    	reservedRowIndex = rowIndex;     
					reservedColumnIndex = columnIndex; 
					
			    	System.out.println("Seat reserved successfully!");
			   } 
		 else {
				   
			    System.out.println("Seat is already taken!");
			   }
		 	 
		 System.out.println();
		 
	 			}
	 
	 public static int[] GetSeatSelectionHelper()
	 {
		 System.out.print("Enter Row (A-E): ");
			char rawInput = input.next().charAt(0);

			switch (rawInput)
			{
			    case 'a': case 'A':
			    	rowChoice = 'A';
			    	break;
			    case 'b': case 'B':
			    	rowChoice = 'B';
			    	break;
			    case 'c': case 'C':
			    	rowChoice = 'C';
			    	break;
			    case 'd': case 'D':
			    	rowChoice = 'D';
			    	break;
			    case 'e': case 'E':
			    	rowChoice = 'E';
			    	break;
			    default:
			    	System.out.println("Invalid letter input.");
			    	return null;
			}

			System.out.print("Enter Column (1-8): ");

			while (!input.hasNextInt())
			{
				System.out.print("Invalid input. Enter Column (1-8): ");
				input.next();
			}

			columnChoice = input.nextInt();

			int rowIndex = rowChoice - 'A';
			int columnIndex = columnChoice - 1;

			if (columnIndex < 0 || columnIndex >= seats[0].length)
			{
				System.out.println("Invalid seat selection.");
				return null;
			}

			return new int[] {rowIndex, columnIndex};
	 }
	 
	 public static void CancelReservation()
	 {
	 	if (!hasReservedSeat)
	 	{
	 		System.out.println("You have no reservation.");
	 		return;
	 	}

	 	seats[reservedRowIndex][reservedColumnIndex] = 'O';

	 	char rowLetter = (char) (reservedRowIndex + 'A');
	 	int columnNumber = reservedColumnIndex + 1;

	 	System.out.println("Reservation for seat " + rowLetter + columnNumber + " has been cancelled.");

	 	hasReservedSeat = false;
	 	reservedRowIndex = -1;
	 	reservedColumnIndex = -1;
	 	
	 	System.out.println();
	 }
	 
	 public static void DisplaySeatMap()
		{
			System.out.println("==== AVR SEAT MAP ====");

			char row = 'A';

			System.out.printf("%-4s", "");

			for (int num = 1; num < seats[0].length + 1; num++)
			{
				System.out.printf("%-2d", num);
			}
			System.out.println();

			for (int i = 0; i < seats.length; i++)
			{
				System.out.printf("%-4c", row);

				for (int j = 0; j < seats[i].length; j++)
				{
					System.out.printf("%-2c", seats[i][j]);
				}

				System.out.println();
				row++;
			}
			
			System.out.println();
			
			int totalSeats = seats.length * seats[0].length;
			int occupiedSeats = 0;

			for (int i = 0; i < seats.length; i++)
			{
				for (int j = 0; j < seats[i].length; j++)
				{
					if (seats[i][j] == 'X')
					{
						occupiedSeats++;
					}
				}
			}

			int availableSeats = totalSeats - occupiedSeats;
			double occupancyRate = (occupiedSeats / (double) totalSeats) * 100;

			System.out.printf("Available: %d of %d seats", availableSeats, totalSeats);
			System.out.println();
			System.out.printf("Occupancy: %.2f%%", occupancyRate);
			System.out.println();
			
			System.out.println();

		
		}
	 
}
