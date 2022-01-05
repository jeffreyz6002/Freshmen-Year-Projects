import java.util.*;
public class testConnect4 {
public static void main (String [] args) throws Exception{
	
	Scanner stdin = new Scanner (System.in);

	String [][] board = new String[7][15]; 
	int counter = 0;
	int position;
	
	System.out.println("======Welcome to Connect 4=====");
	
	board = Connect4.create(board); //Create the board
	
	while( !(Connect4.full(board) || Connect4.win(board))) { //Loop till a player wins 
		try {
		if(counter % 2 == 0) { // Player one moves (Red)
			System.out.print("Drop a red disk at column (0-6): ");
			position = stdin.nextInt();
			Connect4.add(board,position,counter); //Disk input 
			Connect4.display(board); //Display board
			counter++;
		}
		else { // Player two moves (Yellow)
			System.out.print("Drop a yellow disk at column (0-6): ");
			position = stdin.nextInt();
			Connect4.add(board,position,counter); //Disk input 
			Connect4.display(board); //Display board
			counter++;
		}
		
		}
		catch(InputMismatchException ex) { //Error proof for no Strings
			System.out.print("ERROR! You have not entered a number.\n");
			stdin.nextLine();
		}
		catch(ArrayIndexOutOfBoundsException ex) { //Error proof for no numbers outside of board range 
			System.out.print("ERROR! Input is not in given range.\n");
			stdin.nextLine();
		}
		
	}
	
	if(Connect4.full(board)) { // Tie situation
		System.out.print("The game is a tie.");
	} else {
		System.out.print("Congratulations, the "); // Congratulations to winner 
		if(counter % 2 == 0) {
			System.out.print("yellow");
		}
		else {
			System.out.print("red");
		}
	System.out.print(" player won!");
}
}
}