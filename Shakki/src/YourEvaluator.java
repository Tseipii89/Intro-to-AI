public class YourEvaluator extends Evaluator {
    public Object[][] kingEvalWhite;
    public Object[][] evalQueen;
    public Object[][] rookEvalWhite;
    public Object[][] bishopEvalWhite;
    public Object[][] knightEval;
    public Object[][] pawnEvalWhite;
    public YourEvaluator() {
        this.pawnEvalWhite = new Object[][]{{ 5.0,  5.0,  5.0,  5.0,  5.0,  5.0}, { 1.0,  2.0,  3.0,  3.0,  2.0,  1.0}, { 0.5,  1.0,  2.5,  2.5,  1.0,  0.5}, { 0.0,  0.0,  2.0,  2.0,  0.0,  0.0}, {-0.5, -1.0,  0.0,  0.0, -1.0, -0.5}, { 1.0,  1.0, -2.0, -2.0,  1.0,  1.0}};
        this.knightEval = new Object[][]{{-2.0,  0.0,  0.0,  0.0,  0.0, -2.0}, { 0.0,  1.0,  1.5,  1.5,  1.0,  0.0}, { 0.5,  1.5,  2.0,  2.0,  1.5,  0.5}, {  0.0,  1.5,  2.0,  2.0,  1.5,  0.0}, {-3.0,  0.5,  1.0,  1.5,  1.5,  1.0,  0.5}, { -2.0,  0.0,  0.5,  0.5,  0.0, -2.0}};
        this.bishopEvalWhite = new Object[][]{{  0.0,  0.0,  0.0,  0.0,  0.0,  0.0}, {  0.0,  0.5,  1.0,  1.0,  0.5,  0.0}, { 0.5,  0.5,  1.0,  1.0,  0.5,  0.5}, { 0.0,  1.0,  1.0,  1.0,  1.0,  0.0}, { 1.0,  1.0,  1.0,  1.0,  1.0,  1.0}, { 0.5,  0.0,  0.0,  0.0,  0.0,  0.5}};
        this.rookEvalWhite = new Object[][]{{   1.0,  1.0,  1.0,  1.0,  1.0,  1.0}, {   0.0,  0.0,  0.0,  0.0,  0.0,  0.0}, {   0.0,  0.0,  0.0,  0.0,  0.0,  0.0}, {   0.0,  0.0,  0.0,  0.0,  0.0,  0.0}, {   0.0,  0.0,  0.0,  0.0,  0.0,  0.0}, {   0.0,  0.0,  0.0,  0.0,  0.0,  0.0}};
        this.evalQueen = new Object[][]{{  0.0,  0.0,  0.0,  0.0,  0.0,  0.0}, {  0.0,  0.5,  0.5,  0.5,  0.5,  0.0}, {  0.0,  0.5,  0.5,  0.5,  0.5,  0.0}, {  0.0,  0.5,  0.5,  0.5,  0.5,  0.0}, {  0.5,  0.5,  0.5,  0.5,  0.5,  0.0}, {  0.0,  0.5,  0.0,  0.0,  0.0,  0.0}};
        this.kingEvalWhite = new Object[][]{{ -4.0, -4.0, -5.0, -5.0, -4.0, -4.0}, { -4.0, -4.0, -5.0, -5.0, -4.0, -4.0}, { -4.0, -4.0, -5.0, -5.0, -4.0, -4.0}, { -3.0, -3.0, -4.0, -4.0, -3.0, -3.0}, { -2.0, -2.0, -2.0, -2.0, -2.0, -2.0}, {  2.0,  0.0,  0.0,  0.0,  0.0,  2.0}};
    }

    // Implement your heuristic evaluation function here!
    // The below default function prefers:
    //  * not to lose (white king is worth at lot)
    //  * to win (black not having a king is worth a lot)
    //  * to have more white pieces (+1) and less black pieces (-1)

	public double eval(Position p) {
		double ret = 0;
		for(int x = 0; x < p.board.length; ++x) {
			for(int y = 0; y < p.board[x].length; ++y) {
				if(p.board[x][y] == Position.Empty) continue;
				if(p.board[x][y] == Position.WKing) ret += 900 + (Double)kingEvalWhite[x][y];
				if(p.board[x][y] == Position.WQueen) ret += 90 + (Double)evalQueen[x][y];
				if(p.board[x][y] == Position.WRook) ret += 50 + (Double)rookEvalWhite[x][y];
				if(p.board[x][y] == Position.WBishop) ret += 30 + (Double)bishopEvalWhite[x][y];
				if(p.board[x][y] == Position.WKnight) ret += 30 + (Double)knightEval[x][y];
				if(p.board[x][y] == Position.WPawn) ret += 10 + (Double)pawnEvalWhite[x][y];
				if(p.board[x][y] == Position.BKing) ret -= 900 + (Double)kingEvalWhite[5-x][5-y];
				if(p.board[x][y] == Position.BQueen) ret -= 90 + (Double)evalQueen[5-x][5-y];
				if(p.board[x][y] == Position.BRook) ret -= 50 + (Double)rookEvalWhite[5-x][5-y];
				if(p.board[x][y] == Position.BBishop) ret -= 30 + (Double)bishopEvalWhite[5-x][5-y];
				if(p.board[x][y] == Position.BKnight) ret -= 30 + (Double)knightEval[5-x][5-y];
				if(p.board[x][y] == Position.BPawn) ret -= 10 + (Double)pawnEvalWhite[5-x][5-y];
			}
		}
		return ret;
	}
}
        















