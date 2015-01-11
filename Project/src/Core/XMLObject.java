package Core;

public class XMLObject {
	
	public int time;
	public Lokum[][] lokumArray;
	public int currentScore;
	public int level;
	public int moveCount;
	public int specialMove;
	public String gameName;
	
	public Lokum[][] getLokumArray() {
		return lokumArray;
	}
	public void setLokumArray(Lokum[][] lokumArray) {
		this.lokumArray = lokumArray;
	}
	public int getCurrentScore() {
		return currentScore;
	}
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getMoveCount() {
		return moveCount;
	}
	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	public void setSpecialMove(int specialMove) {
		this.specialMove = specialMove;
	}
	public int getTime() {
		return time;
	}
	public int getSpecialMove() {
		return specialMove;
	}
	
	
}
