package model;

import javafx.scene.image.ImageView;

public class DANGEROUS extends BOMBS {
	ImageView dangerous = new ImageView("view/resources/dangerous.png");
	public DANGEROUS() {
		super();
		dangerous.setFitHeight(100);
		dangerous.setFitWidth(100);
		setBomb(dangerous);
	}
	

}
