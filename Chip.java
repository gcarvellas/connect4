
public class Chip {

	// Variables
	// isChecked is used for the algorithm in Main that detects if the game is over.
	private int chipType;

	// Constructors
	public Chip() {
		chipType = 0;
	}

	public Chip(int chipType) {
		this.chipType = chipType;
	}

	// Accessor Methods
	public int getChipType() {
		return chipType;
	}

	// Mutator Methods
	public void changeChipType(int chipType) {
		this.chipType = chipType;
	}

}
