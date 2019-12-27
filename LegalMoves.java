

public class LegalMoves {

	public static boolean LegalMove(int horFrom, int verFrom, int horTo, int verTo, int curTeam ,Pawn[][] board) {
		//Checking the bounds.
		if ((verTo + horTo)%2 == 0 || verFrom<1 || verFrom>8 || verTo<1 || verTo>8 || horFrom<1 || horFrom>8 || horTo<1 || horTo>8) {
			return false;
		}
		
		//Checking the pawn at the source and the destination point.
		if (board[horFrom][verFrom] == null || board[horFrom][verFrom].getTeam() != curTeam || board[horTo][verTo] != null) {
			return false;
		}
		
		
		if (board[horFrom][verFrom].isKing()) {
			
			//Checks legality of moves if the pawn is a king.
			
				//The case of a normal step and a jump.
				int enemyPawn = 1;
				if (Math.abs(horFrom-horTo) == Math.abs(verFrom-verTo))
					while (horFrom != horTo && verFrom != verTo && enemyPawn >= 0) {
						if (horFrom > horTo)
							horFrom--;
						else
							horFrom++;
						if (verFrom > verTo)
							verFrom--;
						else
							verFrom++;
						if (enemyPawn == 0 &&  horFrom != horTo) {
							
							return false;
						}
						if (board[horFrom][verFrom] != null)
							if (board[horFrom][verFrom].getTeam() != curTeam) {
								enemyPawn-=1;
								if (board[horFrom][verFrom].getTeam() == 2)
									CheckersGame.team2--;
								else
									CheckersGame.team1--;
								board[horFrom][verFrom] = null;
							}
							else {
								return false;
							}
					}
				if (enemyPawn >= 0)
					return true;
				else {
					return false;
				}
			
		}
		else {
			//Checks legality of moves if the pawn is not a king.
			
		switch(curTeam){
		case 1:
			//The case of a normal step.

			if (horFrom-1 == horTo) 
				if (verFrom-1 == verTo || verFrom+1 == verTo) 
					return true;

			//In case of a jump.
			if (horFrom-2 == horTo) {
				if (verFrom-2 == verTo && board[horFrom-1][verFrom-1] != null && board[horFrom-1][verFrom-1].getTeam() == 2)
					return true;
				if (verFrom+2 == verTo && board[horFrom-1][verFrom+1] != null && board[horFrom-1][verFrom+1].getTeam() == 2)
					return true;
			}
			return false;
		case 2:
			//The case of a normal step.
			if (horFrom+1 == horTo)
				if (verFrom-1 == verTo || verFrom+1 == verTo) 
					return true;
				
			//In case of a jump.
			if (horFrom+2 == horTo) {
				if (verFrom-2 == verTo && board[horFrom+1][verFrom-1] != null && board[horFrom+1][verFrom-1].getTeam() == 1)
					return true;
				if (verFrom+2 == verTo && board[horFrom+1][verFrom+1] != null && board[horFrom+1][verFrom+1].getTeam() == 1)
					return true;
				}
		
		}
		return false;
	}
	}

