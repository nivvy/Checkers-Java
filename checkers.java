import java.util.Scanner;
import java.util.Random;

public class checkers {
	public static void main(String[] args) {
		Random r = new Random( 34246988);
		// print checkers rules & start game
		System.out.println("Let's play Checkers.");
		System.out.println("");
		System.out.println("Player 1, you are represented by the r checkers.");
		System.out.println("Player 2, you are represented by the b checkers.");
		System.out.println("You can move by one space diagonally at a time.");
		System.out.println("If your opponent's checker is in this space, you can jump by 2 spaces diagonally to capture it.");
		System.out.println("Once one of your checkers reaches the first row of the opposite side of the board, it will be kinged.");
		System.out.println("Only then can you move backwards.");
		System.out.println("");
		System.out.println("Beginning the game...");
		// create standard 8x8 checkers board
		String[][] board = new String[8][8];
		//int counter = 0; 
		for (int row = 0; row < board.length; row++) {
			int counter = 0; 
			for (int col = 0; col < board[row].length; col++) {
				// account for 2 empty rows in middle
				if (row == 4) {
					board[row][col] = "0";
				}
				else if (row == 3) {
					board[row][col] = "0";
				}
				// populate player 1
				else if (row == 7 && col%2 == 0) {
					board[row][col] = "r";
				}
				else if (row == 6 && col%2 != 0) {
					board[row][col] = "r";
				}
				else if (row == 5 && col%2 == 0) {
					board[row][col] = "r";
				}
				// populate player 2
				else if (row == 0 && col%2 != 0) {
					board[row][col] = "b";
				}
				else if (row == 1 && col%2 == 0) {
					board[row][col] = "b";
				}
				else if (row == 2 && col%2 != 0) {
					board[row][col] = "b";
				}
				else {
					board[row][col] = "0";
				}
			}
		}
		// print board
        printArr(board);
        // keep playing until there is a winner
        boolean winner = false;
        while (winner == false) {
        	// 	PLAYER 1 TURN
        	System.out.println("Player one's turn -");
        	// player 1 input for which checker to move
        	Scanner p1 = new Scanner(System.in);
        	System.out.println("Player 1, enter the row and column of the r checker you wish to move. ");
        	int p1row = p1.nextInt() - 1;
        	int p1col = p1.nextInt() - 1;
        	// check if space is valid
        	
        	while (board[p1row][p1col] != "r") {
        		System.out.println("This is not an r checker space. Try again.");
        		System.out.println("Player 1, enter the row and column of the r checker you wish to move. ");
                p1row = p1.nextInt() - 1;
            	p1col = p1.nextInt() - 1;
        	}
        	// player 1 input for space to move to
        	System.out.println("Player 1, enter the row and column of the space you wish to move your checker to. ");
        	int p1moverow = p1.nextInt() - 1;
        	int p1movecol = p1.nextInt() - 1;
        	// check if space is valid for jumps
        	boolean invalid = true;
        	while (invalid == true) {
        		// check if jump works
        		if (p1moverow == p1row - 2) {
        			// make sure space is diagonal
        			if (p1movecol == p1col+2 || p1movecol == p1col-2) {
        				// make sure space is empty
        				if (board[p1moverow][p1movecol] == "0") {
	        				// make sure there is something to jump over
	        				if (board[p1moverow+1][p1movecol-1] == "b" || board[p1moverow+1][p1movecol+1] == "b") {
	        					// replace checker that is captured w 0 & and break loop
	        				
	        		    		if (board[p1moverow+1][p1movecol-1] == "b") {
	        		    			
	        		    			board[p1moverow+1][p1movecol-1] = "0";
	        		    			invalid = false;
	        		    			continue;
	        		    		}
	        		    		else if (board[p1moverow+1][p1movecol+1] == "b") {
	        		 
	        		    			board[p1moverow+1][p1movecol+1] = "0";
	        		    			invalid = false;
	        		    			continue;
	        		    		}
	        		    	}
	        			}
	        		}
        		}
        		// check if space is valid for standard horizontal move
        		else if (p1moverow == p1row-1) {
        			if (p1movecol == p1col+1 || p1movecol == p1col - 1) {
        				if (board[p1moverow][p1movecol] == "0") {
        					invalid = false;
        					continue; 
        				}
        			}
        		}
        		System.out.println("Invalid move.");
        		System.out.println("Player 1, enter the row and column of the space you wish to move to. ");
        		p1moverow = p1.nextInt() - 1;
            	p1movecol = p1.nextInt() - 1;
        	}
        	// remove r checker from old space
        	board[p1row][p1col] = "0";
        	// populate new space w r checker
            board[p1moverow][p1movecol] = "r";
            // show board
            printArr(board);
            // check for winner
            int rpieces = 0;
            int bpieces = 0;
            for (int row = 0; row < board.length; row++) {
    			for (int col = 0; col < board[row].length; col++) {
    				if (board[row][col] == "r") {
    					rpieces++;
    				}
    				else if (board[row][col] == "R") {
    					rpieces++;
    				}
    				else if (board[row][col] == "b") {
    					bpieces++;
    				}
    				else if (board[row][col] == "B") {
    					bpieces++;
    				}
    			}
            }
            if (rpieces>0 && bpieces == 0) {
            	System.out.println("All of Player 2's checkers have been captured.");
            	System.out.println("Player 1 wins!");
            	winner = true;
            	continue;
            }
            if (bpieces>0 && rpieces == 0) {
            	System.out.println("All of Player 1's checkers have been captured.");
            	System.out.println("Player 2 wins!");
            	winner = true;
            	continue;
            }
            // PLAYER 2 turn
        	System.out.println("Player two's turn -");
        	// player 1 input for which checker to move
        	//Scanner p1 = new Scanner(System.in);
        	System.out.println("Player 2, enter the row and column of the b checker you wish to move. ");
        	int p2row = p1.nextInt() - 1;
        	int p2col = p1.nextInt() - 1;
        	// check if space is valid
        	while (board[p2row][p2col] != "b") {
        		System.out.println("This is not a b checker space. Try again.");
        		System.out.println("Player 2, enter the row and column of the r checker you wish to move. ");
                p2row = p1.nextInt() - 1;
            	p2col = p1.nextInt() - 1;
        	}
        	// player 2 input for space to move to
        	System.out.println("Player 2, enter the row and column of the space you wish to move your checker to. ");
        	int p2moverow = p1.nextInt() - 1;
        	int p2movecol = p1.nextInt() - 1;
        	// check if space is valid for jumps
        	invalid = true;
        	while (invalid == true) {
        		// check if jump works
        		if (p2moverow == p2row + 2) {
        			// make sure space is diagonal
        			if (p2movecol == p2col+2 || p2movecol == p2col-2) {
        				// make sure space is empty
        				if (board[p2moverow][p2movecol] == "0") {
	        				// make sure there is something to jump over
	        				if (board[p2row+1][p2movecol-1] == "r" || board[p2row+1][p2movecol+1] == "r") {
	        					// replace checker that is captured w 0 & and break loop
	        		    		if (board[p2row+1][p2movecol-1] == "r") {
	        		    			board[p2row+1][p2movecol-1] = "0";
	        		    			invalid = false;
	        		    			continue;
	        		    		}
	        		    		else if (board[p2row+1][p2movecol+1] == "b") {
	        		    			board[p2row+1][p2movecol+1] = "0";
	        		    			invalid = false;
	        		    			continue;
	        		    		}
	        		    	}
	        			}
	        		}
        		}
        		// check if space is valid for standard horizontal move
        		else if (p2moverow == p2row+1) {
        			if (p2movecol == p2col+1 || p2movecol == p2col - 1) {
        				if (board[p2moverow][p2movecol] == "0") {
        					invalid = false;
        					continue; 
        				}
        			}
        		}
        		System.out.println("Invalid move.");
        		System.out.println("Player 2, enter the row and column of the space you wish to move to. ");
        		p2moverow = p1.nextInt() - 1;
            	p2movecol = p1.nextInt() - 1;
        	}
        	// remove b checker from old space
        	board[p2row][p2col] = "0";
        	// populate new space w b checker
            board[p2moverow][p2movecol] = "b";
            // show board
            printArr(board);
            // check for winner
            rpieces = 0;
            bpieces = 0;
            for (int row = 0; row < board.length; row++) {
    			for (int col = 0; col < board[row].length; col++) {
    				if (board[row][col] == "r") {
    					rpieces++;
    				}
    				else if (board[row][col] == "R") {
    					rpieces++;
    				}
    				else if (board[row][col] == "b") {
    					bpieces++;
    				}
    				else if (board[row][col] == "B") {
    					bpieces++;
    				}
    			}
            }
            if (rpieces>0 && bpieces == 0) {
            	System.out.println("All of Player 2's checkers have been captured.");
            	System.out.println("Player 1 wins!");
            	winner = true;
            	continue;
            }
            if (bpieces>0 && rpieces == 0) {
            	System.out.println("All of Player 1's checkers have been captured.");
            	System.out.println("Player 2 wins!");
            	winner = true;
            	continue;
            }
            
        	}
        }
        	
        
     
	

	
	// method to print board
	public static void printArr(String[][] arr) {
		for (int row = 0; row < arr.length; row++) {
			for (int col = 0; col < arr[row].length; col++) {
				System.out.print(arr[row][col] + " ");
			}
			System.out.println(" ");
		}
	}
	
}
