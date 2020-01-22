import java.util.Scanner;

public class CheckersGame {
	public static int team1 = 12, team2 = 12, nowPlay = 1;
	
	public static void main(String[]args) {
		Pawn[][] board = new Pawn[9][9];
		int verTo, verFrom, horFrom, horTo;
		Scanner scan = new Scanner(System.in);
		/*
		board[2][7]=new Pawn(1);
		board[3][6] = new Pawn(2);
		board[3][2]= new Pawn(2);
		board[7][8] = new Pawn(2);
		
		*/
		InitBoard(board);
		
		
		System.out.println("Instructions:");
		System.out.println("player 1 is the bottom player and player 2 is the top.");
		System.out.println("To move your pawns, please enter the coordinates of the pawn you want to move horizontal and vertical. for ex: 6 3");
		System.out.println("Then enter teh coordinates of the destination.");
		System.out.println("Remember: You can only take out your rival's pawns diagonally");
		System.out.println("If your pawn arrives to the rivals side it becomes a 'king' and can move as many steps\n as you want in a diagonal direction");
		System.out.println("Good luck");
		
		while(EndGame()) {
			PBoard(board);
			System.out.println("Player "+nowPlay+", please enter the coordinates of the pawn you'd like to move:");
			
			horFrom = (int)scan.next().charAt(0);
			horFrom  = ((horFrom-96>0) ? horFrom-96 : horFrom-64);
			verFrom = scan.nextInt();
			System.out.println("please enter the coordinates of the destination:");
			horTo = (int)scan.next().charAt(0);
			horTo = ((horTo-96>0) ? horTo-96 : horTo-64);
			verTo = scan.nextInt();
			boolean legal = LegalMoves.LegalMove(horFrom, verFrom, horTo, verTo, nowPlay, board, false);
			if (legal) {
				board[horFrom][verFrom].Move(horFrom, verFrom, horTo, verTo, nowPlay, board);
			}
			else
				System.out.println("\n****Illegal move, please try again.****\n");
			
					
		}
		if (team1 == 0)
			System.out.println("Player 2 Wins!");
		else
			System.out.println("Player 1 Wins!");
		
	}
	
	public static void PBoard(Pawn[][] board) {
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++) {
				if (i == 0)
					System.out.print(""+j+"   ");
				else if (j == 0)
					System.out.print(""+(char)(i+64)+" ");
				else {
					if (board[i][j] == null)
						System.out.print(" . |");
					else
						if (board[i][j].isKing())
							System.out.print((board[i][j].getTeam()==1?"W":"B")+"K |");
						else
						System.out.print(" "+(board[i][j].getTeam()==1?"W":"B")+" |");
				}
				if (j == 8)
					System.out.print("\n");
				
			}
		System.out.println("");
	}
	
	public static void InitBoard(Pawn[][] board) {
		int j=2, k;
		for (int i = 1; i<4; i++) {
			for (k=j; k<9; k+=2) {
				board[i][k] = new Pawn(2);
			}
			j=k%2+1;
		}
		j=1;
		for (int i = 6; i<9; i++) {
			for (k=j; k<9; k+=2) {
				board[i][k] = new Pawn(1);
			}
			j=k%2+1;
		}	
		
	}
	
	public static boolean EndGame() {
		if (team1<=0 || team2<=0)
			return false;
		else
			return true;
	}


}