	//Checking if after a jump the player can continue for an additional jump.
	public static boolean Cont(int horFrom,int verFrom,Pawn[][] board, int curTeam) {
		if (horFrom - 2 < 1 && verFrom - 2 < 1) {
			if (board[horFrom+1][verFrom+1] != null && board[horFrom+2][verFrom+2] == null && board[horFrom+1][verFrom+1].getTeam() != curTeam)
				return true;
			else
				return false;
			}
		if (horFrom - 2 < 1 && verFrom - 2 > 0 && verFrom + 2 < 9) {
			if (board[horFrom+1][verFrom+1] != null && board[horFrom+2][verFrom+2] == null && board[horFrom+1][verFrom+1].getTeam() != curTeam)
				return true;
			if (board[horFrom+1][verFrom-1] != null && board[horFrom+2][verFrom-2] == null && board[horFrom+1][verFrom-1].getTeam() != curTeam)
				return true;
			return false;
		}
		if (horFrom - 2 <1 && verFrom + 2 > 8) {
			if (board[horFrom+1][verFrom-1] != null && board[horFrom+2][verFrom-2] == null && board[horFrom+1][verFrom-1].getTeam() != curTeam)
				return true;
			else
				return false;
		}
		if (horFrom - 2 > 0 && horFrom + 2 < 9 && verFrom - 2 < 1) {
			if (board[horFrom-1][verFrom+1] != null && board[horFrom-2][verFrom+2] == null && board[horFrom-1][verFrom+1].getTeam() != curTeam)
				return true;
			if (board[horFrom+1][verFrom+1] != null && board[horFrom+2][verFrom+2] == null && board[horFrom+1][verFrom+1].getTeam() != curTeam)
				return true;
			return false;
		}
		if (horFrom - 2 > 0 && horFrom + 2 < 9 && verFrom - 2 > 0 && verFrom + 2 < 9) {
			if (board[horFrom-1][verFrom+1] != null && board[horFrom-2][verFrom+2] == null && board[horFrom-1][verFrom+1].getTeam() != curTeam)
				return true;
			if (board[horFrom+1][verFrom+1] != null && board[horFrom+2][verFrom+2] == null && board[horFrom+1][verFrom+1].getTeam() != curTeam)
				return true;
			if (board[horFrom+1][verFrom-1] != null && board[horFrom+2][verFrom-2] == null && board[horFrom+1][verFrom-1].getTeam() != curTeam)
				return true;
			if (board[horFrom-1][verFrom-1] != null && board[horFrom-2][verFrom-2] == null && board[horFrom-1][verFrom-1].getTeam() != curTeam)
				return true;
			return false;
		}
		if (horFrom - 2 > 0 && horFrom + 2 < 9 && verFrom + 2 > 8) {
			if (board[horFrom-1][verFrom-1] != null && board[horFrom-2][verFrom-2] == null && board[horFrom-1][verFrom-1].getTeam() != curTeam)
				return true;
			if (board[horFrom+1][verFrom-1] != null && board[horFrom+2][verFrom-2] == null && board[horFrom+1][verFrom-1].getTeam() != curTeam)
				return true;
			return false;
		}
		if (horFrom + 2 > 8 && verFrom - 2 < 1) {
			if (board[horFrom-1][verFrom+1] != null && board[horFrom-2][verFrom+2] == null && board[horFrom-1][verFrom+1].getTeam() != curTeam)
				return true;
			else
				return false;
		}
		if (horFrom + 2 > 8 && verFrom - 2 > 0 && verFrom + 2 < 9) {
			if (board[horFrom-1][verFrom+1] != null && board[horFrom-2][verFrom+2] == null && board[horFrom-1][verFrom+1].getTeam() != curTeam)
				return true;
			if (board[horFrom-1][verFrom-1] != null && board[horFrom-2][verFrom-2] == null && board[horFrom-1][verFrom-1].getTeam() != curTeam)
				return true;
			return false;
		}
		if (horFrom + 2 > 8 && verFrom + 2 > 8) {
				if (board[horFrom-1][verFrom-1] != null && board[horFrom-2][verFrom-2] == null && board[horFrom-1][verFrom-1].getTeam() != curTeam)
					return true;
				else
					return false;
		}
			
		return false;	
	}
	
	//Checking if the additional jump is legal.
	public static boolean ContSecond(int horFrom, int verFrom, int horTo, int verTo, int curTeam, Pawn[][] board) {
		if ((verTo + horTo)%2 == 0 || verFrom<1 || verFrom>8 || verTo<1 || verTo>8 || horFrom<1 || horFrom>8 || horTo<1 || horTo>8) {
			System.out.println("Illegal move, please try again.");
			return false;
		}
		
		//Checking the pawn at the source and the destination point.
		if (board[horFrom][verFrom] == null || board[horFrom][verFrom].getTeam() != curTeam || board[horTo][verTo] != null) {
			System.out.println("Illegal move, please try again.");
			return false;
		}
		


			if (horFrom-2 == horTo) {
				if (verFrom-2 == verTo && board[horFrom-1][verFrom-1] != null && board[horFrom-1][verFrom-1].getTeam() != curTeam)
					return true;
				if (verFrom+2 == verTo && board[horFrom-1][verFrom+1] != null && board[horFrom-1][verFrom+1].getTeam() != curTeam)
					return true;
			}	

			if (horFrom+2 == horTo) {
				if (verFrom-2 == verTo && board[horFrom+1][verFrom-1] != null && board[horFrom+1][verFrom-1].getTeam() != curTeam)
					return true;
				if (verFrom+2 == verTo && board[horFrom+1][verFrom+1] != null && board[horFrom+1][verFrom+1].getTeam() != curTeam)
					return true;
				}
			System.out.println("Illegal move, please try again.");
			return false;
	}
}



