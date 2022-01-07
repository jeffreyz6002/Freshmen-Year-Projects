import java.util.*;
public class Connect4 {
	public static String [][] create(String [][] board) { //Method to create board
		
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c <board[r].length; c++) {
				if(r == board.length - 1) 
					board[r][c] = ".";
				else {
					if(c % 2 == 0) 
						board[r][c] = "|";
					else 
						board[r][c] = " ";
					}
			}
		}
		return board;
	}	
	
	public static boolean full(String [][] board) { //Check if the board if full
		boolean full = true;
		for(int r = 0; r < board.length - 1; r++) {
			for(int c = 1; c < board[r].length; c+=2) {
				if(board[r][c].equals(" ")) {
					full = false;
				}
			}
		}
		return full;
	}
	
	public static void add(String[][] board,int pos, int count){ //Input disk
		int col = 1 + (pos * 2);
		String disk = "Y";
		if(count % 2 == 0) {
			disk = "R";
		}
		for(int r = 5; r >= 0; r--) {
			if(board[r][col] == " ") {
				board[r][col] = disk;
				break;
			}
		}
	}
	
	public static void display(String[][] board) { //Display board
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[r].length; c++) {
				System.out.print(board[r][c]);
			}
			System.out.println();
		}
	}
	
	public static boolean win(String [][] board) { //Check for win conditions 
		String main = "";
		boolean won = false;
		for(int r = 0; r < board.length - 1; r++) { 
			for(int c = 1; c < board[r].length - 6; c+=2) { //Check for horizontal wins from left 
				main = board[r][c];
				if((main.equals(board[r][c+2]) && (main.equals(board[r][c+4]))  && main.equals(board[r][c+6])) && (board[r][c].equals("R") || board[r][c].equals("Y"))) {
					won = true;
				}
			}
			for(int c = board[r].length - 1; c > 6; c-=2) { //Check for horizontal wins from right
				main = board[r][c];
				if((main.equals(board[r][c-2]) && (main.equals(board[r][c-4]))  && main.equals(board[r][c-6])) && (board[r][c].equals("R") || board[r][c].equals("Y")))  {
					won = true;
				}	
			}
		}
		for(int r = board.length - 1; r >= 3; r--) { // Check for vertical wins 
			for(int c = 1; c < board[r].length; c+=2) {
				main = board[r][c];
				if((main.equals(board[r-1][c]) && (main.equals(board[r-2][c])) && main.equals(board[r-3][c])) && (board[r][c].equals("R") || board[r][c].equals("Y")))  {
					won = true;
				}
			}
		}
		for(int r = board.length - 1; r >= 3; r--) {
			for(int c = 1; c < 8; c+= 2) { //Check for positive slope diagonal wins 
				main = board[r][c];
				if((main.equals(board[r-1][c+2]) && (main.equals(board[r-2][c+4])) && main.equals(board[r-3][c+6])) && (board[r][c].equals("R") || board[r][c].equals("Y")))  {
					won = true;
				}
			}
			for(int c = board[r].length - 2; c > 6; c-=2) { //Check for negative slope diagonal wins 
				main = board[r][c];
				if((main.equals(board[r-1][c-2]) && (main.equals(board[r-2][c-4])) && main.equals(board[r-3][c-6])) && (board[r][c].equals("R") || board[r][c].equals("Y")))  {
					won = true;
				}
			}
		}
		return won;
	}
	}
