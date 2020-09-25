public class Computer extends Player {

	// Variables
	private Board board;
	private int row, col;
	private final int BOARD_MIDDLE;

	// Constructor
	public Computer(Board board) {
		super();
		this.board = board;
		row = board.getRows();
		col = board.getCols();
		BOARD_MIDDLE = col / 2;
	}

	// Accessor Methods
	public boolean isComputer() {
		return true;
	}

	private int chipAt(int x1, int y1) {
		return board.getChip(x1, y1);
	}

	public int getMove() {
		// Priority #1: Connect 4th
		if (connect4th(2) != -1) {
			System.out.println("Connect 4th");
			return connect4th(2);
		}
		// Priority #2: Block 4th
		if (connect4th(1) != -1) {
			System.out.println("Block 4th");
			return connect4th(1);
		}
		// Priority #3 Block Horizontals
		if (horizontalPairs(1) != -1) {
			System.out.println("Block Horizontals");
			return horizontalPairs(1);
		}
		// Priority #4: Middle Row
		if (isSlotAvailable(BOARD_MIDDLE)) {
			System.out.println("Middle Row");
			return BOARD_MIDDLE;
		}
		// Priority #5 Horizontals
		if (horizontalPairs(2) != -1) {
			System.out.println("Horizontals");
			return horizontalPairs(2);
		}

		// Priority #6: Connect 2nd
		if (makePair() != -1) {
			System.out.println("Connect 2nd");
			return makePair();
		}
		// Priority #7: Split From Middle
		return getClosestToMiddle(BOARD_MIDDLE);
	}

	private boolean isSlotAvailable(int col) {
		if (chipAt(0, col) == 0)
			return true;
		return false;
	}

	// Helper Method

	private boolean isChipsEqual(int x1, int y1, int x2, int y2) {
		if (board.getChip(x1, y1) == board.getChip(x2, y2))
			return true;
		return false;
	}

	// Mutator Methods
	private int horizontalPairs(int player) {
		for (int i = 0; i < col; i++) {
			if (chipAt(row - 1, i) == player) {
				// Bottom Row 3's
				// Left to Right
				try {
					if (isChipsEqual(row - 1, i, row - 1, i + 1) && isChipsEqual(row - 1, i + 1, row - 1, i + 2)
							&& chipAt(row - 1, i + 3) == 0) {
						return i + 3;
					}
				} catch (Exception e) {
				}
				// Right to Left
				try {
					if (isChipsEqual(row - 1, i, row - 1, i - 1) && isChipsEqual(row - 1, i - 1, row - 1, i - 2)
							&& chipAt(row - 1, i - 3) == 0) {
						return i - 3;
					}
				} catch (Exception e) {
				}
				// Bottom Row 2's
				// Left to Right
				try {
					if (isChipsEqual(row - 1, i, row - 1, i + 1) && chipAt(row - 1, i + 2) == 0) {
						return i + 2;
					}
				} catch (Exception e) {
				}
				// Right to Left
				try {
					if (isChipsEqual(row - 1, i, row - 1, i - 1) && chipAt(row - 1, i - 2) == 0) {
						return i - 2;
					}
				} catch (Exception e) {
				}
			}
		}
		// Other Horizontal Rows
		// Left to Right
		for (int i = 0; i < col; i++) {
			for (int j = row - 1; j > -1; j--) {
				if (chipAt(j, i) == player) {
					try {
						if (isChipsEqual(j, i, j, i + 1) && chipAt(j, i + 2) == 0 && chipAt(j+1,i+2) != 0) {
							return i + 2;
						}
					} catch (Exception e) {
					}
					// Right to Left
					try {
						if (isChipsEqual(j, i, j, i - 1) && chipAt(j, i - 2) == 0 && chipAt(j+1,i+2) != 0) {
							return i - 2;
						}
					} catch (Exception e) {
					}
				}
			}
		}
		return -1;
	}

	private int connect4th(int player) {
		for (int i = row - 1; i > -1; i--) {
			for (int j = 0; j < col; j++) {
				if (chipAt(i, j) == player) {
					try {

						// horizontal
						if (isChipsEqual(i, j, i, j + 1) && isChipsEqual(i, j + 1, i, j + 2)
								&& (board.getChip(i + 1, j + 3) == 0 || i > (col - 1) || i < 0)
								&& ((board.getChip(i + 2, j + 3) != 0)))
							return j + 3;
					} catch (Exception e) {
					}
					try {
						// vertical
						if (isChipsEqual(i, j, i - 1, j) && isChipsEqual(i - 1, j, i - 2, j)
								&& (board.getChip(i - 3, j) == 0 || i > (col - 1) || i < 0))
							return j;
					} catch (Exception e) {
					}
					try {
						// Top-Right Diagonals
						if (isChipsEqual(i, j, i - 1, j + 1)
								&& isChipsEqual(i - 1, j + 1, i - 2, j + 2) && (board.getChip(i - 3, j + 3) == 0
										|| i > (col - 1) || i < 0 || j < 0 || j > (row - 1))
								&& (board.getChip(i - 2, j + 3)) != 0)
							return j + 3;
					} catch (Exception e) {
					}
					try {
						// Top-Left Diagonals
						if (isChipsEqual(i, j, i - 1, j - 1)
								&& isChipsEqual(i - 1, j - 1, i - 2, j - 2) && (board.getChip(i - 3, j - 3) == 0
										|| i > (col - 1) || i < 0 || j < 0 || j > (row - 1))
								&& (board.getChip(i - 2, j - 3) != 0))
							return j - 3;
					} catch (Exception e) {
					}
					try {
						// Bottom-Right Diagonals
						if (isChipsEqual(i, j, i + 1, j + 1)
								&& isChipsEqual(i + 1, j + 1, i + 2, j + 2) && (board.getChip(i + 3, j + 3) == 0
										|| i > (col - 1) || i < 0 || j < 0 || j > (row - 1))
								&& (board.getChip(i + 4, j + 3)) != 0)
							return j + 3;
					} catch (Exception e) {
					}
					try {
						// Bottom-Left Diagonals
						if (isChipsEqual(i, j, i + 1, j - 1)
								&& isChipsEqual(i + 1, j - 1, i + 2, j - 2) && (board.getChip(i + 3, j - 3) == 0
										|| i > (col - 1) || i < 0 || j < 0 || j > (row - 1))
								&& (board.getChip(i + 4, j - 3)) != 0)
							return j - 3;
					} catch (Exception e) {
					}
				}
			}
		}
		return -1;
	}

	private int makePair() { // returns the column needed for the computer to make a pair
		for (int i = row - 1; i > -1; i--) {// y Axis
			for (int j = 0; j < col; j++) { // x Axis
				// Left Direction
				if (board.getChip(i, j) == 2) {
					try {
						if ((board.getChip(i, j - 1) == 0 || i > (col - 1) || i < 0 || j < 0 || j > (row - 1))
								&& board.getChip(i + 1, j - 1) != 0)
							return j - 1;
					} catch (Exception e) {
					}

					// Right Direction
					try {
						if ((board.getChip(i, j + 1) == 0 || i > (col - 1) || i < 0 || j < 0 || j > (row - 1))
								&& board.getChip(i + 1, j + 1) != 0)
							return j + 1;
					} catch (Exception e) {
					}

					// Top Right Direction
					try {
						if ((board.getChip(i - 1, j + 1) == 0 || i > (col - 1) || i < 0 || j < 0 || j > (row - 1))
								&& board.getChip(i, j + 1) != 0)
							return j + 1;
					} catch (Exception e) {
					}

					// Top Left Direction
					try {
						if ((board.getChip(i - 1, j - 1) == 0 || i > (col - 1) || i < 0 || j < 0 || j > (row - 1))
								&& board.getChip(i, j - 1) != 0)
							return j - 1;
					} catch (Exception e) {
					}

					// Bottom Right Direction
					try {
						if ((board.getChip(i + 1, j + 1) == 0 || i > (col - 1) || i < 0 || j < 0 || j > (row - 1))
								&& board.getChip(i + 2, j + 1) != 0)
							return j + 1;
					} catch (Exception e) {
					}

					// Bottom Left Direction
					try {
						if ((board.getChip(i + 1, j - 1) == 0 || i > (col - 1) || i < 0 || j < 0 || j > (row - 1))
								&& board.getChip(i + 2, j - 1) != 0)
							return j - 1;
					} catch (Exception e) {
					}

					// Top Direction
					try {
						if ((board.getChip(i - 1, j) == 0 || i > (col - 1) || i < 0 || j < 0 || j > (row - 1)))
							return j;
					} catch (Exception e) {
					}
				}
			}
		}
		return -1; // no possible pair combination was found
	}

	private int[] createColSplitter() {
		int[] output = new int[col];
		output[0] = BOARD_MIDDLE;
		for (int i = 1; i < output.length; i++) {
			if (i % 2 == 1)
				output[i] = output[i - 1] + i;
			else
				output[i] = output[i - 1] - i;
		}
		return output;
	}

	private int getClosestToMiddle(int col) {
		int[] cols = createColSplitter();
		for (int i = 0; i < cols.length; i++) {
			if (isSlotAvailable(cols[i])) {
				return cols[i];
			}
		}
		return 0;
	}
}
