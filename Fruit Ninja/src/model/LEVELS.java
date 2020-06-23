package model;

public enum LEVELS {

	EASY("view/resources/levels/ninja2.png"),
	MEDIUM("view/resources/levels/ninja.png"),
	HARD("view/resources/levels/pikatchu.png");
	
	
	private String urlLevels;
	
	private LEVELS(String urlLevels) {
		this.urlLevels = urlLevels;
	}

	public String getUrl() {
		return this.urlLevels;
	}
}
