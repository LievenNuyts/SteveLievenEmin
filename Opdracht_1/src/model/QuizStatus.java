package model;

public enum QuizStatus {

	UNDERCONSTRUCTION("Under Construction"), 
	COMPLETED("Completed"), 
	READY("Ready"),
	LASTCHANCE("Last Chance"), 
	CLOSED("Closed");

	private final String StatusID;

	QuizStatus(String StatusID) { 
		this.StatusID = StatusID; 
	}

	@Override
	public String toString() {
		return StatusID;
	}

}
