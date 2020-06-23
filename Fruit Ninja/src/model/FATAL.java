package model;

import javafx.scene.image.ImageView;

public class FATAL extends BOMBS {
	ImageView fatal = new ImageView("view/resources/bomb3.png");
	public FATAL() {
		super();
		fatal.setFitHeight(100);
		fatal.setFitWidth(100);
		setBomb(fatal);
	}
	
}
