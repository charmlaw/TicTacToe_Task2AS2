import java.awt.Graphics;
import java.awt.Color;

public class Board {
	// grid line width
	public static final int GRID_WIDTH = 8;
	
	// grid line half width
	public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
	
	//2D array of ROWS-by-COLS Cell instances
	Cell [][] cells;
	
	/** 
	 * Constructor to create the game board
	 */
	public Board() {
		
		// Initialized the cells array using ROWS and COLS constants 
		cells = new Cell[GameMain.ROWS] [GameMain.COLS];

		
		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				cells[row][col] = new Cell(row, col);
			}
		}
	}
	

	 /** 
	  * Return true if it is a draw (i.e., no more EMPTY cells) 
	  * 
	  * @return boolean indicating if the game is a draw
	  */ 
	public boolean isDraw() {		 
		// Check if there are any Empty cells left
		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				if (cells [row] [col].content == Player.EMPTY) {
					// If any cell is Empty, it's not a draw
					return false;
				}
			}
		}

		return true;	
	}
	
	/** 
	 * Return true if the current player has won after making their move
	 * 
	 *   @param thePlayer the player to check for winning
	 *   @param playerRow the row of the last move
	 *   @param playerCol the column of the last move
	 *   @param boolean indicating if the player has won
	 */
	public boolean hasWon(Player thePlayer, int playerRow, int playerCol) {
		 // Check if player has 3-in-that-row
		if(cells[playerRow][0].content == thePlayer 
		   && cells[playerRow][1].content == thePlayer 
		   && cells[playerRow][2].content == thePlayer ) {
			return true; 
		}
		
		 // Check if player has 3-in-that-col		
		if(cells[0][playerCol].content == thePlayer 
		   && cells[1][playerCol].content == thePlayer 
		   && cells[2][playerCol].content == thePlayer ) {
			return true; 
		}
			
		 // Check 3-in-the-diagonal
		if(cells[0][0].content == thePlayer 
		   && cells[1][1].content == thePlayer 
		   && cells[2][2].content == thePlayer) {
			return true;
		}
		
		// Check 3-in-the-diagonal other direction
		if(cells[0][2].content == thePlayer 
		   && cells[1][1].content == thePlayer 
		   && cells[2][0].content == thePlayer) {
			return true;
		}
	
		//no winner, keep playing
		return false;
	}
	
	
	/**
	 * Draws the grid (rows then columns) using constant sizes,
	 * then calls on the Cells to paint themselves into the grid
	 * 
	 * @param g the Graphics object to paint on
	 */
	public void paint(Graphics g) {
		// Draw the grid
		g.setColor(Color.GRAY);
		
		// Draw horizontal lines
		for (int row = 1; row < GameMain.ROWS; ++row) {          
			g.fillRoundRect(
				0, 
				GameMain.CELL_SIZE * row - GRID_WIDTH_HALF,                
				GameMain.CANVAS_WIDTH - 1, 
				GRID_WIDTH,                
				GRID_WIDTH, 
				GRID_WIDTH
			);       
		}
		
		// Draw vertical lines
		for (int col = 1; col < GameMain.COLS; ++col) {          
			g.fillRoundRect(
				GameMain.CELL_SIZE * col - GRID_WIDTH_HALF, 
				0,                
				GRID_WIDTH, 
				GameMain.CANVAS_HEIGHT - 1,                
				GRID_WIDTH, 
				GRID_WIDTH
			);
		}
		
		//Draw the cells
		for (int row = 0; row < GameMain.ROWS; ++row) {          
			for (int col = 0; col < GameMain.COLS; ++col) {  
				cells[row][col].paint(g);
			}
		}
	}
	

}
