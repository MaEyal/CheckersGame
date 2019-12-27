import java.util.Scanner;

public class Pawn {
	private boolean king = false;
	private int[] location = new int[]{0,0};
	private int team;
	
	public Pawn(int hor,int ver, int teamN){
		location[0] = hor;
		location[1] = ver;
		team = teamN;
	}
	
	public boolean isKing() {
		return king;
	}
	
	public int getTeam(){
		return team;
	}

	public void Move(int horFrom, int verFrom,int horTo, int verTo, int curTeam, Pawn[][] board) {
		/*This method is executing the movement of the pawn requested.
		 * Including moving the pawn and deleting enemy pawns in the process.
		 */
		if (board[horFrom][verFrom].isKing()) {
			board[horTo][verTo] = board[horFrom][verFrom];
			board[horFrom][verFrom] = null;
			while (verFrom != verTo) {
				if (verFrom > verTo)
					verFrom--;
				else
					verFrom++;
				if (horFrom > horTo)
					horFrom--;
				else
					horFrom++;
				if (board[horFrom][verFrom] != null) 
					if (board[horFrom][verFrom].getTeam() == 1) {
						CheckersGame.team1--;
						
					}
					else {
						CheckersGame.team2--;
						CheckersGame.nowPlay = 2;
				}
				if (board[horTo][verTo].getTeam() == 1)
					CheckersGame.nowPlay = 2;
				else
					CheckersGame.nowPlay = 1;
			}
			CheckKing(horTo,verTo,board);
			SecondJump(horFrom,verFrom,horTo,verTo,curTeam,board);
		}
		else
		{
	//normal step without a jump.
		if (horFrom+1 == horTo || horFrom-1 == horTo) {
			board[horTo][verTo] = board[horFrom][verFrom];
			board[horFrom][verFrom] = null;
			if (curTeam == 1)
				CheckersGame.nowPlay = 2;
			else
				CheckersGame.nowPlay = 1;
			CheckKing(horTo,verTo,board);
		}
	//Step with a jump.
		if (horFrom+2 == horTo || horFrom-2 == horTo) {
			board[horTo][verTo] = board[horFrom][verFrom];
			board[horFrom][verFrom] = null;
			if (horFrom > horTo)
				horFrom--;
			else
				horFrom++;
			if (verFrom > verTo)
				verFrom--;
			else
				verFrom++;
			board[horFrom][verFrom] = null;
			if (curTeam == 1) {
				CheckersGame.team2--;
				CheckersGame.nowPlay = 2;
			}
			else {
				CheckersGame.team1--;
				CheckersGame.nowPlay = 1;
			}
			SecondJump(horFrom,verFrom,horTo,verTo,curTeam,board);
			CheckKing(horTo,verTo,board);
			
			
		}
		}
	}
	
	public static void SecondJump(int horFrom, int verFrom, int horTo, int verTo, int curTeam, Pawn[][] board)
	{
		boolean contin = LegalMoves.Cont(horTo,verTo,board,curTeam);
			while (contin) {
				CheckersGame.PBoard(board);
				System.out.println("Would you like to make an additional jump? (Y/N)");
				String contin2;
				Scanner scan = new Scanner(System.in);
				contin2 = scan.next();
				while (!contin2.contentEquals("Y") && !contin2.contentEquals("y") && !contin2.contentEquals("N") && !contin2.contentEquals("n")) {
					System.out.println("Wrong input.");
					System.out.println("Would you like to make an additional jump? (Y/N)");
					contin2 = scan.next();
			}
				if (contin2.equals("Y") || contin2.contentEquals("y")) {
					verFrom = verTo;
					horFrom = horTo;
					System.out.println("please enter the coordinates of the destination:");
					horTo = scan.nextInt();
					verTo = scan.nextInt();
					boolean legal = LegalMoves.ContSecond(horFrom,verFrom,horTo,verTo,curTeam,board);
					if (legal) {
							board[horTo][verTo] = board[horFrom][verFrom];
							board[horFrom][verFrom] = null;
							CheckKing(horTo,verTo,board);
							if (horFrom > horTo)
								horFrom--;
							else
								horFrom++;
							if (verFrom > verTo)
								verFrom--;
							else
								verFrom++;
							board[horFrom][verFrom] = null;
							if (curTeam == 1) {
								CheckersGame.team2--;
							}
							else {
								CheckersGame.team1--;
							}
							horFrom = horTo;
							verFrom = verTo;
							contin = LegalMoves.Cont(horTo,verTo,board,curTeam);
					}
					else
						System.out.println("\n****Illegal move, please try again.****\n");
				}
				else
					contin = false;
						
		
				
			}
	}
			

	public static void CheckKing(int hor,int ver, Pawn[][] board) {
		if (hor == 1 && board[hor][ver].getTeam() == 1)
			board[hor][ver].king = true;
		if (hor == 8 && board[hor][ver].getTeam() == 2)
			board[hor][ver].king = true;
	}
}
		
	

