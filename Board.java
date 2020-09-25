public class Board {

	// Variables
	private int row, col;
	private Chip[][] grid;

	// Constructors

	public Board(int row, int col) {
		this.row = row;
		this.col = col;
		grid = new Chip[this.row][this.col];
		initializeGrid();
	}

	private void initializeGrid() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				grid[i][j] = new Chip();
			}
		}
	}

	// Accessor Methods
	public int getChip(int row, int col) {
		return grid[row][col].getChipType();
	}
	
	public int getRows(){
		return row;
	}
	
	public int getCols() {
		return col;
	}

	public int isGameOver() {
		for (int i = row - 1; i > -1; i--) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j].getChipType() != 0 ) {
				try {
					
					// horizontal
					if ((grid[i][j].getChipType() == grid[i][j + 1].getChipType())
							&& (grid[i][j + 1].getChipType() == grid[i][j + 2].getChipType())
							&& (grid[i][j + 2].getChipType() == grid[i][j + 3].getChipType()))
						return grid[i][j].getChipType();
				} catch (Exception e) {
				}
				try {
					// vertical
					if ((grid[i][j].getChipType() == grid[i - 1][j].getChipType())
							&& (grid[i - 1][j].getChipType() == grid[i - 2][j].getChipType())
							&& (grid[i - 2][j].getChipType() == grid[i - 3][j].getChipType()))
						return grid[i][j].getChipType();
				} catch (Exception e) {
				}
				try {
					// Top-Right Diagonals
					if ((grid[i][j].getChipType() == grid[i - 1][j + 1].getChipType())
							&& (grid[i - 1][j + 1].getChipType() == grid[i - 2][j + 2].getChipType())
							&& (grid[i - 2][j + 2].getChipType() == grid[i - 3][j + 3].getChipType()))
						return grid[i][j].getChipType();
				} catch (Exception e) {
				}
				try {
					// Top-Left Diagonals
					if ((grid[i][j].getChipType() == grid[i - 1][j - 1].getChipType())
							&& (grid[i - 1][j - 1].getChipType() == grid[i - 2][j - 2].getChipType())
							&& (grid[i - 2][j - 2].getChipType() == grid[i - 3][j - 3].getChipType()))
						return grid[i][j].getChipType();
				} catch (Exception e) {
				}
				}
				}
		}
		return 0;
	}

	// Mutator Methods
	public void insertChip(Chip input, int columnNum) throws RowException {
		for (int i = row - 1; i > -2; i--) { // Loop goes past the row index for the try/catch block
			try {
				if ((grid[i][columnNum]).getChipType() == 0) {
					grid[i][columnNum] = input;
					break;
				}
			} catch (ArrayIndexOutOfBoundsException RowException) {
				if (i == -1)
					throw new RowException("Row is full. Try another row.");
				else
					throw new NumberFormatException("Column does not exist. Try another column.");
			}
		}
	}
}
